package controller;

import data.dao.MetodoPagoDao;
import data.dao.ReporteAnonimoDao;
import data.dao.ReporteClienteDao;
import data.dao.TipoResiduoDao;
import model.Direccion;
import model.ReporteAnonimo;
import model.ReporteCliente;
import model.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "ReportServlet", value = {"/clientReport", "/anonymousReport", "/anonymousReportRequest",
        "/clientReportRequest", "/searchByStatus"})
public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TipoResiduoDao wasteType = new TipoResiduoDao();
        MetodoPagoDao paymentMethod = new MetodoPagoDao();
        wasteType.selectAll();
        paymentMethod.selectAll();
        request.getSession(true).setAttribute("wasteType", wasteType.getTrashList());
        request.getSession(true).setAttribute("paymentMethod", paymentMethod.getPaymentList());
        switch ( request.getServletPath() ) {
            case "/anonymousReportRequest":
                response.sendRedirect("views/report/anonymousReport.jsp");
                break;
            case "/clientReportRequest":
                response.sendRedirect("views/report/clientReport.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ReporteClienteDao myClientReport = new ReporteClienteDao();
        switch ( request.getServletPath() ) {
            case "/clientReport":
                int tipoResiduo = Integer.parseInt( request.getParameter("tipoResiduo") ),
                        metodoPago = Integer.parseInt( request.getParameter("metodoPago") );
                short status = Short.parseShort( request.getParameter("status") );
                boolean estaPagado = !(metodoPago == 3); // if equals 3 then it'll be paid with cash, then is not paid at
                // insertion
                // may insert on ReporteCliente on database
                int fkCliente = Integer.parseInt( request.getParameter("usuarioId") ),
                        fkTipoResiduo = Integer.parseInt( request.getParameter("tipoResiduo") ),
                        fkMetodoPago = Integer.parseInt( request.getParameter("metodoPago") );
                short fkEstado = Short.parseShort( request.getParameter("status") );

                ReporteCliente clientReport = new ReporteCliente(fkCliente, fkTipoResiduo, fkMetodoPago, estaPagado,
                        fkEstado);

                if ( !myClientReport.insert(clientReport) ) {
                    request.setAttribute("reportInsertFail", true);
                    request.getRequestDispatcher("views/report/clientReport.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("reportInsert", true);
                    request.getSession().setAttribute("folio", myClientReport.retrieveLastReportId());
                    response.sendRedirect("views/user/profile.jsp");
                }
                break;

            case "/anonymousReport":
                tipoResiduo = Integer.parseInt( request.getParameter("tipoResiduo") );
                metodoPago = Integer.parseInt( request.getParameter("metodoPago") );
                status = Short.parseShort( request.getParameter("status") );
                estaPagado = !(metodoPago == 3); // if equals 3 then it'll be paid with cash, then is not paid at
                // insertion
                // may insert on ReporteAnonimo on database
                String nombre = request.getParameter("nombre"),
                        apellidop = request.getParameter("apellidop"),
                        apellidom = request.getParameter("apellidom"),
                        telefono = request.getParameter("telefono"),
                        extension = request.getParameter("extension"),
                        correo = request.getParameter("correo"),
                        codigoPostal = request.getParameter("codigoPostal"),
                        colonia = request.getParameter("colonia"),
                        calle = request.getParameter("calle"),
                        referencias = request.getParameter("ref"),
                        ciudad = request.getParameter("ciudad"),
                        municipio = request.getParameter("municipio"),
                        estado = request.getParameter("estado");
                short numeroExterior = Short.parseShort( request.getParameter("numeroExterior") ),
                        numeroInterior = Short.parseShort( request.getParameter("numeroInterior") );
                ReporteAnonimoDao myAnonimousReport = new ReporteAnonimoDao();
                ReporteAnonimo anonymousReport = new ReporteAnonimo(nombre, apellidop, apellidom, telefono, extension,
                        new Direccion(codigoPostal, colonia, calle, referencias, numeroExterior, numeroInterior,
                                ciudad, municipio, estado), correo, tipoResiduo, metodoPago, estaPagado, status);

                if ( !myAnonimousReport.insert( anonymousReport ) ) {
                    request.setAttribute("reportInsertFail", true);
                    request.getRequestDispatcher("views/report/anonymousReport.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("reportInsert", true);
                    request.getSession().setAttribute("folio", myAnonimousReport.retrieveLastReportId());
                    response.sendRedirect("./index.jsp");
                }

                break;

            case "/searchByStatus":
                String folio = request.getParameter("folio"),
                        statusReport = request.getParameter("status");
                Usuario user = (Usuario) request.getSession().getAttribute("currentUser");
                if ( folio.equals("") ) { // search by status
                    LinkedList<ReporteClienteDao.User> userList = myClientReport.searchByStatus(user.getClienteId(),
                            Short.parseShort(statusReport));
                    if (!userList.isEmpty())
                        request.getSession().setAttribute("userReportList", userList);
                    else {
                        request.getSession().setAttribute("userReportList", null);
                        request.getSession().setAttribute("showPopupMessage", true);
                        request.getSession().setAttribute("searchType","estado");
                    }
                    response.sendRedirect("views/user/profile.jsp");
                } else { // search by folio
                    myClientReport.select(Integer.parseInt(folio));
                    LinkedList<ReporteClienteDao.User> report = myClientReport.getAuxUser();
                    if ( report == null || report.isEmpty() ) {
                        request.getSession().setAttribute("userReportList", null);
                        request.getSession().setAttribute("showPopupMessage", true);
                        request.getSession().setAttribute("searchType", "folio");
                    } else {
                        request.getSession().setAttribute("userReportList", report);
                    }
                    response.sendRedirect("views/user/profile.jsp");
                }
                break;
        }
    }

    }

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
        "/clientReportRequest", "/searchByStatus", "/anonymousQuery", "/selectAllUser", "/selectAllAnonymous",
        "/adminQueryUser", "/adminQueryAnonymous"})
public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ReporteClienteDao myReport = new ReporteClienteDao();
        ReporteAnonimoDao anonymousReport = new ReporteAnonimoDao();
        switch ( request.getServletPath() ) {
            case "/selectAllUser":
                myReport.selectAll();
                request.getSession().setAttribute("userReport", myReport.getReportList());
                response.sendRedirect("views/admin/admin.jsp");
                break;
            case "/selectAllAnonymous":
                anonymousReport.selectAll();
                request.getSession().setAttribute("anonymousReport", anonymousReport.getReportList());
                response.sendRedirect("views/admin/admin.jsp");
                break;
            case "/anonymousReportRequest":
                setReportAttributes(request);
                response.sendRedirect("views/report/anonymousReport.jsp");
                break;
            case "/clientReportRequest":
                setReportAttributes(request);
                response.sendRedirect("views/report/clientReport.jsp");
                break;
            case "/adminQueryUser":
                String folio = request.getParameter("folio"),
                        usuarioid = request.getParameter("userid"),
                        estado = request.getParameter("status"),
                        sql = "";

                if ( !folio.equals("") ) { // search by folio
                    // select * from reportecliente where folio = folio
                    sql = String.format("select * from reportecliente where folio = %d;", Integer.parseInt(folio));
                } else { // search either by userid, report status or both
                    if ( !usuarioid.equals("") ) {
                        // select * from reportecliente where fk_cliente = usuarioid and fk_estado = estado
                        sql = String.format("select * from reportecliente where fk_cliente = %d and fk_estado = %d;",
                                Integer.parseInt(usuarioid), Integer.parseInt(estado));
                    } else {
                        // select * from reportecliente where fk_estado = estado
                        sql = String.format("select * from reportecliente where fk_estado = %d;", Integer.parseInt(estado));
                    }
                }
                if ( !myReport.selectAllWhere(sql) ) {
                    // on failure
                    request.getSession().setAttribute("showPopupMessage", true);
                    request.getSession().setAttribute("popUpMessage", "No se encontró información con los parámetros de búsqueda especificados.");
                    request.getSession().setAttribute("userReport", null);
                    request.getSession().setAttribute("statusImage", "404-oops.png");
                } else {
                    // on success
                    request.getSession().setAttribute("userReport", myReport.getReportList());
                }

                response.sendRedirect("views/admin/admin.jsp");
                break;
            case "/adminQueryAnonymous":
                folio = request.getParameter("reportid");
                estado = request.getParameter("estatus");
                sql = "";

                if ( !folio.equals("") ) { // search by folio
                    // select * from reporteanonumo where folio = folio
                    sql = String.format("select * from reporteanonimo where folio = %d;", Integer.parseInt(folio));
                } else { // search report status
                    // select * from anonimo where fk_estado = estado
                    sql = String.format("select * from reporteanonimo where fk_estado = %d;", Integer.parseInt(estado));
                }

                if ( !anonymousReport.selectAllWhere(sql) ) {
                    // on failure
                    request.getSession().setAttribute("showPopupMessage", true);
                    request.getSession().setAttribute("popUpMessage", "No se encontró información con los parámetros de búsqueda especificados.");
                    request.getSession().setAttribute("anonymousReport", null);
                    request.getSession().setAttribute("statusImage", "404-oops.png");
                } else {
                    // on success
                    request.getSession().setAttribute("anonymousReport", anonymousReport.getReportList());
                }

                response.sendRedirect("views/admin/admin.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ReporteClienteDao myClientReport = new ReporteClienteDao();
        ReporteAnonimoDao myAnonimousReport = new ReporteAnonimoDao();
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
                    if ( report == null || report.isEmpty() ) { // fail
                        request.getSession().setAttribute("userReportList", null);
                        request.getSession().setAttribute("showPopupMessage", true);
                        request.getSession().setAttribute("searchType", "folio");
                    } else { // success
                        request.getSession().setAttribute("userReportList", report);
                    }
                    response.sendRedirect("views/user/profile.jsp");
                }
                break;

            case "/anonymousQuery":
                folio = request.getParameter("folio");
                if ( !folio.equals("") ) { // search by folio
                    myAnonimousReport.select( Integer.parseInt(folio) );
                    LinkedList<ReporteClienteDao.User> report = myAnonimousReport.getReportAux();
                    if ( report == null || report.isEmpty() ) { // fail
                        request.getSession().setAttribute("anonymousReportList", null);
                        request.getSession().setAttribute("showPopupMessage", true);
                        request.getSession().setAttribute("popupMessage", "No se encontró ningún reporte con el folio " + folio);
                    } else { // success
                        request.getSession().setAttribute("anonymousReportList", report);
                    }
                    response.sendRedirect("views/report/queryAnonymousReport.jsp");
                } else { // no folio was found
                    request.getSession().setAttribute("anonymousReportList", null);
                    request.getSession().setAttribute("showPopupMessage", true);
                    request.getSession().setAttribute("popupMessage", "Asegúrese de ingresar el número de folio e intente de nuevo.");
                    response.sendRedirect("views/report/queryAnonymousReport.jsp");
                }
                break;
        }
    }

    /*
    * Helper method that collects data from TipoResiduo and
    * MetodoPago database tables and puts the result set
    * content in session attributes in order to use that
    * data in select html tags on both client report and
    * anonymous report views.
    * */
    private void setReportAttributes(HttpServletRequest request) {
        TipoResiduoDao wasteType = new TipoResiduoDao();
        MetodoPagoDao paymentMethod = new MetodoPagoDao();
        wasteType.selectAll();
        paymentMethod.selectAll();
        request.getSession(true).setAttribute("wasteType", wasteType.getTrashList());
        request.getSession(true).setAttribute("paymentMethod", paymentMethod.getPaymentList());
    }
}

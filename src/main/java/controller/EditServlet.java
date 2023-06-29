package controller;

import data.dao.ReporteAnonimoDao;
import data.dao.ReporteClienteDao;
import model.ReporteAnonimo;
import model.ReporteCliente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;

/**
 *  Servlet to handle report modifications
 *  made in admin.jsp view.
 * */
@WebServlet(name = "EditServlet", value = {"/editClientReport", "/editAnonymousReport", "/deleteAnonymous", "/deleteReport"})
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ReporteClienteDao myUserReport = new ReporteClienteDao();
        ReporteAnonimoDao myAnonymousReport = new ReporteAnonimoDao();
        int folio;
        boolean isPaid;
        short status;

        switch ( request.getServletPath() ) {
            case "/editClientReport":
                folio = Integer.parseInt( request.getParameter("folioBtn") );
                // when this servlet is invoked, this list is never null due to modification button only get drawn
                // if this list is different from null
                // LinkedList<ReporteCliente> myList = (LinkedList<ReporteCliente>) request.getSession().getAttribute("userReport");
                LinkedList<ReporteCliente> myList = myUserReport.select(folio);
                if (myList != null) {
                    ReporteCliente myReport = myList.get(0);
                    isPaid = Boolean.parseBoolean( request.getParameter("pagado") );
                    status = Short.parseShort( request.getParameter("reportStatus") );
                    // we update the changes
                    myReport.setPagado(isPaid);
                    myReport.setFkEstado(status);

                    if ( !myUserReport.update(myReport) ) {
                        // on failure set error message in admin view
                        setUpdateErrorPopup(request);
                    } else {
                        // on success set success message in admin view
                        setUpdateSuccessPopup(request);
                    }
                    request.getSession().setAttribute("showPopupMessage", true);
                    response.sendRedirect("views/admin/admin.jsp");
                }
                break;
            case "/editAnonymousReport":
                folio = Integer.parseInt( request.getParameter("btnFolio") );
                LinkedList<ReporteAnonimo> reportesAnonimos = myAnonymousReport.select (folio);
                if (reportesAnonimos != null) {
                    ReporteAnonimo reporteAnonimo = reportesAnonimos.get(0);
                    isPaid = Boolean.parseBoolean( request.getParameter("paid") );
                    status = Short.parseShort( request.getParameter("estado") );
                    // update changes
                    reporteAnonimo.setPagado(isPaid);
                    reporteAnonimo.setFk_estado(status);

                    if ( !myAnonymousReport.update(reporteAnonimo) ) {
                        // on failure
                        setUpdateErrorPopup(request);
                    } else {
                        // on success
                        setUpdateSuccessPopup(request);
                    }
                    request.getSession().setAttribute("showPopupMessage", true);
                    response.sendRedirect("views/admin/admin.jsp");
                }
                break;
            case "/deleteAnonymous":
                folio = Integer.parseInt( request.getParameter("btnFolio") );
                if ( myAnonymousReport.delete(folio) ) { // on success
                    setDeleteSuccessPopup(request);
                } else { // on failure
                    setDeleteErrorPopup(request);
                }
                request.getSession().setAttribute("showPopupMessage", true);
                response.sendRedirect("views/admin/admin.jsp");
                break;
            case "/deleteReport":
                folio = Integer.parseInt( request.getParameter("btnFolio") );
                if ( myUserReport.delete(folio) ) { // on success
                    setDeleteSuccessPopup(request);
                } else { // on failure
                    setDeleteErrorPopup(request);
                }
                request.getSession().setAttribute("showPopupMessage", true);
                response.sendRedirect("views/admin/admin.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void setUpdateErrorPopup(HttpServletRequest request) {
        request.getSession().setAttribute("popUpMessage", "Ocurrió un error al actualizar el registro. Inténtelo de nuevo.");
        request.getSession().setAttribute("statusImage", "404-oops.png");
        request.getSession().setAttribute("alt", "oops");
        request.getSession().setAttribute("title", "¡Oops!");
    }

    private void setUpdateSuccessPopup(HttpServletRequest request) {
        request.getSession().setAttribute("popUpMessage", "Se ha actualizado exitosamente el registro.");
        request.getSession().setAttribute("statusImage", "404-tick.png");
        request.getSession().setAttribute("alt","success");
        request.getSession().setAttribute("title", "¡Operación completada!");
    }

    private void setDeleteErrorPopup(HttpServletRequest request) {
        request.getSession().setAttribute("popUpMessage", "Ocurrió un error al eliminar el registro. Inténtelo de nuevo.");
        request.getSession().setAttribute("statusImage", "404-oops.png");
        request.getSession().setAttribute("alt","oops");
        request.getSession().setAttribute("title", "¡Oops!");
    }

    private void setDeleteSuccessPopup(HttpServletRequest request) {
        request.getSession().setAttribute("popUpMessage", "Se ha eliminado exitosamente el reporte.");
        request.getSession().setAttribute("statusImage", "404-tick.png");
        request.getSession().setAttribute("alt","success");
        request.getSession().setAttribute("title", "¡Operación completada!");
    }
}

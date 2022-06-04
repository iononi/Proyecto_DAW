package controller;

import data.dao.ReporteAnonimoDao;
import data.dao.ReporteClienteDao;
import model.ReporteCliente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet(name = "EditServlet", value = {"/editClientReport", "/editAnonymousReport"})
public class EditServlet extends HttpServlet {

    ReporteClienteDao myUserReport = new ReporteClienteDao();
    ReporteAnonimoDao myAnonymousReport = new ReporteAnonimoDao();
    int folio;
    boolean isPaid;
    short status;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch ( request.getServletPath() ) {
            case "/editClientReport":
                folio = Integer.parseInt( request.getParameter("folioBtn") );
                System.out.println("Folio: " + folio);
                LinkedList<ReporteCliente> myList = (LinkedList<ReporteCliente>) request.getSession().getAttribute("userReport");
                ReporteCliente myReport = myList.get( folio - 1 ); // due to folios starting at 1 and index at 0
                System.out.println(myReport);
                System.out.println("Esta pagado: " + isPaid);
                isPaid = Boolean.parseBoolean( request.getParameter("pagado") );
                status = Short.parseShort( request.getParameter("reportStatus") );
                // we update the changes
                myReport.setPagado(isPaid);
                myReport.setFkEstado(status);

                if ( !myUserReport.update(myReport) ) {
                    // on failure
                    request.getSession().setAttribute("popUpMessage", "Ocurrió un error al actualizar el registro. Inténtelo de nuevo.");
                    request.getSession().setAttribute("statusImage", "404-oops.png");
                    request.getSession().setAttribute("alt","oops");
                    request.getSession().setAttribute("title", "¡Oops!");
                } else {
                    // on success
                    request.getSession().setAttribute("popUpMessage", "Se ha actualizado exitosamente el registro.");
                    request.getSession().setAttribute("statusImage", "404-tick.png");
                    request.getSession().setAttribute("alt","success");
                    request.getSession().setAttribute("title", "¡Operación completada!");
                }
                request.getSession().setAttribute("showPopupMessage", true);
                response.sendRedirect("views/admin/admin.jsp");
                break;
            case "/editAnonymousReport":
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
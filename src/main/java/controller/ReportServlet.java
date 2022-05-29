package controller;

import data.dao.MetodoPagoDao;
import data.dao.TipoResiduoDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ReportServlet", value = {"/clientReport", "/anonymousReport", "/anonymousReportRequest"})
public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch ( request.getServletPath() ) {
            case "/clientReport":
                break;

            case "/anonymousReport":
                System.out.println();
                break;

            case "/anonymousReportRequest":
                TipoResiduoDao wasteType = new TipoResiduoDao();
                MetodoPagoDao paymentMethod = new MetodoPagoDao();
                wasteType.selectAll();
                paymentMethod.selectAll();
                request.getSession(true).setAttribute("wasteType", wasteType.getTrashList());
                request.getSession(true).setAttribute("paymentMethod", paymentMethod.getPaymentList());
                response.sendRedirect("views/report/anonymousReport.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

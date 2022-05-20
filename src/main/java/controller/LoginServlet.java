package controller;

import data.dao.ClienteDao;
import model.Cliente;
import model.Direccion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"/login", "/signup"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("Hola desde el metodo doGet :D");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch ( request.getServletPath() ) {
            case "/login":
                // Search for user in DB
                ClienteDao client = new ClienteDao(); //
                Cliente user;
                String email = request.getParameter("email");
                int password = request.getParameter("password").hashCode();
                user = client.find(email, password);
                // If not exists, throw login error
                if ( user == null )
                    response.sendRedirect("views/loginFail.jsp");
                else {
                    // If exists, redirect the user to index.jsp with new sessions created for user
                    request.getSession().setAttribute("currentUser", user);
                    response.sendRedirect("./index.jsp");
                }
                break;

            case "/signup":
                Cliente newClient;
                ClienteDao myClient = new ClienteDao();
                Direccion myDir;
                String  curp = request.getParameter("curp"),
                        rfc = request.getParameter("rfc"),
                        nombre = request.getParameter("nombre"),
                        apellidop = request.getParameter("apellidop"),
                        apellidom = request.getParameter("apellidom"),
                        correo = request.getParameter("correo"),
                        contrasenia = request.getParameter("contrasenia"),
                        extension = request.getParameter("extension"),
                        telefono = request.getParameter("telefono"),
                        codigoPostal = request.getParameter("codigoPostal"),
                        colonia = request.getParameter("colonia"),
                        calle = request.getParameter("calle"),
                        referencias = request.getParameter("ref"),
                        ciudad = request.getParameter("ciudad"),
                        municipio = request.getParameter("municipio"),
                        estado = request.getParameter("estado");
                short numeroExterior = Short.parseShort( request.getParameter("numeroExterior") ),
                        numeroInterior = ( request.getParameter("numeroInterior").isEmpty() ) ? (short) 0 :
                                Short.parseShort( request.getParameter("numeroExterior") );

                myDir = new Direccion(codigoPostal, colonia, calle, referencias, numeroExterior, numeroInterior,
                        ciudad, municipio, estado);

                // Create new client, currentUser
                newClient = new Cliente(curp, rfc, nombre, apellidop, apellidom, correo, contrasenia, extension, telefono,
                        myDir);
                if ( !myClient.insert(newClient) ) {
                    request.getSession().setAttribute("userSignUpFail", "Error al registrar. Inténtelo de nuevo.");
                    request.getRequestDispatcher("views/signup.jsp").forward(request, response);
                } else {
                    request.getSession(true).setAttribute("currentUser", newClient);
                    response.sendRedirect("./index.jsp");
                }

                break;
        }

    }
}

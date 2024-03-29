package controller;

import data.dao.UsuarioDao;
import model.Usuario;
import model.Direccion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Servlet to handle login, logout and signup operations.
 * */
@WebServlet(name = "LoginServlet", value = {"/login", "/signup", "/logout"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("./index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch ( request.getServletPath() ) {
            case "/login":
                // Search for user in DB
                UsuarioDao client = new UsuarioDao(); //
                Usuario user;
                String email = request.getParameter("email");
                int password = request.getParameter("password").hashCode();
                user = client.find(email, password);
                // If not exists, throw login error
                if ( user == null ) {
                    request.setAttribute("userNotFound", true);
                    request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
                }
                else { // user exists in database
                    if ( user.isAdmin() )
                        request.getSession().setAttribute("userIsAdmin", true); // grant admin access

                    // If exists, redirect the user to index.jsp with new session created for user
                    request.getSession().setAttribute("currentUser", user);
                    if ( request.getSession().getAttribute("calledFromAnonymousReport") != null ) // if url pattern was called from anonymousReport.jsp view
                        response.sendRedirect("views/report/clientReport.jsp"); // it gets redirect to client report view with the new session for the user
                    else
                        response.sendRedirect("./index.jsp");
                }
                break;

            case "/signup":
                Usuario newUser;
                UsuarioDao myUser = new UsuarioDao();
                Direccion myDir;
                // we get data from html form
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
                                Short.parseShort( request.getParameter("numeroExterior") ),
                        rolID = Short.parseShort( request.getParameter("role") );

                myDir = new Direccion(codigoPostal, colonia, calle, referencias, numeroExterior, numeroInterior,
                        ciudad, municipio, estado);

                // Create new client, currentUser
                newUser = new Usuario(curp, rfc, nombre, apellidop, apellidom, rolID, correo, contrasenia, extension, telefono,
                        myDir);
                if ( !myUser.insert(newUser) ) {
                    request.setAttribute("userSignUpFail", "Error al registrar. Inténtelo de nuevo.");
                    request.getRequestDispatcher("views/user/signup.jsp").forward(request, response);
                } else {
                    // if no user/admin is logged in, we create its session. Otherwise just insertion on db is made
                    if ( request.getSession().getAttribute("currentUser") == null )
                        request.getSession().setAttribute("currentUser", newUser);
                    // if this url pattern was called from anonymous report view, it redirects the user to client report view
                    if ( request.getSession().getAttribute("calledFromAnonymousReport") != null && request.getSession().getAttribute("calledFromAnonymousReport").equals(true) )
                        response.sendRedirect("views/report/clientReport.jsp");
                    else
                        response.sendRedirect("./index.jsp");
                }

                break;

            case "/logout":
                request.getSession().invalidate();
                response.sendRedirect("./index.jsp");
                break;

        }

    }
}

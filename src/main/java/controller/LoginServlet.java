package controller;

import data.dao.AdministradorDao;
import data.dao.ClienteDao;
import model.Administrador;
import model.Cliente;
import model.Direccion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"/login", "/signup", "/logout", "/loginAdmin", "/signupAdmin"})
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
                    response.sendRedirect("views/user/loginFail.jsp");
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
                    request.getRequestDispatcher("views/user/signup.jsp").forward(request, response);
                } else {
                    request.getSession(true).setAttribute("currentUser", newClient);
                    response.sendRedirect("./index.jsp");
                }

                break;

            case "/logout":
                request.getSession().invalidate();
                response.sendRedirect("./index.jsp");
                break;

            case "/loginAdmin":
                // check if admin exists in database
                AdministradorDao myAdmin = new AdministradorDao();
                Administrador admin;
                email = request.getParameter("email");
                password = request.getParameter("password").hashCode();
                admin = myAdmin.find(email, password);
                // If not exists, throw login error
                if ( admin == null )
                    response.sendRedirect("views/admin/loginAdminFail.jsp");
                else {
                    // If exists, redirect the user to admin.jsp with new sessions created for admin
                    request.getSession().setAttribute("currentAdmin", admin);
                    response.sendRedirect("./views/admin/admin.jsp");
                }
                break;

            case "/signupAdmin":
                //Administrador admin;
                myAdmin = new AdministradorDao();
                Direccion dir;
                curp = request.getParameter("curp");
                rfc = request.getParameter("rfc");
                nombre = request.getParameter("nombre");
                apellidop = request.getParameter("apellidop");
                apellidom = request.getParameter("apellidom");
                correo = request.getParameter("correo");
                contrasenia = request.getParameter("contrasenia");
                extension = request.getParameter("extension");
                telefono = request.getParameter("telefono");
                codigoPostal = request.getParameter("codigoPostal");
                colonia = request.getParameter("colonia");
                calle = request.getParameter("calle");
                referencias = request.getParameter("ref");
                ciudad = request.getParameter("ciudad");
                municipio = request.getParameter("municipio");
                estado = request.getParameter("estado");
                numeroExterior = Short.parseShort( request.getParameter("numeroExterior") );
                numeroInterior = ( request.getParameter("numeroInterior").isEmpty() ) ? (short) 0 :
                                Short.parseShort( request.getParameter("numeroExterior") );

                dir = new Direccion(codigoPostal, colonia, calle, referencias, numeroExterior, numeroInterior,
                        ciudad, municipio, estado);

                // Create new client, currentUser
                admin = new Administrador(curp, rfc, nombre, apellidop, apellidom, correo, contrasenia, extension, telefono,
                        dir);
                if ( !myAdmin.insert(admin) ) {
                    request.getSession().setAttribute("userSignUpFail", "Error al registrar. Inténtelo de nuevo.");
                    request.getRequestDispatcher("views/admin/signupAdmin.jsp").forward(request, response);
                } else {
                    request.getSession(true).setAttribute("currentUser", admin);
                    response.sendRedirect("./views/admin/admin.jsp");
                }
                break;
        }

    }
}

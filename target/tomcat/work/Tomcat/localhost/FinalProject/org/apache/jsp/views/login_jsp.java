/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-05-23 20:16:45 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Inicio de sesión</title>\r\n");
      out.write("    <link rel=\"shortcut icon\" href=\"../images/veracruz.ico\" type=\"image/x-icon\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<h1 style=\"text-align: center\">Inicio de sesión</h1>\r\n");
      out.write("<fieldset style=\"width: 100px; height: auto; margin: 0 auto\">\r\n");
      out.write("    <form action=\"../login\" method=\"post\">\r\n");
      out.write("        <p>\r\n");
      out.write("            <label for=\"email\">Correo electrónico</label>\r\n");
      out.write("            <input type=\"text\" name=\"email\" id=\"email\" placeholder=\"example@email.com\" required title=\"Ingresa tu email\">\r\n");
      out.write("        </p>\r\n");
      out.write("        <p>\r\n");
      out.write("            <label for=\"password\">Contraseña</label>\r\n");
      out.write("            <input type=\"password\" name=\"password\" id=\"password\" required title=\"La contraseña debe contener al menos 8 caracteres\"\r\n");
      out.write("            minlength=\"8\">\r\n");
      out.write("        </p>\r\n");
      out.write("\r\n");
      out.write("        <input type=\"submit\" value=\"Enviar\">\r\n");
      out.write("        <p>\r\n");
      out.write("            <a href=\"./signup.jsp\">¿No tiene cuenta? ¡Registrese ahora!</a>\r\n");
      out.write("        </p>\r\n");
      out.write("    </form>\r\n");
      out.write("</fieldset>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

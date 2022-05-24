/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-05-24 04:02:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class signup_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
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
      out.write("    <title>Crear cuenta</title>\r\n");
      out.write("    <link rel=\"shortcut icon\" href=\"../../images/veracruz.ico\" type=\"image/x-icon\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../../static/css/styles.css\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div style=\"background-color: #dddddd; padding: 10px 10px 10px 10px\">\r\n");
      out.write("    <img src=\"../../images/logo-veracruz-1.png\" alt=\"Logo Veracruz\">\r\n");
      out.write("    <img src=\"../../images/logo-me-llena-de-orgullo.png\" alt=\"Veracruz me llena de orgullo\" class=\"toRight\">\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"topnav\">\r\n");
      out.write("    <a href=\"/FinalProject/index.jsp\" style=\"font-size: larger\">Inicio</a>\r\n");
      out.write("</div>\r\n");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<div style=\"text-align: center\">\r\n");
      out.write("    <form action=\"../../signup\" method=\"post\">\r\n");
      out.write("        <fieldset title=\"Ingrese sus datos\" class=\"wrapper\">\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"curp\"><abbr title=\"Clave Única de Registro de Población\">CURP</abbr></label><br>\r\n");
      out.write("                <input type=\"text\" name=\"curp\" id=\"curp\" minlength=\"18\" maxlength=\"18\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"rfc\"><abbr title=\"Registro Federal de Contribuyentes\">RFC</abbr> (Opcional)</label><br>\r\n");
      out.write("                <input type=\"text\" name=\"rfc\" id=\"rfc\" minlength=\"13\" maxlength=\"13\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"nombre\">Nombre(s)</label><br>\r\n");
      out.write("                <input type=\"text\" name=\"nombre\" id=\"nombre\" placeholder=\"Eduardo\" required onkeydown=\"return /[a-zA-Z ]/i.test(event.key)\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"apellidop\">Apellido Paterno</label><br>\r\n");
      out.write("                <input type=\"text\" name=\"apellidop\" id=\"apellidop\" placeholder=\"Ruiz\" required onkeydown=\"return /[a-zA-Z ]/i.test(event.key)\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"apellidom\">Apellido Materno</label><br>\r\n");
      out.write("                <input type=\"text\" name=\"apellidom\" id=\"apellidom\" placeholder=\"Rios\" required onkeydown=\"return /[a-zA-Z ]/i.test(event.key)\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"correo\">Correo electrónico</label><br>\r\n");
      out.write("                <input type=\"email\" name=\"correo\" id=\"correo\" placeholder=\"example@email.com\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"contrasenia\">Contraseña</label><br>\r\n");
      out.write("                <input type=\"password\" name=\"contrasenia\" id=\"contrasenia\" minlength=\"8\" required><br>\r\n");
      out.write("                <span style=\"color: red; font-size: small\">*Debe contener mínimo 8 caracteres</span>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"extension\">Extensión</label><br>\r\n");
      out.write("                <input type=\"number\" name=\"extension\" id=\"extension\" min=\"1\" minlength=\"3\" maxlength=\"3\" placeholder=\"229\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"telefono\">Teléfono/Celular</label><br>\r\n");
      out.write("                <input type=\"number\" name=\"telefono\" id=\"telefono\" min=\"1\" placeholder=\"1289177\" minlength=\"7\" maxlength=\"7\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"codigoPostal\">Código Postal</label><br>\r\n");
      out.write("                <input type=\"number\" name=\"codigoPostal\" id=\"codigoPostal\" placeholder=\"91777\" minlength=\"5\" maxlength=\"5\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"colonia\">Colonia</label><br>\r\n");
      out.write("                <input type=\"text\" name=\"colonia\" id=\"colonia\" placeholder=\"Geovillas del Puerto\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"calle\">Calle</label><br>\r\n");
      out.write("                <input type=\"text\" name=\"calle\" id=\"calle\" placeholder=\"Blvd Veracruz Norte\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"ref\">Referencias</label><br>\r\n");
      out.write("                <input type=\"text\" name=\"ref\" id=\"ref\" placeholder=\"Entre Nautla y Jamapa\" maxlength=\"100\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"numeroExterior\">Número exterior</label><br>\r\n");
      out.write("                <input type=\"number\" name=\"numeroExterior\" id=\"numeroExterior\" min=\"1\" placeholder=\"5\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"numeroInterior\">Número interior</label><br>\r\n");
      out.write("                <input type=\"number\" name=\"numeroInterior\" id=\"numeroInterior\" min=\"0\" placeholder=\"0\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"ciudad\">Ciudad</label><br>\r\n");
      out.write("                <input type=\"text\" name=\"ciudad\" id=\"ciudad\" placeholder=\"Veracruz\" onkeydown=\"return /[a-zA-Z ]/i.test(event.key)\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"municipio\">Municipio</label><br>\r\n");
      out.write("                <input type=\"text\" name=\"municipio\" id=\"municipio\" placeholder=\"Veracruz\" onkeydown=\"return /[a-zA-Z ]/i.test(event.key)\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div>\r\n");
      out.write("                <label for=\"estado\">Estado</label><br>\r\n");
      out.write("                <input type=\"text\" name=\"estado\" id=\"estado\" placeholder=\"Veracruz\" onkeydown=\"return /[a-zA-Z ]/i.test(event.key)\" required>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <input type=\"submit\" value=\"Registrarse\">\r\n");
      out.write("\r\n");
      out.write("        </fieldset>\r\n");
      out.write("    </form>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /views/user/signup.jsp(23,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(false);
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /views/user/signup.jsp(24,4) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue("${sessionScope.userSignUpFail}");
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }
}

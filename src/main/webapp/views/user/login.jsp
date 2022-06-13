<%--
  Author: Eduardo Ruiz Rios
  Date: 15/05/2022
  Time: 3:39 p. m.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Inicio de sesión</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/veracruz.ico" type="image/x-icon">
</head>
<body>
<h1 style="text-align: center">Inicio de sesión</h1>
<c:if test="${requestScope.userNotFound}">
    <div style="text-align: center">
        <span style="color: red; font-weight: bold; font-size: large">El correo o la contraseña son incorrectos.</span>
    </div>
</c:if>
<fieldset style="width: 100px; height: auto; margin: 0 auto">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <p>
            <label for="email">Correo electrónico</label>
            <input type="text" name="email" id="email" placeholder="example@email.com" onkeydown="return /[a-zA-Z_@0-9.-]/i.test(event.key)" required title="Ingresa tu email">
        </p>
        <p>
            <label for="password">Contraseña</label>
            <input type="password" name="password" id="password" required title="La contraseña debe contener al menos 8 caracteres"
                   onkeydown="return /[a-zA-Z0-9]/i.test(event.key)"  minlength="8">
        </p>

        <input type="submit" value="Enviar">
        <p>
            <a href="./signup.jsp">¿No tiene cuenta? ¡Registrese ahora!</a>
        </p>
    </form>
</fieldset>

</body>
</html>

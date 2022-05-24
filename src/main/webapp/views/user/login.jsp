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
    <link rel="shortcut icon" href="../../images/veracruz.ico" type="image/x-icon">
</head>
<body>
<h1 style="text-align: center">Inicio de sesión</h1>
<fieldset style="width: 100px; height: auto; margin: 0 auto">
    <form action="../../login" method="post">
        <p>
            <label for="email">Correo electrónico</label>
            <input type="text" name="email" id="email" placeholder="example@email.com" required title="Ingresa tu email">
        </p>
        <p>
            <label for="password">Contraseña</label>
            <input type="password" name="password" id="password" required title="La contraseña debe contener al menos 8 caracteres"
            minlength="8">
        </p>

        <input type="submit" value="Enviar">
        <p>
            <a href="./signup.jsp">¿No tiene cuenta? ¡Registrese ahora!</a>
        </p>
    </form>
</fieldset>

</body>
</html>

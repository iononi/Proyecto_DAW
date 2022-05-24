<%--
  Author: Eduardo Ruiz Rios
  Date: 16/05/2022
  Time: 4:32 p. m.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Crear cuenta</title>
    <link rel="shortcut icon" href="../../images/veracruz.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../static/css/styles.css" type="text/css">
</head>
<body>
<div style="background-color: #dddddd; padding: 10px 10px 10px 10px">
    <img src="../../images/logo-veracruz-1.png" alt="Logo Veracruz">
    <img src="../../images/logo-me-llena-de-orgullo.png" alt="Veracruz me llena de orgullo" class="toRight">
</div>

<div class="topnav">
    <a href="../../index.jsp" style="font-size: larger">Inicio</a>
</div>
<c:if test="${sessionScope.userSignUpFail != null}">
    <c:out value="${sessionScope.userSignUpFail}" />
</c:if>
<div style="text-align: center">
    <form action="../../signup" method="post">
        <fieldset title="Ingrese sus datos" class="wrapper">
            <div>
                <label for="curp"><abbr title="Clave Única de Registro de Población">CURP</abbr></label><br>
                <input type="text" name="curp" id="curp" minlength="18" maxlength="18" required>
            </div>

            <div>
                <label for="rfc"><abbr title="Registro Federal de Contribuyentes">RFC</abbr> (Opcional)</label><br>
                <input type="text" name="rfc" id="rfc" minlength="13" maxlength="13">
            </div>

            <div>
                <label for="nombre">Nombre(s)</label><br>
                <input type="text" name="nombre" id="nombre" placeholder="Eduardo" required onkeydown="return /[a-zA-Z ]/i.test(event.key)">
            </div>

            <div>
                <label for="apellidop">Apellido Paterno</label><br>
                <input type="text" name="apellidop" id="apellidop" placeholder="Ruiz" required onkeydown="return /[a-zA-Z ]/i.test(event.key)">
            </div>

            <div>
                <label for="apellidom">Apellido Materno</label><br>
                <input type="text" name="apellidom" id="apellidom" placeholder="Rios" required onkeydown="return /[a-zA-Z ]/i.test(event.key)">
            </div>

            <div>
                <label for="correo">Correo electrónico</label><br>
                <input type="email" name="correo" id="correo" placeholder="example@email.com" required>
            </div>

            <div>
                <label for="contrasenia">Contraseña</label><br>
                <input type="password" name="contrasenia" id="contrasenia" minlength="8" required><br>
                <span style="color: red; font-size: small">*Debe contener mínimo 8 caracteres</span>
            </div>

            <div>
                <label for="extension">Extensión</label><br>
                <input type="number" name="extension" id="extension" min="1" minlength="3" maxlength="3" placeholder="229" required>
            </div>

            <div>
                <label for="telefono">Teléfono/Celular</label><br>
                <input type="number" name="telefono" id="telefono" min="1" placeholder="1289177" minlength="7" maxlength="7">
            </div>

            <div>
                <label for="codigoPostal">Código Postal</label><br>
                <input type="number" name="codigoPostal" id="codigoPostal" placeholder="91777" minlength="5" maxlength="5" required>
            </div>

            <div>
                <label for="colonia">Colonia</label><br>
                <input type="text" name="colonia" id="colonia" placeholder="Geovillas del Puerto" required>
            </div>

            <div>
                <label for="calle">Calle</label><br>
                <input type="text" name="calle" id="calle" placeholder="Blvd Veracruz Norte" required>
            </div>

            <div>
                <label for="ref">Referencias</label><br>
                <input type="text" name="ref" id="ref" placeholder="Entre Nautla y Jamapa" maxlength="100">
            </div>

            <div>
                <label for="numeroExterior">Número exterior</label><br>
                <input type="number" name="numeroExterior" id="numeroExterior" min="1" placeholder="5" required>
            </div>

            <div>
                <label for="numeroInterior">Número interior</label><br>
                <input type="number" name="numeroInterior" id="numeroInterior" min="1" placeholder="0">
            </div>

            <div>
                <label for="ciudad">Ciudad</label><br>
                <input type="text" name="ciudad" id="ciudad" placeholder="Veracruz" onkeydown="return /[a-zA-Z ]/i.test(event.key)" required>
            </div>

            <div>
                <label for="municipio">Municipio</label><br>
                <input type="text" name="municipio" id="municipio" placeholder="Veracruz" onkeydown="return /[a-zA-Z ]/i.test(event.key)" required>
            </div>

            <div>
                <label for="estado">Estado</label><br>
                <input type="text" name="estado" id="estado" placeholder="Veracruz" onkeydown="return /[a-zA-Z ]/i.test(event.key)" required>
            </div>

            <input type="submit" value="Registrarse">

        </fieldset>
    </form>

</div>
</body>
</html>

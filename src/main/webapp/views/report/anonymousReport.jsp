<%--
  Author: Eduardo Ruiz Rios
  Date: 27/05/2022
  Time: 7:13 p. m.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html" charset="UTF-8">
    <link rel="shortcut icon" href="../../images/veracruz.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="../../static/css/styles.css">
    <title>¡Haz tu reporte!</title>
</head>
<body>
<c:set var="calledFromAnonymousReport" value="${true}" scope="session"/>
<div style="background-color: #dddddd; padding: 10px 10px 10px 10px">
    <img src="../../images/logo-veracruz-1.png" alt="Logo Veracruz">
    <img src="../../images/logo-me-llena-de-orgullo.png" alt="Veracruz me llena de orgullo" class="toRight">
</div>

<div class="topnav">
    <a href="../../index.jsp">Inicio</a>
    <c:if test="${empty sessionScope.currentUser}">
        <a href="../user/signup.jsp">Registrarse</a>
        <a href="../user/login.jsp">Iniciar sesión</a>
    </c:if>

    <c:if test="${sessionScope.userIsAdmin}">
        <a href="../user/signup.jsp">Registrar usuario</a>
        <a href="../admin/admin.jsp">Administrador</a>
    </c:if>

    <c:if test="${sessionScope.currentUser != null}">
        <a href="../user/profile.jsp">Mi Perfil</a>
    </c:if>
</div>

<div style="text-align: center">
    <c:if test="${requestScope.reportInsertFail}">
        <div style="text-align: center">
            <span style="color: red; font-size: large; font-weight: bold">Sucedió un error al registrar el reporte.</span>
        </div>
    </c:if>
    <form action="../../anonymousReport" method="post">
        <fieldset title="Ingrese sus datos" class="wrapper">

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
                <input type="number" name="numeroInterior" id="numeroInterior" min="0" placeholder="0">
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

            <div>
                <label for="tipoResiduo">Tipo de Residuo</label> <br>
                <select name="tipoResiduo" id="tipoResiduo">
                    <c:forEach var="wasteType" items="${sessionScope.wasteType}">
                        <option value="${wasteType.residuoId}">${wasteType.tipoResiduo}</option>
                    </c:forEach>
                </select>
            </div>

            <div>
                <label for="metodoPago">Metodo de pago</label> <br>
                <select name="metodoPago" id="metodoPago">
                    <c:forEach var="paymentMethod" items="${sessionScope.paymentMethod}">
                        <option value="${paymentMethod.metodoId}">${paymentMethod.metodoPago}</option>
                    </c:forEach>
                </select>
            </div>

            <div>
                <label for="status">Estado</label> <br>
                <select name="status" id="status">
                    <option value="1">Recibido</option>
                </select>
            </div>

            <input type="submit" value="Registrar reporte">

        </fieldset>
    </form>

</div>
</body>
</html>

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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/veracruz.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
    <title>¡Haz tu reporte!</title>
</head>
<body>
<div style="background-color: #dddddd; padding: 10px 10px 10px 10px">
    <img src="${pageContext.request.contextPath}/images/logo-veracruz-1.png" alt="Logo Veracruz">
    <img src="${pageContext.request.contextPath}/images/logo-me-llena-de-orgullo.png" alt="Veracruz me llena de orgullo" class="toRight">
</div>

<div class="topnav">
    <a href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
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
    <form action="${pageContext.request.contextPath}/clientReport" method="post">
        <fieldset title="Ingrese sus datos" class="wrapper">

            <div>
                <label for="usuarioId">ID</label> <br>
                <input type="number" name="usuarioId" id="usuarioId" value="${sessionScope.currentUser.clienteId}" readonly>
            </div>

            <div>
                <label for="nombre">Nombre(s)</label><br>
                <input type="text" name="nombre" id="nombre" value="${sessionScope.currentUser.nombre}" readonly>
            </div>

            <div>
                <label for="apellidop">Apellido Paterno</label><br>
                <input type="text" name="apellidop" id="apellidop" value="${sessionScope.currentUser.apellidop}" readonly>
            </div>

            <div>
                <label for="apellidom">Apellido Materno</label><br>
                <input type="text" name="apellidom" id="apellidom" value="${sessionScope.currentUser.apellidom}" readonly>
            </div>

            <div>
                <label for="correo">Correo electrónico</label><br>
                <input type="email" name="correo" id="correo" value="${sessionScope.currentUser.correo}" readonly>
            </div>

            <div>
                <label for="extension">Extensión</label><br>
                <input type="number" name="extension" id="extension" value="${sessionScope.currentUser.extension}" readonly>
            </div>

            <div>
                <label for="telefono">Teléfono/Celular</label><br>
                <input type="number" name="telefono" id="telefono" value="${sessionScope.currentUser.telefono}" readonly>
            </div>

            <div>
                <label for="codigoPostal">Código Postal</label><br>
                <input type="number" name="codigoPostal" id="codigoPostal" value="${sessionScope.currentUser.dir.codigoPostal}" readonly>
            </div>

            <div>
                <label for="colonia">Colonia</label><br>
                <input type="text" name="colonia" id="colonia" value="${sessionScope.currentUser.dir.colonia}" readonly>
            </div>

            <div>
                <label for="calle">Calle</label><br>
                <input type="text" name="calle" id="calle" value="${sessionScope.currentUser.dir.calle}" readonly>
            </div>

            <div>
                <label for="ref">Referencias</label><br>
                <input type="text" name="ref" id="ref" value="${sessionScope.currentUser.dir.referencias}" readonly>
            </div>

            <div>
                <label for="numeroExterior">Número exterior</label><br>
                <input type="number" name="numeroExterior" id="numeroExterior" value="${sessionScope.currentUser.dir.numeroExterior}" readonly>
            </div>

            <div>
                <label for="numeroInterior">Número interior</label><br>
                <input type="number" name="numeroInterior" id="numeroInterior" value="${sessionScope.currentUser.dir.numeroInterior}" readonly>
            </div>

            <div>
                <label for="ciudad">Ciudad</label><br>
                <input type="text" name="ciudad" id="ciudad" value="${sessionScope.currentUser.dir.ciudad}" readonly>
            </div>

            <div>
                <label for="municipio">Municipio</label><br>
                <input type="text" name="municipio" id="municipio" value="${sessionScope.currentUser.dir.municipio}" readonly>
            </div>

            <div>
                <label for="estado">Estado</label><br>
                <input type="text" name="estado" id="estado" value="${sessionScope.currentUser.dir.estado}" readonly>
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

            <div>
                <input type="submit" value="Registrar reporte">
            </div>


        </fieldset>
    </form>

</div>
</body>
</html>
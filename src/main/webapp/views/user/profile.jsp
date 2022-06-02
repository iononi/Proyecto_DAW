<%--
  Author: Eduardo Ruiz Rios
  Date: 19/05/2022
  Time: 3:18 p. m.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html" charset="UTF-8">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/veracruz.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
    <title>Mi Perfil</title>
</head>
<body>
<div style="background-color: #dddddd; padding: 10px 10px 10px 10px; width: auto">
    <img src="${pageContext.request.contextPath}/images/logo-veracruz-1.png" alt="Logo Veracruz">
    <img src="${pageContext.request.contextPath}/images/logo-me-llena-de-orgullo.png" alt="Veracruz me llena de orgullo"
         class="toRight">
</div>

<div class="topnav">
    <a href="../../index.jsp">Inicio</a>
    <a href="${pageContext.request.contextPath}/clientReportRequest">Haz tu reporte</a>
    <c:if test="${empty sessionScope.currentUser}">
        <a href="../user/signup.jsp">Registrarse</a>
        <a href="../user/login.jsp">Iniciar sesión</a>
    </c:if>

    <c:if test="${sessionScope.userIsAdmin}">
        <a href="../user/signup.jsp">Registrar usuario</a>
        <a href="../admin/admin.jsp">Administrador</a>
    </c:if>

</div>
<br>

<c:if test="${sessionScope.reportInsert and sessionScope.folio > 0}">
    <div class="popup open-popup" id="pop-up">
        <img src="${pageContext.request.contextPath}/images/404-tick.png" alt="successful insertion">
        <h2>¡Muchas gracias!</h2>
        <p>Tu reporte ha sido registrado con el folio <c:out value="${sessionScope.folio}"/>.</p>
        <script src="${pageContext.request.contextPath}/static/js/utilities.js"></script>
        <button type="button" onclick="closePopup(); <c:remove var="folio" scope="session"/> <c:remove var="reportInsert" scope="session"/> ">OK</button>
    </div>
</c:if>
<c:if test="${empty sessionScope.userReportList and sessionScope.showPopupMessage}">
    <div class="popup open-popup" id="pop-up">
        <img src="${pageContext.request.contextPath}/images/404-oops.png" alt="something went wrong">
        <h2>¡Oops!</h2>
        <p>Al parecer no existe ningún reporte con el estado especificado.</p>
        <script src="${pageContext.request.contextPath}/static/js/utilities.js"></script>
        <button type="button" onclick="closePopup(); <c:remove var="folio" scope="session"/> <c:remove var="reportInsert" scope="session"/> ">OK</button>
    </div>
</c:if>
<div style="float: right;">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Cerrar sesión</button>
    </form>
</div>
<br>

<div>
    <fieldset style="width: fit-content">
        <legend>Buscar por:</legend>
        <form action="${pageContext.request.contextPath}/searchByStatus" title="La búsqueda por folio tiene preferencia sobre la búsqueda por estado. Si desea buscar
        reportes por su estado, el campo de folio debe permanecer vacío." method="post">
            <p>
                <label for="folio">Folio</label>
                <input type="number" name="folio" id="folio" min="1">
            </p>

            <p>
                <label for="status">Estado</label>
                <select name="status" id="status">
                    <option value="1">Recibido</option>
                    <option value="2" selected>En proceso</option>
                    <option value="3">Finalizado</option>
                </select>
            </p>

            <button type="submit">Buscar</button>
        </form>
    </fieldset>
</div>
<br>

<c:if test="${sessionScope.userReportList != null}">
    <div>
        <table>
            <tr>
                <th>Folio</th>
                <th>Nombre</th>
                <th>Apellido Paterno</th>
                <th>Apellido Materno</th>
                <th>Correo</th>
                <th>Teléfono</th>
                <th>Dirección</th>
                <th>Residuo</th>
                <th>Método de Pago</th>
                <th>Pagado</th>
                <th>Estado</th>
            </tr>
            <c:forEach var="userReport" items="${sessionScope.userReportList}">
                <tr>
                    <td>${userReport.folio}</td>
                    <td>${userReport.nombre}</td>
                    <td>${userReport.apellidop}</td>
                    <td>${userReport.apellidom}</td>
                    <td>${userReport.correo}</td>
                    <td>(${userReport.extension}) ${userReport.telefono}</td>
                    <td>${userReport.direccion}</td>
                    <td>${userReport.tipoResiduo}</td>
                    <td>${userReport.metodoPago}</td>
                    <c:if test="${userReport.pagado eq 'true'}">
                        <td>Sí</td>
                    </c:if>
                    <c:if test="${userReport.pagado eq 'false'}">
                        <td>No</td>
                    </c:if>
                    <td>${userReport.estado}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>

<c:if test="">
    <div class="popup">

    </div>
</c:if>

<c:remove var="userReportList" scope="session"/>
<c:remove var="showPopupMessage" scope="session"/>
</body>
</html>

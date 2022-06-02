<%--
  Author: Eduardo Ruiz Rios
  Date: 01/06/2022
  Time: 11:07 p. m.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html" charset="UTF-8">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/veracruz.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
    <title>Consulta tus reportes</title>
</head>
<body>
<div style="background-color: #dddddd; padding: 10px 10px 10px 10px; width: auto">
    <img src="${pageContext.request.contextPath}/images/logo-veracruz-1.png" alt="Logo Veracruz">
    <img src="${pageContext.request.contextPath}/images/logo-me-llena-de-orgullo.png" alt="Veracruz me llena de orgullo"
         class="toRight">
</div>

<div class="topnav">
    <a href="../../index.jsp">Inicio</a>
    <a href="${pageContext.request.contextPath}/anonymousReportRequest">Haz tu reporte</a>
</div>

<div>
    <fieldset style="width: fit-content">
        <legend>Buscar por:</legend>
        <form action="${pageContext.request.contextPath}/anonymousQuery" method="post">
            <p>
                <label for="folio">Folio</label>
                <input type="number" name="folio" id="folio" min="1">
            </p>

            <div style="text-align: center">
            <button type="submit">Buscar</button>
            </div>
        </form>
    </fieldset>
</div>
<br>

<c:if test="${empty sessionScope.anonymousReportList and sessionScope.showPopupMessage}">
    <div class="popup open-popup" id="pop-up">
        <img src="${pageContext.request.contextPath}/images/404-oops.png" alt="something went wrong">
        <h2>¡Oops!</h2>
        <p>${sessionScope.popupMessage}</p>
        <script src="${pageContext.request.contextPath}/static/js/utilities.js"></script>
        <button type="button" onclick="closePopup(); <c:remove var="showPopupMessage" scope="session"/> <c:remove var="popupMessage" scope="session"/> ">OK</button>
    </div>
</c:if>

<%-- Print table if report exists --%>
<c:if test="${not empty sessionScope.anonymousReportList}">
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
            <c:forEach var="anonymousReport" items="${sessionScope.anonymousReportList}">
                <tr>
                    <td>${anonymousReport.folio}</td>
                    <td>${anonymousReport.nombre}</td>
                    <td>${anonymousReport.apellidop}</td>
                    <td>${anonymousReport.apellidom}</td>
                    <td>${anonymousReport.correo}</td>
                    <td>(${anonymousReport.extension}) ${anonymousReport.telefono}</td>
                    <td>${anonymousReport.direccion}</td>
                    <td>${anonymousReport.tipoResiduo}</td>
                    <td>${anonymousReport.metodoPago}</td>
                    <c:if test="${anonymousReport.pagado eq 'true'}">
                        <td>Sí</td>
                    </c:if>
                    <c:if test="${anonymousReport.pagado eq 'false'}">
                        <td>No</td>
                    </c:if>
                    <td>${anonymousReport.estado}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>

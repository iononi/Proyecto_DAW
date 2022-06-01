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
    <title>Profile test</title>
</head>
<body>
<div style="background-color: #dddddd; padding: 10px 10px 10px 10px">
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


<c:if test="${sessionScope.reportInsert and sessionScope.folio > 0}">
    <div class="popup open-popup" id="pop-up">
        <img src="${pageContext.request.contextPath}/images/404-tick.png" alt="successful insertion">
        <h2>¡Muchas gracias!</h2>
        <p>Tu reporte ha sido registrado con el folio <c:out value="${sessionScope.folio}"/>.</p>
        <script src="${pageContext.request.contextPath}/static/js/utilities.js"></script>
        <button type="button" onclick="closePopup(); <c:remove var="folio" scope="session"/> <c:remove var="reportInsert" scope="session"/> ">OK</button>
    </div>
</c:if>
<div>
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Log out</button>
    </form>
</div>

</body>
</html>

<%--
  Author: Eduardo Ruiz Rios
  Date: 20/05/2022
  Time: 2:39 p. m.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value=
        "La búsqueda por folio tiene preferencia sobre la búsqueda por ID o estado.
Para realizar la búsqueda por ID o estado asegúrese que el campo folio esté vacío." scope="page"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" >
    <meta content="text/html" charset="UTF-8">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/veracruz.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
    <title>Mi Administrador</title>
</head>
<body>
<div style="background-color: #dddddd; padding: 10px 10px 10px 10px">
        <img src="${pageContext.request.contextPath}/images/logo-veracruz-1.png" alt="Logo Veracruz">
        <img src="${pageContext.request.contextPath}/images/logo-me-llena-de-orgullo.png" alt="Veracruz me llena de orgullo" class="toRight">
</div>

<div class="topnav">
    <a href="${pageContext.request.contextPath}/index.jsp" style="font-size: larger">Inicio</a>
    <a href="${pageContext.request.contextPath}/views/user/signup.jsp">Registrar Usuario</a>
</div>
<p>
<div style="float: right">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Cerrar sesión</button>
    </form>
</div>
<br>
<p>
    <h2>Reportes de clientes</h2>
<p>
<p>
    <div>
    <fieldset style="width: 200px">
        <legend>Buscar por:</legend>
        <form action="${pageContext.request.contextPath}/adminQueryUser" title="${pageScope.title}" method="get">
            <p>
                <label for="folio">Folio</label>
                <input type="number" name="folio" id="folio" min="1" class="small right">
            </p>
            <p>
                <label for="userid">ID Usuario</label>
                <input type="number" name="userid" id="userid" min="1" class="right small">
            </p>
            <p>
                <label for="status">Estado</label>
                <select name="status" id="status" class="right">
                    <option value="1">Recibido</option>
                    <option value="2" selected>En proceso</option>
                    <option value="3">Finalizado</option>
                </select>
            </p>
            <div class="left">
                <button type="submit">Buscar</button>
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/selectAllUser" method="get">
            <div>
                <button type="submit" class="right">Mostrar todos</button>
            </div>
        </form>
    </fieldset>
    <br>
</div>
<c:if test="${empty sessionScope.userReport and sessionScope.showPopupMessage}">
    <div class="popup open-popup" id="pop-up">
        <img src="${pageContext.request.contextPath}/images/404-oops.png" alt="oops">
        <h2>¡Oops!</h2>
        <p>${sessionScope.popUpMessage} Inténtelo de nuevo.</p>
        <script src="${pageContext.request.contextPath}/static/js/utilities.js"></script>
        <button type="button" onclick="closePopup(); <c:remove var="showPopupMessage" scope="session"/> <c:remove var="popUpMessage" scope="session"/> ">OK</button>
    </div>
</c:if>
<div>
    <c:if test="${not empty sessionScope.userReport}">
        <table style="margin: auto">
            <tr>
                <th>Folio</th>
                <th>ID Cliente</th>
                <th>ID Residuo</th>
                <th>ID Metodo de Pago</th>
                <th>Pagado</th>
                <th>Estado</th>
            </tr>
            <c:forEach var="userReport" items="${sessionScope.userReport}">
                <tr>
                    <td>${userReport.folio}</td>
                    <td>${userReport.fkCliente}</td>
                    <td>${userReport.fkTipoResiduo}</td>
                    <td>${userReport.fkMetodoPago}</td>
                    <td>
                        <select name="pagado">
                            <option value="true" <c:if test="${userReport.pagado eq 'true'}">selected</c:if>>Si</option>
                            <option value="false" <c:if test="${userReport.pagado eq 'false'}">selected</c:if> >No</option>
                        </select>
                    </td>
                    <td>
                        <select name="status">
                            <option value="1" <c:if test="${userReport.fkEstado eq 1}">selected</c:if> >Recibido</option>
                            <option value="2" <c:if test="${userReport.fkEstado eq 2}">selected</c:if> >En proceso</option>
                            <option value="3" <c:if test="${userReport.fkEstado eq 3}">selected</c:if> >Finalizado</option>
                        </select>
                    </td>
                    <td>
                        <a href="#">
                            <button type="button" value="${userReport.folio}">Modificar</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<p>
<p>
<h2>Reportes anónimos</h2>
<p>
<div>
    <fieldset style="width: 200px">
        <legend>Buscar por:</legend>
        <form action="${pageContext.request.contextPath}/adminQueryAnonymous" title="${pageScope.title}" method="get">
            <p>
                <label for="id">Folio</label>
                <input type="number" name="reportid" id="id" min="1" class="small right">
            </p>
            <p>
                <label for="estado">Estado</label>
                <select name="status" id="estado" class="right">
                    <option value="1">Recibido</option>
                    <option value="2" selected>En proceso</option>
                    <option value="3">Finalizado</option>
                </select>
            </p>
            <div class="left">
                <button type="submit">Buscar</button>
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/selectAllAnonymous" method="get">
            <div>
                <button type="submit" class="right">Mostrar todos</button>
            </div>
        </form>
    </fieldset>
    <br>
</div>
<div>
    <c:if test="${not empty sessionScope.anonymousReport}">
        <table>
            <tr>
                <th>Folio</th>
                <th>Nombre</th>
                <th>Apellido Paterno</th>
                <th>Apellido Materno</th>
                <th>Correo</th>
                <th>Extensión</th>
                <th>Teléfono</th>
                <th>Dirección</th>
                <th>ID Residuo</th>
                <th>ID Método Pago</th>
                <th>Pagado</th>
                <th>ID Estado</th>
            </tr>
            <c:forEach var="anonymousReport" items="${sessionScope.anonymousReport}">
                <tr>
                    <td>${anonymousReport.folio}</td>
                    <td>${anonymousReport.nombre}</td>
                    <td>${anonymousReport.apellidop}</td>
                    <td>${anonymousReport.apellidom}</td>
                    <td>${anonymousReport.correo}</td>
                    <td>${anonymousReport.extension}</td>
                    <td>${anonymousReport.telefono}</td>
                    <td><c:out value="${anonymousReport.dir.calle}, ${anonymousReport.dir.colonia},
                    ${anonymousReport.dir.codigoPostal}, ${anonymousReport.dir.municipio},
                    ${anonymousReport.dir.estado}"/></td>
                    <td>${anonymousReport.fkTipoResiduo}</td>
                    <td>${anonymousReport.fkMetodoPago}</td>
                    <td>
                        <select name="paid">
                            <option value="true" <c:if test="${anonymousReport.pagado eq 'true'}">selected</c:if> >Si</option>
                            <option value="false" <c:if test="${anonymousReport.pagado eq 'false'}">selected</c:if> >No</option>
                        </select>
                    </td>
                    <td>
                        <select name="estado">
                            <option value="1" <c:if test="${anonymousReport.fk_estado eq 1}">selected</c:if> >Recibido</option>
                            <option value="2" <c:if test="${anonymousReport.fk_estado eq 2}">selected</c:if> >En proceso</option>
                            <option value="3"  <c:if test="${anonymousReport.fk_estado eq 3}">selected</c:if> >Finalizado</option>
                        </select>
                    </td>
                    <td>
                        <a href="#">
                            <button type="button" value="${anonymousReport.folio}">Modificar</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<div>

</div>
<c:remove var="userReport" scope="session"/>
<c:remove var="anonymousReport" scope="session"/>
</body>
</html>

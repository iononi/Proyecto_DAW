<%--
  Author: Eduardo Ruiz Rios
  Date: 20/05/2022
  Time: 2:39 p. m.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %> <%-- set isELIgnored=true to break routes --%>
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
    <c:out value="Context path: ${pageContext.request.contextPath}" />
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
<%-- Error/Success pop up message --%>
<c:if test="${sessionScope.showPopupMessage}">
    <div class="popup open-popup" id="pop-up">
        <img src="${pageContext.request.contextPath}/images/${sessionScope.statusImage}" alt="${sessionScope.alt}">
        <h2>${sessionScope.title}</h2>
        <p>${sessionScope.popUpMessage}</p>
        <script src="${pageContext.request.contextPath}/static/js/utilities.js"></script>
        <button type="button" onclick="closePopup(); <c:remove var="showPopupMessage" scope="session"/> <c:remove var="popUpMessage" scope="session"/> <c:remove var="statusImage" scope="session"/> <c:remove var="popUpMessage" scope="session"/> <c:remove var="alt" scope="session"/> <c:remove var="title" scope="session"/> <c:remove var="showPopupMessage" scope="session"/> <c:remove var="userReport" scope="session"/>">OK</button>
    </div>
</c:if>
<div>
    <c:if test="${not empty sessionScope.userReport}">
        <table style="margin: auto">
            <tr>
                <th>Folio</th>
                <th>ID Cliente</th>
                <th>ID Residuo</th>
                <th>Metodo de Pago</th>
                <th>Pagado</th>
                <th>Estado</th>
            </tr>
            <c:forEach var="userReport" items="${sessionScope.userReport}">
                <tr>
                    <form action="${pageContext.request.contextPath}/editClientReport" method="get">
                        <td>${userReport.folio}</td>
                        <td>${userReport.fkCliente}</td>
                        <td>
                            <c:if test="${userReport.fkTipoResiduo eq 1}" ><p>Construccion/Demolicion</p></c:if>
                        </td>
                        <td>
                            <c:if test="${userReport.fkMetodoPago eq 1}" ><p>Tarjeta Credito</p></c:if>
                            <c:if test="${userReport.fkMetodoPago eq 2}" ><p>Tarjeta Debito</p></c:if>
                            <c:if test="${userReport.fkMetodoPago eq 3}" ><p>Efectivo</p></c:if>
                        </td>
                        <td>
                            <label for="pagado" hidden></label>
                            <select name="pagado" id="pagado">
                                <option value="true" <c:if test="${userReport.pagado eq 'true'}">selected</c:if>>Si</option>
                                <option value="false" <c:if test="${userReport.pagado eq 'false'}">selected</c:if> >No</option>
                            </select>
                        </td>
                        <td>
                            <label for="reportStatus" hidden></label>
                            <select name="reportStatus" id="reportStatus">
                                <option value="1" <c:if test="${userReport.fkEstado eq 1}">selected</c:if> >Recibido</option>
                                <option value="2" <c:if test="${userReport.fkEstado eq 2}">selected</c:if> >En proceso</option>
                                <option value="3" <c:if test="${userReport.fkEstado eq 3}">selected</c:if> >Finalizado</option>
                            </select>
                        </td>
                        <td>
                            <button type="submit" name="folioBtn" value="${userReport.folio}">Modificar</button>
                        </td>
                    </form>
                    <form action="${pageContext.request.contextPath}/deleteReport" method="get">
                        <td>
                            <button type="submit" name="btnFolio" value="${userReport.folio}">Eliminar</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<p>
<hr>
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
                <label for="estatus">Estado</label>
                <select name="estatus" id="estatus" class="right">
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
                <th>Método Pago</th>
                <th>Pagado</th>
                <th>Estado</th>
            </tr>
            <c:forEach var="anonymousReport" items="${sessionScope.anonymousReport}">
                <tr>
                    <form action="${pageContext.request.contextPath}/editAnonymousReport" method="get">
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
                        <td>
                            <c:if test="${anonymousReport.fkTipoResiduo eq 1}" ><p>Construccion/Demolicion</p></c:if>
                        </td>
                        <td>${anonymousReport.fkMetodoPago}</td>
                        <td>
                            <label for="paid" hidden></label>
                            <select name="paid" id="paid">
                                <option value="true" <c:if test="${anonymousReport.pagado eq 'true'}">selected</c:if> >Si</option>
                                <option value="false" <c:if test="${anonymousReport.pagado eq 'false'}">selected</c:if> >No</option>
                            </select>
                        </td>
                        <td>
                            <label for="estado" hidden></label>
                            <select name="estado" id="estado">
                                <option value="1" <c:if test="${anonymousReport.fk_estado eq 1}">selected</c:if> >Recibido</option>
                                <option value="2" <c:if test="${anonymousReport.fk_estado eq 2}">selected</c:if> >En proceso</option>
                                <option value="3"  <c:if test="${anonymousReport.fk_estado eq 3}">selected</c:if> >Finalizado</option>
                            </select>
                        </td>
                        <td>
                            <button type="submit" name="btnFolio" value="${anonymousReport.folio}">Modificar</button>
                        </td>
                    </form>
                    <form action="${pageContext.request.contextPath}/deleteAnonymous" method="get">
                        <td>
                            <button type="submit" name="btnFolio" value="${anonymousReport.folio}">Eliminar</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<div>

</div>
<%--<c:remove var="userReport" scope="session"/>--%> <%-- Ejemplo de error al eliminar variables --%>
<%--<c:remove var="anonymousReport" scope="session"/>--%>
</body>
</html>

<%--
  Author: Eduardo Ruiz Rios
  Date: 20/05/2022
  Time: 2:39 p. m.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<div>
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
                <c:choose>
                    <c:when test="${userReport.pagado eq 'true'}">
                        <td>
                            <select name="" id="">
                                <option value="true" selected>Si</option>
                                <option value="false">No</option>
                            </select>
                        </td>
                    </c:when>
                    <c:when test="${userReport.pagado eq 'false'}">
                        <td>
                            <select name="" id="">
                                <option value="true">Si</option>
                                <option value="false" selected>No</option>
                            </select>
                        </td>
                    </c:when>
                </c:choose>
                <td>
                    <c:choose>
                        <c:when test="${userReport.fkEstado eq 1}">
                            <select name="" id="">
                                <option value="1" selected>Recibido</option>
                                <option value="2">En proceso</option>
                                <option value="3">Finalizado</option>
                            </select>
                        </c:when>
                        <c:when test="${userReport.fkEstado eq 2}">
                            <select name="" id="">
                                <option value="1">Recibido</option>
                                <option value="2" selected>En proceso</option>
                                <option value="3">Finalizado</option>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select name="" id="">
                                <option value="1">Recibido</option>
                                <option value="2">En proceso</option>
                                <option value="3" selected>Finalizado</option>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="#">
                        <button value="${userReport.folio}">Modificar</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<p>
<div>
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
                <c:choose>
                    <c:when test="${anonymousReport.pagado eq 'true'}">
                        <td>
                            <select name="" id="">
                                <option value="true" selected>Si</option>
                                <option value="false">No</option>
                            </select>
                        </td>
                    </c:when>
                    <c:when test="${anonymousReport.pagado eq 'false'}">
                        <td>
                            <select name="" id="">
                                <option value="true">Si</option>
                                <option value="false" selected>No</option>
                            </select>
                        </td>
                    </c:when>
                </c:choose>
                <td>
                    <c:choose>
                        <c:when test="${anonymousReport.fk_estado eq 1}">
                            <select name="" id="">
                                <option value="1" selected>Recibido</option>
                                <option value="2">En proceso</option>
                                <option value="3">Finalizado</option>
                            </select>
                        </c:when>
                        <c:when test="${anonymousReport.fk_estado eq 2}">
                            <select name="" id="">
                                <option value="1">Recibido</option>
                                <option value="2" selected>En proceso</option>
                                <option value="3">Finalizado</option>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select name="" id="">
                                <option value="1">Recibido</option>
                                <option value="2">En proceso</option>
                                <option value="3" selected>Finalizado</option>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="#">
                        <button value="${anonymousReport.folio}">Modificar</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>



<div>

</div>

</body>
</html>

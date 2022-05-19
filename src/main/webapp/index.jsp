<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html" charset="UTF-8">
    <link rel="shortcut icon" href="images/veracruz.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
    <title>Programa de recolección</title>
</head>
<body class="main">
<div style="background-color: #dddddd; padding: 10px 10px 10px 10px">
    <img src="images/logo-veracruz-1.png" alt="Logo Veracruz">
    <img src="images/logo-me-llena-de-orgullo.png" alt="Veracruz me llena de orgullo" class="toRight">
</div>

<div class="topnav">
    <a href="/FinalProject/index.jsp">Inicio</a>
    <a href="views/signup.jsp">Registrarse</a>
    <c:if test="${sessionScope.currentUser != null}">
        <a href="views/profile.jsp">Mi Perfil</a>
    </c:if>
</div>

<br>
<c:if test="${sessionScope.currentUser != null}">
    <span>¡Bienvenido<c:out value=", ${sessionScope.currentUser.nombre}"/>!</span>
</c:if>
<br>

<h1 class="center">Importancia de la recolección de residuos</h1>
<div class="center">
    <pre style="justify-content: flex-start; font-family: 'Calisto MT', serif; font-size: large">
    En México, la generación de residuos sólidos urbanos alcanzó 53.1 millones de toneladas anuales y actualmente las
    cifras continúan al alza, de acuerdo a la cifra más reciente publicada en 2015 en el Informe de la situación del
    medio ambiente en México.

    La <abbr title="Secretaría de Medio Ambiente y Recursos Naturales">SEMARNAT</abbr> promueve a nivel federal la gestión integral de los residuos, iniciando con la generación hasta la
    disposición final pasando por las fases o etapas intermedias de recolección, transporte, acopio transferencia,
    aprovechamiento, tratamiento y disposición final a través de planes, programas y marco regulatorio, complementado con
    estrategias de educación, capacitación, comunicación y fortalecimiento del marco jurídico y administrativo, entre otras.

    En este marco, la Secretaría de Sustentabilidad (SS) de la UANL a través de la Dirección de Gestión Ambiental y
    Seguridad Operativa (DGASO), lleva a cabo la implementación de un plan de manejo para los diferentes tipos de
    residuos que se generan en los campus de la Universidad Autónoma de Nuevo León (UANL), con el objetivo de eliminar
    y/o minimizar los impactos provocados por un manejo inadecuado de los mismos, cumpliendo así con las regulaciones
    ambientales vigentes y fomentando la cultura del cuidado al ambiente entre la comunidad universitaria.
    </pre>
</div>

<br>

<div style="text-align: center">
    <iframe width="560" height="315" src="https://www.youtube.com/embed/TV-YEQOIFuQ" title="YouTube video player" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div>
<br><br>
<div style="text-align: center">
    <a href="views/login.jsp">
        <button style="width: 100px; height: 50px">¡Solicita tu servicio!</button>
    </a>
</div>


</body>
</html>

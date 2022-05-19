<%--
  Author: Eduardo Ruiz Rios
  Date: 16/05/2022
  Time: 4:32 p. m.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Crear cuenta</title>
    <link rel="shortcut icon" href="../images/veracruz.ico" type="image/x-icon">
    <link rel="stylesheet" href="../static/css/styles.css" type="text/css">
</head>
<body>
<div class="topnav">
    <a href="/FinalProject/index.jsp">Inicio</a>
</div>
<div style="text-align: center">
    <form action="../signup" method="post">
        <fieldset title="Ingrese sus datos" class="wrapper">
            <div>
                <label for="curp"><abbr title="Clave Única de Registro de Población">CURP</abbr></label><br>
                <input type="text" name="cupr" id="curp" minlength="18" maxlength="18" required>
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
                <label for="contraseña">Contraseña</label><br>
                <input type="password" name="contraseña" id="contraseña" minlength="8" required><br>
                <span style="color: red; font-size: small">*Debe contener mínimo 8 caracteres</span>
            </div>

            <div>
                <label for="extension">Extensión</label><br>
                <input type="number" name="extension" id="extension" minlength="3" maxlength="3" placeholder="229" required>
            </div>

            <div>
                <label for="telefono">Teléfono/Celular</label><br>
                <input type="number" name="telefono" id="telefono" placeholder="1289177" minlength="7" maxlength="7">
            </div>

            <input type="submit" value="Registrarse">
        
        </fieldset>
    </form>
    
</div>
</body>
</html>

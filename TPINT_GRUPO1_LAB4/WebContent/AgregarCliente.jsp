<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Pais" %>
<%@ page import="negocio.PaisNeg" %>
<%@ page import="nogocioImpl.PaisNegImpl" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registrar Cliente</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Registro de Cliente</h2>
        <form action="servletAgregarCliente" method="POST">
            <div class="form-group">
                <label for="dni">DNI:</label>
                <input type="text" class="form-control" id="dni" name="dni" pattern="\d{8}" title="Ingrese 8 dígitos" required>
            </div>
            <div class="form-group">
                <label for="cuil">CUIL:</label>
                <input type="text" class="form-control" id="cuil" name="cuil" pattern="\d{11}" title="Ingrese 11 dígitos" required>
            </div>
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" class="form-control" id="apellido" name="apellido" required>
            </div>
            <div class="form-group">
                <label for="sexo">Sexo:</label>
                <select class="form-control" id="sexo" name="sexo" required>
                    <option value="">Seleccionar</option>
                    <option value="Masculino">Masculino</option>
                    <option value="Femenino">Femenino</option>
                </select>
            </div>
            <div class="form-group">
                <label for="usuario">Usuario:</label>
                <input type="text" class="form-control" id="usuario" name="usuario" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            
            <div class="form-group">
                <label for="pais">País:</label>
                    <select class="form-control" id="pais" name="pais" required>
                    <option value="">Seleccionar</option>
                   <%
                     // Obtener la lista de países desde la base de datos
                           PaisNegImpl paisNeg = new PaisNegImpl();
                           List<Pais> paises = paisNeg.listarPaises();
                           if (paises != null && !paises.isEmpty()) {
                             for (Pais pais : paises) {
                    %>
                              <option value="<%= pais.getId() %>"><%= pais.getNombre() %></option>
                      <%
                           }
                           } else {
                      %>
                                 <option value="">No hay países disponibles</option>
                       <%
                              }
                         %>

                </select>
            </div>
            <div class="form-group">
                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
            </div>
            <div class="form-group">
                <label for="correoElectronico">Correo Electrónico:</label>
                <input type="email" class="form-control" id="correoElectronico" name="correoElectronico" required>
            </div>
            <div class="form-group">
                <label for="telefono">Teléfono:</label>
                <input type="tel" class="form-control" id="telefono" name="telefono" pattern="\d{5}" title="Ingrese entre 10 y 15 dígitos" required>
            </div>
            <div class="form-group">
                <label for="celular">Celular:</label>
                <input type="tel" class="form-control" id="celular" name="celular" pattern="\d{5}" title="Ingrese entre 10 y 15 dígitos" required>
            </div>
          
            <br><br>
            <button type="submit" class="btn btn-primary btn-block">Registrar Cliente</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

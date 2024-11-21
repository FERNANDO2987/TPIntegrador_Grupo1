
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente" %>
<%@ page import="datos.ClienteDao" %>
<%@ page import="datosImpl.ClienteDaoImpl" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Modificar Cliente</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Modificar Cliente</h2>

           <%
            // Obtener el ID del cliente desde la solicitud
            int idCliente = Integer.parseInt(request.getParameter("id"));
            ClienteDao clienteDao = new ClienteDaoImpl();
            
            Cliente cliente = clienteDao.obtenerClientes().stream()
                    .filter(c -> c.getId() == idCliente)
                    .findFirst()
                    .orElse(null);

            if (cliente != null) {
        %>
		    <div class="container mt-5">
		        <h2 class="text-center mb-4">Modificar Cliente</h2>
		
		            <form action="servletModificarCliente" method="post">
		                <input type="hidden" name="id" value="<%= cliente.getId() %>"> <!-- Campo oculto para el ID -->
		
		                <div class="form-group">
		                    <label for="txtDni">DNI:</label>
		                    <input type="text" class="form-control" id="dni" name="dni" value="<%= cliente.getDni() %>" required>
		                </div>
		                <div class="form-group">
		                    <label for="txtCuil">CUIL:</label>
		                    <input type="text" class="form-control" id="cuil" name="cuil" value="<%= cliente.getCuil() %>" required>
		                </div>
		                <div class="form-group">
		                    <label for="txtNombre">Nombre:</label>
		                    <input type="text" class="form-control" id="nombre" name="nombre" value="<%= cliente.getNombre() %>" required>
		                </div>
		                <div class="form-group">
		                    <label for="txtApellido">Apellido:</label>
		                    <input type="text" class="form-control" id="apellido" name="apellido" value="<%= cliente.getApellido() %>" required>
		                </div>
		                <div class="form-group">
		                    <label for="txtSexo">Sexo:</label>
		                    <select class="form-control" id="sexo" name="sexo" required>
		                        <option value="Masculino" <%= cliente.getSexo().equals("Masculino") ? "selected" : "" %>>Masculino</option>
		                        <option value="Femenino" <%= cliente.getSexo().equals("Femenino") ? "selected" : "" %>>Femenino</option>
		                        <option value="Otro" <%= cliente.getSexo().equals("Otro") ? "selected" : "" %>>Otro</option>
		                    </select>
		                </div>
		      			<input type="submit" class="btn btn-primary btn-block" value="Modificar Cliente" name="btnModificarCliente">
		            </form>

            <% 
            } 
            else { 
            %>
                <p>No se encontro el cliente.</p>
            <% 
            } 

            // Mostrar mensaje de error si existe
            String error = (String) request.getAttribute("error");
            if (error != null) { 
            %>
                <p style="color:red;"><%= error %></p>
            <% 
            }
            else
            %>
    </div>

            <% 
            
            { 
            %>
                <p>No se encontro el cliente.</p>
            <% 
            } 

            // Mostrar mensaje de error si existe
            error = (String) request.getAttribute("error");
            if (error != null) { 
            %>
                <p style="color:red;"><%= error %></p>
            <% 
            } 
            %>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
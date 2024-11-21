<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Cliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listado de Clientes</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Icono -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Listado de Clientes</h2>
        
        <!-- Formulario de b�squeda -->
       <form action="servletListarCliente" method="get" class="form-inline mb-4" id="formBusqueda">
            <input type="text" name="criterio" class="form-control mr-2" placeholder="Buscar cliente..." id="criterio">
            <button type="submit" class="btn btn-primary mr-2">Buscar</button>
            <a href="servletListarCliente" class="btn btn-secondary">Limpiar</a>
        </form>
        
        <!-- Tabla de clientes -->
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>DNI</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>CUIL</th>
                    <th>Sexo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Recuperar la lista de clientes
                    List<?> clientes = (List<?>) request.getAttribute("clientes");
                    if (clientes != null) {
                        for (Object obj : clientes) {
                            Cliente cliente = (Cliente) obj;
                %>
                            <tr>
                                <td><%= cliente.getId() %></td>
                                <td><%= cliente.getDni() %></td>
                                <td><%= cliente.getNombre() %></td>
                                <td><%= cliente.getApellido() %></td>
                                <td><%= cliente.getCuil() %></td>
                                <td><%= cliente.getSexo() %></td>
                                
                                <td>
                                    <a href="ModificarCliente.jsp?id=<%= cliente.getId() %>" class="btn btn-warning btn-sm" title="Editar">
                                        <i class="fas fa-edit"></i>
                                    </a>
                                    <a href="servletEliminarCliente?id=<%= cliente.getId() %>" class="btn btn-danger btn-sm" title="Eliminar" onclick="return confirm('¿Estas seguro de que deseas eliminar este cliente?');">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </td>
                            </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

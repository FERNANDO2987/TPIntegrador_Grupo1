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
    
       <script>  
        // Función para ocultar el mensaje después de un tiempo  
        function ocultarMensaje() {  
            var mensaje = document.getElementById("successMessage");  
            if (mensaje) {  
                setTimeout(function() {  
                    mensaje.style.display = "none";  
                }, 3000); // Cambia 3000 por la cantidad de milisegundos que desees  
            }  
        }  
    </script>  
    
</head>
<body>
    <div class="container mt-5 ml-0">
        <h2 class="text-center mb-4">Listado de Clientes</h2>
        
           <!-- Mostrar mensaje de éxito -->  
        <%  
            String mensajeExito = (String) request.getAttribute("mensajeExito");  
            if (mensajeExito != null) {  
        %>  
            <div id="successMessage" class="alert alert-success">  
                <%= mensajeExito %>  
            </div>  
        <%  
            }  
        %>  
        
        <!-- Formulario de b�squeda -->
       <form action="servletListarCliente" method="get" class="form-inline mb-4" id="formBusqueda">
            <input type="text" name="criterio" class="form-control mr-2" placeholder="Buscar cliente..." id="criterio">
            <button type="submit" class="btn btn-primary mr-2">Buscar</button>
            <a href="servletListarCliente" class="btn btn-secondary">Limpiar</a>
        </form>
        
        <!-- Tabla de clientes -->
        <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>DNI</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>CUIL</th>
                    <th>Sexo</th>
                    <th>Usuario</th>
                    <th>Password</th>
                    <th>Pais</th>
                    <th>Fecha Nacimiento</th>
                    <th>Correo</th>
                    <th>Telefono</th>
                    <th>Celular</th>
                    <th>Admin</th>
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
                                <td><%= cliente.getUsuario() %></td>
                                <td><%= cliente.getPassword() %></td>
                                <td><%= cliente.getPaisNacimiento() != null ? cliente.getPaisNacimiento().getNombre() : "N/A" %></td>
                               <td><%= cliente.getFechaNacimiento() %></td>
                               <td><%= cliente.getCorreo() %></td>
                               <td class="text-truncate" style="max-width: 150px;"><%= cliente.getTelefono() %></td>
                               <td class="text-truncate" style="max-width: 150px;"><%= cliente.getCelular() %></td>
                               <td><%= cliente.getAdmin() ? "Si" : "No"%></td>
                                
                                
                                <td>
                                     <a href="ModificarCliente.jsp?id=<%= cliente.getId() %>" class="btn btn-primary btn-sm" title="Editar" onclick="return confirm('¿Estas seguro de que deseas Modificar este cliente?');">
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
    </div>
    
        <script>  
        // Función para mostrar el mensaje y luego ocultarlo  
        function mostrarMensaje(tipo) {  
            var mensaje = document.getElementById(tipo);  
            if (mensaje) {  
                mensaje.style.display = "block"; // Mostrar el mensaje  
                // Ocultar el mensaje después de 3 segundos (3000 milisegundos)  
                setTimeout(function() {  
                    mensaje.style.display = "none";  
                }, 3000);  
            }  
        }  

        <% if(request.getAttribute("mensajeExito") != null) { %>  
            mostrarMensaje("successMessage");  
        <% } else if(request.getAttribute("mensajeError") != null) { %>  
            mostrarMensaje("errorMessage");  
        <% } %> 
        
    

        
    </script>  
  

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

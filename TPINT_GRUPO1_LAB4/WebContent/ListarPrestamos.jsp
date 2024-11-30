<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Prestamo" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listado de Préstamos</title>
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

    <!-- jQuery and DataTables -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#table_id').DataTable();
        });
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Listado de Préstamos</h2>
        
        <form method="get" action="servletListarPrestamos">
            <input type="submit" name="btnListarPrestamos" value="Traer Préstamos" class="btn btn-primary mb-3">
        </form>
        
        <%
            // Obtener lista de préstamos del request
            List<Prestamo> listaPrestamos = (List<Prestamo>) request.getAttribute("prestamos");
            if (listaPrestamos == null || listaPrestamos.isEmpty()) {
        %>
            <div class="alert alert-warning" role="alert">
                No se encontraron préstamos para mostrar.
            </div>
        <%
            } else {
        %>
        
        <!-- Tabla de Préstamos -->
        <table id="table_id" class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Cliente</th>
                    <th>Correo</th>
                    <th>Teléfono</th>
                    <th>CBU</th>
                    <th>Fecha Solicitud</th>
                    <th>Importe</th>
                    <th>Cuotas</th>
                    <th>Estado</th>
                    <th>Observaciones</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Prestamo p : listaPrestamos) {
                        String clienteNombre = p.getCliente() != null ? p.getCliente().getNombre() : "Desconocido";
                        String clienteApellido = p.getCliente() != null ? p.getCliente().getApellido() : "";
                        String clienteCorreo = p.getCliente() != null ? p.getCliente().getCorreo() : "No disponible";
                        String clienteTelefono = p.getCliente() != null ? p.getCliente().getTelefono() : "No disponible";
                        String cbu = p.getCuenta() != null ? p.getCuenta().getCbu() : "No disponible";
                        String observaciones = p.getObservaciones() != null ? p.getObservaciones() : "Sin observaciones";
                %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= clienteNombre %> <%= clienteApellido %></td>
                    <td><%= clienteCorreo %></td>
                    <td><%= clienteTelefono %></td>
                    <td><%= cbu %></td>
                    <td><%= p.getFechaSolicitud() %></td>
                    <td>$<%= p.getImporte() %></td>
                    <td><%= p.getCuotas() %></td>
                    <td><%= p.isEstado() ? "Pendiente" : "Aprobado" %></td>
                    <td><%= observaciones %></td>
                    <td>
                        <form method="post" action="servletRechazarPrestamo">
                            <input type="hidden" name="id" value="<%= p.getId() %>">
                            <input type="submit" class="btn btn-success btn-sm" name="btnAceptar" value="Aceptar">
                            <input type="submit" class="btn btn-danger btn-sm" name="btnRechazar" value="Rechazar">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            }
        %>
        <a class="btn btn-secondary btn-block mt-3" href="Home.jsp">Volver</a>
    </div>
</body>
</html>

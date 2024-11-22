<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="entidad.Prestamo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listado de Prestamos</title>
    <link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#table_id').DataTable();
		});
	</script>
	
	<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/2.1.8/css/dataTables.bootstrap4.css">
</head>
<body>
	
	<div class="container mt-5">
        <h2 class="text-center mb-4">Listado de Prestamos</h2>
        
        <form method="get" action="servletListarPrestamos">
        	<input type="submit" name="btnListarPrestamos" value="Traer prestamos">
        </form>
        
 
        <%
        	ArrayList<Prestamo> listaPrestamos = null;
        	if(request.getAttribute("ListaP")!=null)
        	{
        		listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("ListaP");
        	}
        %>
        
        <!-- Tabla de clientes -->
        
        <table id="table_id" class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Nro. Prestamo</th>
                    <th>Nro. Cuenta</th>
                    <th>Fecha Solicitud</th>
                    <th>Importe</th>
                    <th>#</th>
                </tr>
            </thead>
            <tbody>
            
            <% 
            if(listaPrestamos != null)
            for(Prestamo p : listaPrestamos) { %>
				<tr>
		               	<td><%=p.getId()%></td>
	     				<td><%=p.getCuenta().getNroCuenta() %></td>
	                    <td><%=p.getFechaSolicitud() %></td>
	                    <td><%=p.getImporte() %></td>
	                    <td>
							<form method="post" action="servletRechazarPrestamo">
		                    	<input type="hidden" value="<%= p.getId() %>" name="id">
		                    	<input type="submit" class="btn btn-primary btn-sm" name="btnAceptar" value="Aceptar">
								<input type="submit" class="btn btn-danger btn-sm" name="btnRechazar" value="Rechazar">
							</form>
	                    </td>
                </tr>
            
            <%
            	}            
            %>
                
   
            </tbody>
        </table>
        <a class= "btn btn-secondary btn-block" href="Home.jsp">Volver</a>
    </div>
	
<!-- 
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
 -->
</body>
</html>
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
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Icono -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	
	<div class="container mt-5">
        <h2 class="text-center mb-4">Listado de Prestamos</h2>
        
        <form method="post" action="servletListarPrestamos">
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
        
        <table class="table table-bordered table-hover">
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
	               	<td><%=p.getId() %></td>
     				<td><%=p.getCuenta().getNroCuenta() %></td>
                    <td><%=p.getFechaSolicitud() %></td>
                    <td><%=p.getImporte() %></td>
                    <td>
                    	<a href="#">Ver</a>
                    </td>
                </tr>
            
            <%
            	}            
            %>
                
   
            </tbody>
        </table>
        <a class= "btn btn-secondary btn-block" href="Home.jsp">Volver</a>
    </div>
	

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
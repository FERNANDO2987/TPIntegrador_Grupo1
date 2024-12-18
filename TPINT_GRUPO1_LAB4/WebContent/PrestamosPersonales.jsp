<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Prestamo" %>
<%@ page import="negocio.PrestamoNeg" %>
<%@ page import="nogocioImpl.PrestamoNegImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis prestamos</title>
</head>
<body>

	<%
      Cliente usuario = (Cliente)session.getAttribute("usuario");
      if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
      }
    %>
    
	<div class="container mt-5">
		<h1>Prestamos solicitados</h1>
    	<a class="btn btn-secondary w-10 mb-1" href="Home.jsp" > 	Volver al Home </a> 
	
		<table border="1" id="table_id" class="table table-striped table-bordered" >
            <thead class="thead-dark">
                <tr>
                    <th>Nro. Prestamo</th>
                    <th>Nro. Cuenta</th>
                    <th>Fecha Solicitud</th>
                    <th>Importe</th>
                    <th>Observaciones</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
            <%
		    PrestamoNegImpl prestamoNeg = new PrestamoNegImpl();
		    List<Prestamo> prestamos = prestamoNeg.obtenerPrestamosXCliente(usuario.getId());
			if (prestamos != null && !prestamos.isEmpty()) {
			for (Prestamo p : prestamos) {
		    %>
            	<tr>
			    	<td><%=p.getId() %></td>
			    	<td><%=p.getCuenta().getNroCuenta() %></td>
			      	<td><%=p.getFechaSolicitud() %></td>
			      	<td>$<%=p.getImporte() %></td>
			      	<%if(p.getObservaciones() != null){ %>
			      	<td><%=p.getObservaciones() %></td>
			      	<%}else{ %><td>---</td><%} %>
			      	<% 
					if(p.isEstado() == null){%>
					<td>En Proceso</td>
					<%}else if(p.isEstado() == true){%>
			      	<td>Aprobado</td>
			      	<%}else if(p.isEstado() == false){%>
			      	<td>Rechazado</td>
			      	<%} %>
            	</tr>
            	
            <%}}%>
            </tbody>
		</table>
		
		
	</div>
</body>
</html>
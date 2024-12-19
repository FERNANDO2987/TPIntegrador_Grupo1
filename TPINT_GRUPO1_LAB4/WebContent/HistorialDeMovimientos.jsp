<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Movimiento" %>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Historial de movimientos</title>
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
	<%
      Cliente usuario = (Cliente)session.getAttribute("usuario");
      if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
      }
    %>
    <nav class="navbar bg-body-tertiary" style="background-color: #343a40">
	  <div class="container-fluid d-flex justify-content-between align-items-center">
	    <a class="navbar-brand fs-3" href="Home.jsp">Gestion Bancaria</a>
	    <h4 style="fs-2" >Bienvenido, <%= usuario.getUsuario() %></h4>
	  </div>
	</nav>
    
    
	<h3>Usuario logueado: <%=usuario.getUsuario() %></h3>

	<div class="container mt-5">
        

        <!-- Tabla de movimientos -->
        <div class="row pt-5 justify-content-end ">	
       		 <a class="btn btn-secondary my-1 mx-3 " href="Home.jsp" > 	Volver al Home </a> 
        <!-- 
    		<form method="get" action="servletListarMovimientosXCuenta">
            	<input type="submit" name="btnListarMovimientosXCuenta" value="Traer Movimientos" class="btn btn-secondary">
            	<input type="hidden" name="idusuario" value="request.getParameter("id")">
       		</form>
         -->
       		
		</div>
        
        <h2 class="text-center mb-4">Historial de movimientos</h2>
        
        
        <%
            //
            List<Movimiento> listaMovimientos = (List<Movimiento>) request.getAttribute("listado");
            if (listaMovimientos == null || listaMovimientos.isEmpty()) {
        %>
            <div class="alert alert-warning" role="alert">
                No se encontraron Movimientos para mostrar.
            </div>
        <%
            } else {
        %>
        <table id="table_id" class="table table-bordered table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>Nro Cuenta</th>
                    <th>Importe</th>
                    <th>Tipo de movimiento</th>
                    <th>Detalle</th>
                    <th>Fecha</th> 
                </tr>
            </thead>
            <tbody>
            <%
                    for (Movimiento m : listaMovimientos) {
            %>
			    <tr>
			    
			        <td><%=m.getNroCuenta()%></td>
			        <td><%=m.getImporte()%></td>
			        <td><%=m.getTipoMovimiento().getDescripcion()%></td>
			        <td><%=m.getDetalle()%></td>
			        <td><%=m.getFecha()%></td>
			    </tr>
		    <%
            } 
        	%>
			</tbody>
        </table>
        <%
            } 
        %>
    </div>



</body>
</html>
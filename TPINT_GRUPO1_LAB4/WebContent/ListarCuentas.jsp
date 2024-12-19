<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="entidad.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listar Cuentas</title>
	<!-- DataTable -->
	
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
    <nav class="navbar bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="Home.jsp">Gestion Bancaria</a>
	    <h5 class="d-flex"> Bienvenido, <%= usuario.getNombre() %></h5>
	  </div>
	</nav> 
<div class="container mt-5">
        <h2 class="text-center mb-4">Listado de Cuentas</h2>
       <div class="d-flex justify-content-between align-items-center mb-4"> 
        <a href="servletAgregarCuenta" class="btn btn-primary mr-2 mb-4">Agregar Nueva Cuenta</a>
    	<a class="btn btn-secondary my-1" href="Home.jsp" > 	Volver al Home </a>    
         </div>
        <!-- Tabla de cuentas -->
        <%
            		List<Cuenta> listaCuenta;
		            if(request.getAttribute("listado") != null)
		        	{
		        		listaCuenta = (List<Cuenta>) request.getAttribute("listado");
		        	}
		            else
		            {
		            	listaCuenta = null;
		            }
					
					
		%>
		<div class="form-group m-1 mb-2">
		  <form action="servletListarCuentas" method="post" class="d-flex align-items-center">
		    
		    <label class="form-control mb-0 mr-2" id="lblNombApe">Nombre y Apellido</label>
		    
		    <select class="form-control mb-0 mr-2" id="selectCriterio" name="selectCriterio">
		      <option value=1>Comienza por</option>
		      <option value=-1>Termina con</option>
		      <option value=0>Contiene</option>
		    </select>
		    
		    <input class="form-control mb-0 mr-2" id="txtFiltro" name="txtFiltro" type="text">
		    
		    <input class="form-control btn btn-primary mb-0 mr-2" id="btnFiltro" name="btnFiltro" value="Filtrar" type="submit">
		    
		    <input class="form-control btn btn-secondary mb-0" id="btnLimpiar" name="btnLimpiar" value="Limpiar filtro" type="submit">
		
		  </form>
		</div>
		<%if(listaCuenta == null){ %>
			<div class="alert alert-danger" id="errorMessage">
            No se encontraron Registros
        	</div>
		<%} %>
        <table border="1" id="table_id" class="table table-striped table-bordered" >
            <thead class="thead-dark">
                <tr>
                    <th>Nro Cuenta</th>
                    <th>CBU</th>
                    <th>Cliente</th>
                    <th>Tipo de Cuenta</th>
                    <th>Saldo</th>
                    <th>Activo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            
            <tbody>
            	<%
            		if (listaCuenta != null)
            		{
            			for(Cuenta cuenta : listaCuenta)
            			{
            				
            	%>
            			
							<tr>
								<form action="servletListarCuentas" method="post">
					                <td><%= cuenta.getNroCuenta() %><input type="hidden" value="<%= cuenta.getNroCuenta() %>" name="nroCuenta"></td>
				     				<td><%= cuenta.getCbu() %></td>
				                    <td><%= cuenta.getCliente().getNombre() %> <%= cuenta.getCliente().getApellido() %></td>
				                    <td><%= cuenta.getTipoCuenta().getDescripcion() %></td>
				                    <td>$<%= cuenta.getSaldo() %></td>
				                    <td><%= cuenta.getEstado()? "Inactivo" : "Activo" %>  </td>		                    
				                    <td>
										<input type="submit" class="btn btn-primary btn-sm" name="btnModificar" value="Modificar">
				                        <input type="submit" class="btn btn-danger btn-sm" name="btnEliminar" value="Eliminar">
				                  	</td>
			                  	</form>
			                </tr>
		               
				<%
            			}
            		}
            		else
            		{
				%>
					<tr>
						
			                <td>-</td>
		     				<td>-</td>
		                    <td>-</td>
		                    <td>-</td>
		                    <td>$-</td>
		                    <td>-</td>
		                    <td>
								<input type="submit" class="btn btn-primary btn-sm" name="btnModificar" value="Modificar">
				                <input type="submit" class="btn btn-danger btn-sm" name="btnEliminar" value="Eliminar">
		                  	</td>
		                </tr>
					
				<%	} %>
   
            </tbody>
        </table>
	    
    </div>
</body>
</html>
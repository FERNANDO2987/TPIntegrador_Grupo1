<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.TipoCuenta" %>
<%@ page import="entidad.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Cuenta</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<%
      Cliente usuario = (Cliente)session.getAttribute("usuario");
      if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
      }
      
      String exito = new String();
      if(request.getAttribute("exitoAlAgregar") != null)
      {
    	  exito = (String)request.getAttribute("exitoAlAgregar");
      }
    %>
	<%
		List<Cliente> listaClientes;
		List<TipoCuenta> listaTipoCuenta;
		
		if(request.getAttribute("listaClientes") != null)
    	{
    		listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
    	}
        else
        {
        	listaClientes = null;
        }
		
		if(request.getAttribute("listaTipoCuenta") != null)
    	{
    		listaTipoCuenta = (List<TipoCuenta>) request.getAttribute("listaTipoCuenta");
    	}
        else
        {
        	listaTipoCuenta = null;
        }
		
		
	%>
	
	<form action="servletAgregarCuenta" method="get">
		<!-- Modal -->
		<div class="modal fade" id="ModalConfirmacion" tabindex="-1" aria-labelledby="ConfirmacionModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title fs-5" id="ConfirmacionModalLabel">Confirmacion Requerida</h4>
		      </div>
		      <div class="modal-body">
		        Desea confirmar la modificacion del registro?
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		        <input type="submit" class="btn btn-primary" value="Agregar Cuenta" name="btnAgregar">
		      </div>
		    </div>
		  </div>
		</div>
		
	<div class="container mt-5">
        <h2 class="text-center mb-4">Agregar Cuenta</h2>
        <%if(exito.compareTo("Se agrego exitosamente") == 0)
		{%>
			<div class="alert alert-success" id="successMessage">
            <%=exito %>
        	</div>
        	
		<%}
        if(exito.compareTo("Excede el limite de 3(tres) cuentas") == 0)
        {%>
        	<div class="alert alert-danger" id="errorMessage">
            <%=exito %>
        	</div>
        <%} %>
        
        
            
				<div class="form-group">
					<label for="cliente">Cliente:</label>
                    <select class="form-control" id="cliente" name="cliente" required>
                    	<%if (listaClientes != null)
                    	  {
                    		for (Cliente cliente: listaClientes)
                    		{
                    		%>
                        		<option value=<%=cliente.getId() %>><%=cliente.toString() %></option>
                        <%	} 
                          }
                    	  else
                    	  {
                    		  
                        %>
                        	<option >-</option>
                        <%} %>
                    </select>
                    
                    <label for="TipoCuenta">Tipo de Cuenta:</label>
                    <select class="form-control" id="TipoCuenta" name="TipoCuenta" required>
                    	<%if (listaTipoCuenta != null)
                    		{
                    			for(TipoCuenta tipoCuenta : listaTipoCuenta)
                    			{
                    		%>
                        		<option value=<%=tipoCuenta.getId() %>><%=tipoCuenta.toString() %></option>
                        	<%	} 
                        	}
                        	else
                        	{%>
                        		<option value="-" >-</option>
                        	<%} %>
                    </select>
                </div>
                <button type="button" class="btn btn-primary btn-block mt-3" data-toggle="modal" data-target="#ModalConfirmacion">Agregar Cuenta</button>
                <a class= "btn btn-secondary btn-block" href="servletListarCuentas">Volver</a>
            </form>
            
            

    </div>
		
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
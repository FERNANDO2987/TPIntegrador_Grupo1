<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.TipoCuenta" %>
<%@ page import="entidad.Cuenta" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Modificar Cuenta</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Icono -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<%
		List<TipoCuenta> listaTipoCuenta = new ArrayList<TipoCuenta>();
		if(request.getAttribute("listaTiposCuentas") != null)
		{
			listaTipoCuenta = (ArrayList<TipoCuenta>)request.getAttribute("listaTiposCuentas");
		}
		
		Cuenta cuenta = new Cuenta();
		if(request.getAttribute("cuenta") != null)
		{
			cuenta = (Cuenta)request.getAttribute("cuenta");
		}
	%>
	<div class="container mt-5">
        <h2 class="text-center mb-4">Modificar Cuenta</h2>
        
            <form action="servletModificarCuenta" method="post">
            
				<div class="form-group">
					<label for="txtCliente">Cliente:</label>
                    <input type="text" class="form-control" name="txtCliente" readonly="true" value="<%= cuenta.getCliente().getNombre() %> <%= cuenta.getCliente().getApellido() %>">
                    
                    <label for="txtCuenta">Nro Cuenta:</label>
                    <input type="text" class="form-control" name="txtCuenta" readonly="true" value="<%= cuenta.getNroCuenta() %>">
                    
                    <label for="txtCBU">CBU:</label>
                    <input type="text" class="form-control" name="txtCBU" readonly="true" value="<%= cuenta.getCbu() %>">
                    
                    <label for="TipoCuenta">Tipo Cuenta:</label>
                    <select class="form-control" id="TipoCuenta" name="TipoCuenta" required>
                    <% for (TipoCuenta tipo : listaTipoCuenta) 
                    	{%>
                    	
                    		<option <%= tipo.getId() == cuenta.getTipoCuenta().getId()? "selected" : "" %> value=<%=tipo.getId() %>><%= tipo.getDescripcion() %></option>
                    <%	}%>
                    </select>
                    <label for="chkActivo">Activo:</label>
                    <input type="checkbox" class="form-check align-left" name="chkActivo" value="Activo" <%=cuenta.getEstado()?"":"checked" %>>
                    
                    
                </div>
                <input type="submit" class="btn btn-primary btn-block mt-3" value="Modificar Cuenta" name="btnModificarCuenta">
                <a class= "btn btn-secondary btn-block" href="servletListarCuentas">Volver</a>
            </form>
            <%= request.getAttribute("exito") != null? (boolean)request.getAttribute("exito") == true? "La Modificacion se realizo con exito":"":"" %>
             </div>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
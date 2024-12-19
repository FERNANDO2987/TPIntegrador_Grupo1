<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Cuenta" %>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuentas Asociadas</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
    <div class="text-center mb-5">
        <h3>Cuentas Asociadas</h3>
    </div>
<%-- 	<div class="row pt-5 ml-1">	
    	<form method="get" action="servletListarCuentasXCliente">
            <input type="submit" name="btnListarCuentasXCliente" value="Traer Cuentas" class="btn btn-secondary">
            <input type="hidden" name="idusuario" value="<%=usuario.getId()%>">
        </form>
	</div> --%>
    <div class="row pt-1">

		<%
            // Obtener lista de préstamos del request	
            List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listado");
            if (listaCuentas == null || listaCuentas.isEmpty()) {
        %>
		 <div class="alert alert-warning" role="alert">
                No se encontraron préstamos para mostrar.
            </div>
        <%
            } else {
        %>
        <div class="col-md-4">
        
        	<% for (Cuenta c : listaCuentas) 
        	{
        	%>
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Nro de Cuenta: <%=c.getNroCuenta()%></h5>
                    <hr>
                    <p class="card-text">
                        <strong><%=c.getTipoCuenta().getDescripcion()%></strong><hr>
                        <strong>CBU:</strong> <%=c.getCbu()%> <hr>
                        <strong>Fecha de creacion:</strong> <%=c.getFechaCreacion()%>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item" style="font-size: 23px;"><strong> $<%=c.getSaldo()%> </strong></li>
                </ul>
                <div class="card-body pt-1 pb-1">
                	<form method="get" action="servletListarMovimientosXCuenta">
			            <input type="submit" name="btnListarMovimientosXCuenta" value="Historial de movimientos" class="btn btn-secondary">
			            <input type="hidden" name="nroCuenta" value="<%=c.getNroCuenta()%>">
        			</form>
                </div>
            </div>
            <%
            }
        	%>
        </div>
        <%
            }
        %>
        
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

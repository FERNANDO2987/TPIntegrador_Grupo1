<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="t1ext/html; charset=ISO-8859-1">
<title>Transferencia</title>
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Icono -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<%
      Cliente usuario = (Cliente)session.getAttribute("usuario");
      if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
      }
      
      List<Cuenta> listaDeOrigen = new ArrayList<Cuenta>();;
      if(request.getAttribute("listaDeMisCuentas") != null)
      {
    	  listaDeOrigen = (List<Cuenta>) request.getAttribute("listaDeMisCuentas");
      }
      String exito = new String();
      if(request.getAttribute("exitoTransfer") != null)
      {
    	  exito = (String)request.getAttribute("exitoTransfer");
      }
    %>
    
    <nav class="navbar bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="Home.jsp">Gestion Bancaria</a>
	    <h5 class="d-flex"> Bienvenido, <%= usuario.getNombre() %></h5>
	  </div>
	</nav> 
    
    <!-- Modal -->
	<form action="servletTransferencia" method="post">
		<div class="modal fade" id="ModalConfirmacion" tabindex="-1" aria-labelledby="ConfirmacionModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title fs-5" id="ConfirmacionModalLabel">Confirmacion Requerida</h4>
		      </div>
		      <div class="modal-body">
		        Desea confirmar la transferencia?
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		        <input type="submit" class="btn btn-primary" value="Transferir" name="btnTransferir">
		      </div>
		    </div>
		  </div>
		</div>
		

<div class="container mt-5">
    <div class="text-center mb-4">
        <h3>Transferir a otro cliente</h3>
        <%if(exito.compareTo("Transferencia realizada con exito") == 0)
		{%>
			<div class="alert alert-success" id="successMessage">
            <%=exito %>
        	</div>
        	
		<%}
        if(exito.compareTo("No se pudo realizar la transferencia") == 0)
        {%>
        	<div class="alert alert-danger" id="errorMessage">
            <%=exito %>
        	</div>
        <%} %>
    </div>
    
    <div class="row justify-content-center">
    	
    	
        <div class="col-md-6">
                <div class="form-group">
                    <label for="cuentaOrigen">Cuenta de origen</label>
                    <select class="form-control" id="cuentaOrigen" name="cuentaOrigen" required>
                        <%if (listaDeOrigen != null){
                    	for(Cuenta cuenta : listaDeOrigen){%>
                    	<option value=<%=cuenta.getNroCuenta() %>><%=cuenta.toString() %></option>
                    <%	}
                      }%>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="cbuDestino">CBU de la cuenta de destino</label>
                    <input type="text" class="form-control" id="cbuDestino" name="cbuDestino" placeholder="Ingresa el CBU de destino" required>
                </div>
                
                
                <div class="form-group">
                    <label for="monto">Monto a transferir</label>
                    <input type="number" class="form-control" id="monto" name="monto" placeholder="Ingrese el monto" required>
                </div>
                
                <div class="form-group">
                    <label for="descripcion">Detalle</label>
                    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Motivo de la transferencia">
                </div>
                
                <button type="button" class="btn btn-primary btn-block mt-3" data-toggle="modal" data-target="#ModalConfirmacion">Realizar Transferencia</button>
            </form>
        </div>
    </div>
</div>

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
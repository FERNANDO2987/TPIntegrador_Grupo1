<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.Cliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Solicitar Préstamo</title>
    <!-- Bootstrap CSS -->
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
    	<div class="row">
    		<div class="col-6 mx-auto">
		        <h2 class="text-center mb-4">Solicitar Préstamo</h2>
		        <form action="servletAgregarPrestamo" method="POST">
		            
		            <div class="form-group">
		                <label for="monto">Monto Solicitado:</label>
		                <input type="number" class="form-control" id="monto" name="monto" placeholder="Ingrese el monto a solicitar" required>
		            </div>
		            <div class="form-group">
						<label for="cuotas">Cuotas:</label>
		                <input type="number" min="1" max="24"  class="form-control" id="cuotas" name="cuotas" placeholder="Ingrese la cantidad de cuotas" required>
		            </div>
					<input type="submit" class="btn btn-primary btn-block" name=btnSubmit value="Solicitar Préstamo">
		        </form>
    		</div>
    	</div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

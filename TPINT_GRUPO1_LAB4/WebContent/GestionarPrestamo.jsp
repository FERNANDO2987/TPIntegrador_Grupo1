<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gestionar Prestamo</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Icono -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	
	<div class="container mt-5">
        <h2 class="text-center mb-4">Gestion de Prestamos</h2>
        
        <!-- Tabla de prestamo -->
        <table class="table table-bordered table-hover mb-5">
            <thead class="thead-dark">
                <tr>
                    <th>Nro. Prestamo</th>
                    <th>Nro. Cuenta</th>
                    <th>Fecha Solicitud</th>
                    <th>Importe</th>       
                </tr>
            </thead>
            <tbody>
				<tr>
	                <td>1</td>
     				<td>123</td>
                    <td>11/04/2024</td>
                    <td>$18.500,00</td>
                </tr>
            </tbody>
        </table>
        <div>
 
    		<div class="m-auto w-50">
			 	<label for="descripcion" class="form-label">Motivo</label>
			  	<textarea class="form-control" placeholder="Motivo del rechazo..." id="descripcion" rows="3"></textarea>
			</div>
    		<div class="m-auto w-50 d-flex justify-content-between">
	        	<a class= "btn btn-primary w-50"  href="#">Aceptar</a>
    			<a class= "btn btn-danger w-50" href="#">Rechazar</a>    		
    		</div>
        </div>
    </div>
	

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
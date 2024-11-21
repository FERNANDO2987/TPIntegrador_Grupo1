<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis prestamos</title>
</head>
<body>
	<div class="container mt-5">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Nro. Prestamo</th>
		      <th scope="col">Nro. Cuenta</th>
		      <th scope="col">Fecha Solicitud</th>
		      <th scope="col">Importe</th>
		      <th scope="col">Estado</th>
		      <th scope="col">#</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<form>
			    <tr>
			      <th scope="row">1</th>
			      <td>123</td>
			      <td>11/04/2024</td>
			      <td>$130000</td>
			      <td>Aprobado</td>
			      <td><input type="button" value="Detalle" class="btn btn-primary">
			    </tr>
		    </form>
		    
		    <form>
			    <tr>
			      <th scope="row">2</th>
			      <td>123</td>
			      <td>09/07/2024</td>
			      <td>$50000</td>
			      <td>Aprobado</td>
			      <td><input type="button" value="Detalle" class="btn btn-primary">
			    </tr>
		    </form>
		    
		    <form>
		    <tr>
		      <th scope="row">3</th>
		      <td>234</td>
		      <td>21/09/2024</td>
		      <td>$100000</td>
		      <td>Rechazado</td>
		      <td><input type="button" value="Detalle" class="btn btn-primary">
		    </tr>
		    </form>
		  </tbody>
		</table>
	</div>
</body>
</html>
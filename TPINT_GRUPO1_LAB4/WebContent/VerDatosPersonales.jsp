<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datos Personales</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
    <nav class="navbar bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="Home.jsp">Gestion Bancaria</a>
	    <h5 class="d-flex"> Bienvenido, <%= usuario.getNombre() %></h5>
	  </div>
	</nav> 
    <div class="container mt-5">
        <div class="card">
            <div class="card-header text-center">
                <h3>Datos Personales</h3>
            </div>
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-6">
                    	<p><strong>Nombre:</strong> <%= usuario.getNombre() %> </p>
                        
                    </div>
                    <div class="col-md-6">
                    	<p><strong>Apellido:</strong> <%= usuario.getApellido() %></p>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <p><strong>DNI:</strong> <%= usuario.getDni() %></p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>CUIL:</strong> <%= usuario.getCuil() %></p>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <p><strong>Correo:</strong> <%= usuario.getCorreo() %> </p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Nacionalidad:</strong> <%= usuario.getPaisNombre() %></p>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <p><strong>Fecha de Nacimiento:</strong> <%= usuario.getFechaNacimiento() %> </p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Sexo:</strong> <%= usuario.getSexo() %></p>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <p><strong>Dirección:</strong> <%= usuario.getDireccion() %></p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Usuario:</strong> <%= usuario.getUsuario() %> </p>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <p><strong>Teléfono:</strong><%= usuario.getTelefono() %></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

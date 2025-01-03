<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Menu de Gestion de Banco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script>
      function showAlert(option) {
        alert("Seleccionaste: " + option);
      }
    </script>
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

      <div class="accordion" id="menuAccordion">
	
		<% if (usuario.getAdmin()) { %>
        <!-- Menu Administrador -->
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingAdmin">
            <button
              class="accordion-button"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#collapseAdmin"
              aria-expanded="true"
              aria-controls="collapseAdmin"
            >
              Menu Administrador
            </button>
          </h2>
          <div
            id="collapseAdmin"
            class="accordion-collapse collapse show"
            aria-labelledby="headingAdmin"
            data-bs-parent="#menuAccordion"
          >
            <div class="accordion-body">
              <ul class="list-group">
                <li class="list-group-item">
                  Gestion de Clientes
                  <ul>
                    <li>
                      <a
                        href="<%= request.getContextPath() %>/AgregarCliente.jsp"
                        class="text-decoration-none"
                        >Agregar Cliente</a
                      >
                    </li>
                    <li>
                      <a
                        href="servletListarCliente"
                        class="text-decoration-none"
                        >Listar Clientes</a
                      >
                    </li>
                  </ul>
                </li>
                <li class="list-group-item">
                  Gestion de Cuentas
                  <ul>
                    <li>
                      <a
                        href="servletListarCuentas"
                        class="text-decoration-none"
                        >Listar Cuentas</a
                      >
                    </li>
                  </ul>
                </li>
                <li class="list-group-item">
                  Informes
                  <ul>
                    <li>
                      <a
                        href="InformeIngresosEgresos.jsp"
                        class="text-decoration-none"
                        >Generar Informe de Ingresos y Egresos</a
                      >
                    </li>
                     <li>
                      <a
                        href="InformeCuotasPendientesYPagas.jsp"
                        class="text-decoration-none"
                        >Generar Informe de Cuotas Pagas e Impagas</a
                      >
                    </li>
                  </ul>
                </li>
                  <li class="list-group-item">
                  Prestamos Solicitados
                  <ul>
                       <li>
                      <a
                        href="servletListarPrestamos"
                        class="text-decoration-none"
                        >Listar Prestamos Solicitados - Autorizar/Rechazar</a
                      >
                    </li>
                  </ul>
                </li>
                
              </ul>
            </div>
          </div>
        </div>
		<% } %>
		
        <!-- Menu Cliente -->
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingCliente">
            <button
              class="accordion-button collapsed"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#collapseCliente"
              aria-expanded="false"
              aria-controls="collapseCliente"
            >
              Menu Cliente
            </button>
          </h2>
          <div
            id="collapseCliente"
            class="accordion-collapse collapse"
            aria-labelledby="headingCliente"
            data-bs-parent="#menuAccordion"
          >
            <div class="accordion-body">
              <ul class="list-group">
                <li class="list-group-item">
                  Consultar Informacion Personal
                  <ul>
                    <li>
                      <a
                        href="<%= request.getContextPath() %>/VerDatosPersonales.jsp"
                        class="text-decoration-none"
                        >Ver Datos Personales</a
                      >
                    </li>
                  </ul>
                </li>
                
                
                
                <li class="list-group-item">
                  Gestion de Cuentas
                  <ul>
                    <li>
                      <a
                        href="servletListarCuentasXCliente"
                        class="text-decoration-none"
                        >Ver Cuentas Asociadas</a
                      >
                    </li>
                  </ul>
                </li>
                
                <li class="list-group-item">
                  Transferencias
                  <ul>
                    <li>
                      <a
                        href="servletTransferencia"
                        class="text-decoration-none"
                        >Transferir a una cuenta externa</a>
                    </li>
                    <li>
                      <a
                        href="servletTransferenciaEntreCuentas"
                        class="text-decoration-none"
                        >Transferir a mis cuentas</a>
                    </li>
                  </ul>
                </li>
                     <li class="list-group-item">
                  Prestamos
                  <ul>
                    <li>
                      <a
                        href="PagarCuotasPrestamo.jsp"
                        class="text-decoration-none"
                        >Pagar Prestamos Solicitados</a>
                    </li>
                  
                  </ul>
                </li>
                
                <li class="list-group-item">
                	Prestamos
                	<ul>
                		<li>
                			<a class="text-decoration-none" href="SolicitarPrestamos.jsp" >Solicitar un prestamo</a>
                		</li>
                		<li>
                      		<a href="PrestamosPersonales.jsp" class="text-decoration-none" >Ver Prestamos solicitados</a>
                   		</li>
                	</ul>
                </li>
                <li class="list-group-item">
				  <form action="<%= request.getContextPath() %>/servletLogout" method="post">
				    <button type="submit" class="btn btn-danger">Cerrar Sesi�n</button>
				  </form>
				</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>  </body>
</html>

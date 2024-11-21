<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Menu de Gestion de Banco</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
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
    <div class="container mt-5">
      <h1>Menu de Gestion de Banco</h1>
      <h4>Bienvenido, <%= usuario.getUsuario() %>.</h4>

      <div class="accordion" id="menuAccordion">

        <!-- Menu Administrador -->
        <% if (usuario.getAdmin()) { %>
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
                        href="<%= request.getContextPath() %>/ListarClientes.jsp"
                        class="text-decoration-none"
                        >Listar Clientes</a
                      >
                    </li>
                  </ul>
                </li>
                <li class="list-group-item">
                  Gestion de Usuarios
                  <ul>
                    <li>
                      <a
                        href="#"
                        onclick="showAlert('Asignar Usuario a Cliente')"
                        class="text-decoration-none"
                        >Agregar Usuario</a
                      >
                    </li>
                    <li>
                      <a href="ListarUsuarios.jsp" class="text-decoration-none"
                        >Listar Usuarios</a
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
                        href="<%= request.getContextPath() %>/VerCuentasAsociadas.jsp"
                        class="text-decoration-none"
                        >Ver Cuentas Asociadas</a
                      >
                    </li>
                  </ul>
                </li>
                <li class="list-group-item">
				  <form action="<%= request.getContextPath() %>/LogoutServlet" method="post">
				    <button type="submit" class="btn btn-danger">Cerrar Sesión</button>
				  </form>
				</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

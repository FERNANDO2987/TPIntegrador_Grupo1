
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente" %>
<%@ page import="datos.ClienteDao" %>
<%@ page import="datosImpl.ClienteDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Pais" %>
<%@ page import="negocio.PaisNeg" %>
<%@ page import="nogocioImpl.PaisNegImpl" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Modificar Cliente</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
         
        <script>
        function ocultarMensaje() {
            setTimeout(function() {
                var mensaje = document.getElementById("successMessage") || document.getElementById("errorMessage");
                if (mensaje) {
                    mensaje.style.display = "none";
                }
                setTimeout(function() {
                    window.location.href = "ListarClientes.jsp";
                }, 7000); // Redirige despu�s de 7 segundos
            }, 3000); // Oculta despu�s de 3 segundos
        }
    </script>
    
</head>
<%
      Cliente usuario = (Cliente)session.getAttribute("usuario");
      if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
      }
    %>
<body onload="ocultarMensaje()">
    <div class="container mt-5">
        <h2 class="text-center mb-4">Modificar Cliente</h2>
        
     
		    <div class="container mt-5">
		                         <!-- Mostrar mensajes de �xito o error -->
        <%
            String mensajeExito = (String) session.getAttribute("mensajeExito");
            String mensajeError = (String) session.getAttribute("mensajeError");
        %>

        <% if (mensajeExito != null) { %>
            <div id="successMessage" class="alert alert-primary alert-dismissible fade show" role="alert">
                <%= mensajeExito %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <% session.removeAttribute("mensajeExito"); %>
        <% } else if (mensajeError != null) { %>
            <div id="errorMessage" class="alert alert-danger alert-dismissible fade show" role="alert">
                <%= mensajeError %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <% session.removeAttribute("mensajeError"); %>
        <% } %>


           <%
            // Obtener el ID del cliente desde la solicitud
            int idCliente = Integer.parseInt(request.getParameter("id"));
            ClienteDao clienteDao = new ClienteDaoImpl();
            
            Cliente cliente = clienteDao.obtenerClientes().stream()
                    .filter(c -> c.getId() == idCliente)
                    .findFirst()
                    .orElse(null);

            if (cliente != null) {
        %>
		
		            <form action="servletModificarCliente" method="POST">
		                <input type="hidden" name="id" value="<%= cliente.getId() %>"> <!-- Campo oculto para el ID -->
		
		                <div class="form-group">
		                    <label for="txtDni">DNI:</label>
		                    <input type="text" class="form-control" id="dni" name="dni" value="<%= cliente.getDni() %>" required readonly style="pointer-events: none; background-color: #f8f9fa; border: none;">
		                </div>
		                <div class="form-group">
		                    <label for="txtCuil">CUIL:</label>
		                    <input type="text" class="form-control" id="cuil" name="cuil" value="<%= cliente.getCuil() %>" required readonly style="pointer-events: none; background-color: #f8f9fa; border: none;">
		                </div>
		                <div class="form-group">
		                    <label for="txtNombre">Nombre:</label>
		                    <input type="text" class="form-control" id="nombre" name="nombre" value="<%= cliente.getNombre() %>" required>
		                </div>
		                <div class="form-group">
		                    <label for="txtApellido">Apellido:</label>
		                    <input type="text" class="form-control" id="apellido" name="apellido" value="<%= cliente.getApellido() %>" required>
		                </div>
		                <div class="form-group">
		                    <label for="txtSexo">Sexo:</label>
		                    <select class="form-control" id="sexo" name="sexo" required>
		                        <option value="Masculino" <%= cliente.getSexo().equals("Masculino") ? "selected" : "" %>>Masculino</option>
		                        <option value="Femenino" <%= cliente.getSexo().equals("Femenino") ? "selected" : "" %>>Femenino</option>
		                       
		                    </select>
		                </div>
		                
		                  <div class="form-group">
		                    <label for="txtUsuario">Usuario:</label>
		                    <input type="text" class="form-control" id="usuario" name="usuario" value="<%= cliente.getUsuario() %>" required>
		                </div>
		                
		                   <div class="form-group">
		                    <label for="txtPassword">Password:</label>
		                    <input type="text" class="form-control" id="password" name="password" value="<%= cliente.getPassword() %>" required>
		                </div>
		                
		                 <div class="form-group">
                            <label for="txtPais">Pa�s:</label>
                             <select class="form-control" id="pais" name="pais" required>
                            <option value="">Seleccionar</option>
                             <%
                                 // Obtener la lista de pa�ses desde la base de datos
                           PaisNegImpl paisNeg = new PaisNegImpl();
                           List<Pais> paises = paisNeg.listarPaises();
                           if (paises != null && !paises.isEmpty()) {
                             for (Pais pais : paises) {
                             %>
                              <option value="<%= pais.getId() %>"><%= pais.getNombre() %></option>
                           <%
                           }
                           } else {
                           %>
                                 <option value="">No hay pa�ses disponibles</option>
                            <%
                              }
                           %>

                         </select>
                       </div>
                         <div class="form-group">
                          <label for="txtFechaNacimiento">Fecha de Nacimiento:</label>
                           <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="<%= cliente.getFechaNacimiento()%>"  required readonly 
                            onfocus="this.removeAttribute('readonly');">
                        </div>
                           
                           <div class="form-group">
                                <label for="correo">Correo Electr�nico:</label>
                                <input type="email" class="form-control" id="correo" name="correo" value="<%= cliente.getCorreo() %>"
                                 pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" 
                                 title="Ingrese un correo v�lido (ejemplo: usuario@dominio.com)" required>
                                </div>
                           
               	                
		                <div class="form-group">
		                    <label for="txtTelefono">Tel�fono:</label>
		                    <input type="text" class="form-control" id="telefono" name="telefono" value="<%= cliente.getTelefono() %>" required>
		                </div>
		                
		                <div class="form-group">
		                    <label for="txtCelular">Celular:</label>
		                    <input type="text" class="form-control" id="celular" name="celular" value="<%= cliente.getCelular() %>" required>
		                </div>
                       
		               <div class="form-group">
                         <label for="admin">
                        <input type="checkbox" id="admin" name="admin"  value="true" 
                         <%= cliente.getAdmin() ? "checked" : "" %> >
                          Administrador
                           </label>
                           </div>


		                
		      			<input type="submit" class="btn btn-primary btn-block" value="Modificar Cliente" name="btnModificarCliente">
		            </form>

            <% 
            } 
            else { 
            %>
                <p>No se encontro el cliente.</p>
            <% 
            } 

            // Mostrar mensaje de error si existe
            String error = (String) request.getAttribute("error");
            if (error != null) { 
            %>
                <p style="color:red;"><%= error %></p>
            <% 
            }
            else
            %>
    </div>

            <% 
            
            { 
            %>
                <p>No se encontro el cliente.</p>
            <% 
            } 

            // Mostrar mensaje de error si existe
            error = (String) request.getAttribute("error");
            if (error != null) { 
            %>
                <p style="color:red;"><%= error %></p>
            <% 
            } 
            %>
    </div>

<script>
    document.addEventListener('keydown', function (event) {
        // Detecta si se presion� la tecla "Backspace"
        if (event.key === 'Backspace') {
            // Verifica si no hay un campo de texto editable activo
            const activeElement = document.activeElement;
            if (!activeElement || activeElement.tagName !== 'INPUT' && activeElement.tagName !== 'TEXTAREA') {
                event.preventDefault(); // Cancela la acci�n predeterminada
            }
        }
    });
</script>

   <script>  
   // Funci�n para mostrar el mensaje y luego ocultarlo  
   function mostrarMensaje(tipo) {  
       var mensaje = document.getElementById(tipo);  
       if (mensaje) {  
           mensaje.style.display = "block"; // Mostrar el mensaje  
           // Ocultar el mensaje despu�s de 3 segundos (3000 milisegundos)  
           setTimeout(function() {  
               mensaje.style.display = "none";  
           }, 3000);  
       }  
   }  

   <% if(request.getAttribute("mensajeExito") != null) { %>  
       mostrarMensaje("successMessage");  
   <% } else if(request.getAttribute("mensajeError") != null) { %>  
       mostrarMensaje("errorMessage");  
   <% } %> 
   
   document.getElementById('fechaNacimiento').addEventListener('input', function (event) {
       const fecha = event.target.value;
       const regex = /^\d{4}-\d{2}-\d{2}$/;

       if (!regex.test(fecha)) {
           alert('Selecciona una fecha v�lida desde el calendario.');
           event.target.value = '';
       }
   });

   
   const fechaNacimientoInput = document.getElementById('fechaNacimiento');
   const hoy = new Date().toISOString().split('T')[0]; // Obtiene la fecha actual en formato 'yyyy-MM-dd'
   fechaNacimientoInput.setAttribute('max', hoy);
        
    </script> 

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
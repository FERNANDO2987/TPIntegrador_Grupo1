<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Prestamo" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listado de Préstamos</title>
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Icono -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    
    
     <style>  
         .container {  
            margin-left: 5px; /* Elimina margen izquierdo */  
            padding-left: 1; /* Elimina padding izquierdo */  
        }  

        .dataTables_wrapper {  
            margin-top: 20px; /* Espacio superior para el contenedor DataTables */  
        }  

        .dataTables_filter {  
            display: flex;  
            align-items: center;  
            margin-bottom: 20px;  
        }  

        .dataTables_filter label {  
            margin-right: 10px;  
            font-weight: bold;  
        }  

        .dataTables_filter input {  
            width: 200px; /* Ancho  */  
            height: 30px; /* Altura */  
            padding: 5px;  
            border: 1px solid #ccc; /* Borde gris claro */  
            border-radius: 15px; /* Bordes redondeados */  
            font-size: 14px; /* Tamaño de fuente ajustado */  
            transition: border-color 0.3s; /* Transición para el borde */  
        }  

        .dataTables_filter input:focus {  
            border-color: #007bff; /* Borde azul al enfocar */  
            outline: none; /* Sin borde de enfoque */  
        }  

        /* Estilos para mover la paginación a la izquierda */  
        .dataTables_wrapper .dataTables_paginate {  
            float: left; /* Mueve la paginación hacia la izquierda */  
            margin-top: 10px; /* Espacio superior opcional */  
        }  

        .dataTables_wrapper .dataTables_length {  
            float: left; /* Asegura que la opción de longitud también flote a la izquierda */  
            margin-right: 20px; /* Espacio a la derecha de la opción de longitud */  
        }  

        .dataTables_wrapper .dataTables_filter {  
            float: right; /* Mueve el campo de búsqueda a la derecha */  
        }  
    </style> 
    
    <!-- jQuery and DataTables -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
   
    <script>  
        function ocultarMensaje() {  
            var mensaje = document.getElementById("successMessage");  
            if (mensaje) {  
                setTimeout(function() {  
                    mensaje.style.display = "none";  
                }, 3000);  
            }  
        }  
    </script> 

    
  <script>  

  $(document).ready(function() {  
      $('#table_id').DataTable({  
          lengthMenu: [  
              [5, 10, 25, -1],  
              ['5', '10', '25', 'Ver todos'] // Opciones del menú  
          ],  
          language: {  
              lengthMenu: "_MENU_ mostrar", 
              info: "Mostrando _START_ a _END_ de _TOTAL_ mostrar",   
              search: "Filtrar:",  
              searchPlaceholder: "Escribe para buscar..."  
          },  
          pageLength: 5 // Mostrar los primeros 5 registros  
      });
      
      // Mostrar el mensaje si existe  
      var mensajeExito = '<%= request.getAttribute("successMessage") != null ? request.getAttribute("successMessage") : "" %>';  
      if (mensajeExito) {  
          $('#successMessage').show(); // Mostrar el mensaje  
          ocultarMensaje(); // Llama a la función para ocultar el mensaje  
      }  
      
      // Mostrar el mensaje de error si existe  
      var mensajeError = '<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>';  
      if (mensajeError) {  
          $('#errorMessage').show(); // Mostrar el mensaje de error  
          ocultarMensaje("errorMessage"); // Llama a la función para ocultar el mensaje  
      }  
  });  
  
  function openModal(idPrestamo) {
      
      $('#prestamoId').val(idPrestamo);  // Seteamos el ID del préstamo en el modal
      $('#modalAutorizar').modal('show'); // Mostramos el modal
  }
  

  function openModalRechazar(idPrestamo) {
      $('#prestamoIdRechazar').val(idPrestamo);  // Seteamos el ID del préstamo en el modal de rechazo
      $('#modalRechazar').modal('show'); // Mostramos el modal
  }




  
    </script> 
    <script type="text/javascript"></script>
    
     <style>
        /* Asegú
  
        /* Asegúrate de que el contenedor ocupe el 100% del ancho */  
        table {  
            width: 100%;  
        }  

        /* Si es necesario, puedes agregar más estilos aquí para las celdas */  
        td, th {  
            white-space: nowrap; /* Evita que el texto se corte */  
        }  
    </style>  
</head>
<body>

   <!-- Mensaje de éxito -->
        <div class="alert alert-success" role="alert" id="successMessage" style="display:none;">
            ${successMessage}
        </div>

        <!-- Mensaje de error -->
        <div class="alert alert-danger" role="alert" id="errorMessage" style="display:none;">
            ${errorMessage}
        </div>

    <div class="container mt-5">
        <h2 class="text-center mb-4">Listado de Préstamos</h2>
        


        
        
        <%
            //
            List<Prestamo> listaPrestamos = (List<Prestamo>) request.getAttribute("prestamos");
            if (listaPrestamos == null || listaPrestamos.isEmpty()) {
        %>
            <div class="alert alert-warning" role="alert">
                No se encontraron préstamos para mostrar.
            </div>
        <%
            } else {
        %>
        
        <!-- Tabla de Préstamos -->
        <table id="table_id" class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Cliente</th>
                    <th>Correo</th>
                    <th>Teléfono</th>
                    <th>CBU</th>
                    <th>Fecha Solicitud</th>
                    <th>Importe</th>
                    <th>Cuotas</th>
                    <th>Estado</th>
                    <th>Observaciones</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Prestamo p : listaPrestamos) {
                        String clienteNombre = p.getCliente() != null ? p.getCliente().getNombre() : "Desconocido";
                        String clienteApellido = p.getCliente() != null ? p.getCliente().getApellido() : "";
                        String clienteCorreo = p.getCliente() != null ? p.getCliente().getCorreo() : "No disponible";
                        String clienteTelefono = p.getCliente() != null ? p.getCliente().getTelefono() : "No disponible";
                        String cbu = p.getCuenta() != null ? p.getCuenta().getCbu() : "No disponible";
                        String observaciones = p.getObservaciones() != null ? p.getObservaciones() : "Sin observaciones";
                %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= clienteNombre %> <%= clienteApellido %></td>
                    <td><%= clienteCorreo %></td>
                    <td><%= clienteTelefono %></td>
                    <td><%= cbu %></td>
                    <td><%= p.getFechaSolicitud() %></td>
                    <td>$<%= p.getImporte() %></td>
                    <td><%= p.getCuotas() %></td>
                  <td><%= p.isEstado() ? "Autorizado" : "Rechazado" %></td>


                    <td><%= observaciones %></td>
                  <td>
                       <button type="button" class="btn btn-success" 
                              style="font-size: 0.75rem; padding: 0.25rem 0.4rem; border-radius: 0.2rem;" 
                              onclick="openModal(<%= p.getId() %>)" title="Autorizar préstamo solicitado">
                              <i class="fas fa-check"></i>
                      </button>
                      
                             <button type="button" class="btn btn-danger" 
                            style="font-size: 0.75rem; padding: 0.25rem 0.4rem; border-radius: 0.2rem;" 
                            onclick="openModalRechazar(<%= p.getId() %>)" title="Rechazar préstamo solicitado">
                            <i class="fas fa-times"></i>
                    </button>
                          
                      
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            }
        %>
        <br>
    <a class="btn btn-secondary" href="Home.jsp"   
       style="width: 400px; height: 40px; font-size: 18px; display: block; margin: 0 auto;" title="Volver al Home" >  
       Volver  
    </a> 
        <br>
    </div>
    
    
    <div class="modal fade" id="modalAutorizar" tabindex="-1" role="dialog" aria-labelledby="modalAutorizarLabel" aria-hidden="true">  
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <h5 class="modal-title" id="modalAutorizarLabel">Autorizar Préstamo</h5>  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true">&times;</span>  
                </button>  
            </div>  
            <div class="modal-body">  
                <form method="post" action="servletAutorizarPrestamo">  
                    <input type="hidden" id="prestamoId" name="prestamoId">  
                    <div class="form-group">  
                        <label for="comentarios">Comentarios</label>  
                        <textarea class="form-control" id="comentarios" name="comentarios" rows="4" required></textarea>  
                    </div>  
                    <div class="d-flex justify-content-end">
                        <button type="button" class="btn btn-secondary mr-2" data-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Autorizar</button>  
                    </div>
                </form>  
            </div>  
        </div>  
    </div>  
</div>  
  
  
  <div class="modal fade" id="modalRechazar" tabindex="-1" role="dialog" aria-labelledby="modalRechazarLabel" aria-hidden="true">  
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <h5 class="modal-title" id="modalRechazarLabel">Rechazar Préstamo</h5>  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true">&times;</span>  
                </button>  
            </div>  
            <div class="modal-body">  
                <form method="post" action="servletRechazaPrestamo">  
                    <input type="hidden" id="prestamoIdRechazar" name="prestamoIdRechazar">  
                    <div class="form-group">  
                        <label for="comentarios">Comentarios</label>  
                        <textarea class="form-control" id="comentarios" name="comentarios" rows="4" required></textarea>  
                    </div>  
                    <div class="d-flex justify-content-end">
                        <button type="button" class="btn btn-secondary mr-2" data-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-danger">Rechazar</button>  
                    </div>
                </form>  
            </div>  
        </div>  
    </div>  
</div>


 



     <!-- Bootstrap JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ page import="entidad.Prestamo" %>  
<%@ page import="entidad.Cliente" %>  
<%@ page import="entidad.Movimiento" %>  
<%@ page import="java.util.List" %>  
<%@ page import="java.util.Map" %>  
<!DOCTYPE html>  
<html lang="es">  
<head>  
    <meta charset="UTF-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    <title>Préstamos Solicitados</title>  
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">  
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">  
    <style>  
        body {  
            font-family: 'Roboto', sans-serif;  
            background-color: #f4f7fc;  
        }  
        .container {  
            background-color: #ffffff;  
            border-radius: 10px;  
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);  
            padding: 20px;  
        }  
        .page-header {  
            text-align: center;  
            margin-bottom: 30px;  
            color: #004d99;  
        }  
        .card {  
            border: none;  
            border-radius: 8px;  
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);  
        }  
        .card-header {  
            background-color: #004d99;  
            color: #fff;  
            text-align: center;  
            padding: 10px;  
            font-size: 1.2rem;  
        }  
        .btn-custom {  
            background-color: #007bff;  
            color: white;  
            border-radius: 5px;  
            border: none;  
        }  
        .btn-custom:hover {  
            background-color: #0056b3;  
        }  
        .alert {  
            border-radius: 5px;  
        }  
        .form-control {  
            border-radius: 5px;  
            height: 40px;  
        }  
    </style>  
</head>  
<body>  
    <%  
        Cliente usuario = (Cliente) session.getAttribute("usuario");  
        if (usuario == null) {  
            response.sendRedirect("Login.jsp");  
            return;  
        }  
    %>  

    <div class="container mt-5 mb-5">  
        <div class="page-header">  
            <h3>Préstamos Solicitados</h3>  
        </div>  

        <div class="row mb-4">  
            <div class="col-md-12 text-center">  
                <form method="get" action="servletPrestamoSolicitado">  
                    <input type="submit" name="btnListarPrestamos" value="Ver Mis Préstamos" class="btn btn-custom">  
                    <input type="hidden" name="idusuario" value="<%= usuario.getId() %>">  
                </form>  
            </div>  
        </div>  

        <div class="row">  
            <%  
                Map<Prestamo, List<Movimiento>> prestamosConMovimientos = (Map<Prestamo, List<Movimiento>>) request.getAttribute("prestamosConMovimientos");  
                if (prestamosConMovimientos == null || prestamosConMovimientos.isEmpty()) {  
            %>  
                <div class="alert alert-warning" role="alert">  
                    No se encontraron préstamos asociados a tu cuenta.  
                </div>  
            <% } else {  
                    for (Map.Entry<Prestamo, List<Movimiento>> entry : prestamosConMovimientos.entrySet()) {  
                        Prestamo prestamo = entry.getKey();  
                        List<Movimiento> movimientos = entry.getValue();  
            %>  
                <div class="col-md-12 mb-4">  
                    <div class="card">  
                        <div class="card-header">  
                            ID Préstamo: <%= prestamo.getId() %>  
                        </div>  
                        <div class="card-body">  
                            <p><strong>Fecha de Solicitud:</strong> <%= prestamo.getFechaSolicitud() %></p>  
                            <p><strong>Importe Total:</strong> $<%= prestamo.getImporte() %></p>  
                            <p><strong>Cuotas:</strong> <%= prestamo.getCuotas() %></p>  
                            <p><strong>Cuenta Asociada:</strong> Cuenta N°: <%= prestamo.getCuenta().getNroCuenta() %> - Saldo: $<%= prestamo.getCuenta().getSaldo() %></p>  
                        </div>  
                        <div class="card-footer">  
                            <strong>Movimientos:</strong>  
                            <ul>  
                                <%  
                                    for (Movimiento mov : movimientos) {  
                                %>  
                                    <li><strong>Detalle:</strong> <%= mov.getDetalle() %> - <strong>Importe:</strong> $<%= mov.getImporte() %> - <strong>Tipo:</strong> <%= mov.getTipoMovimiento().getDescripcion() %></li>  
                                <%  
                                    }  
                                %>  
                            </ul>  

                            <!-- Formulario para pagar el préstamo -->
                            <form method="post" action="servletProcesarPagoPrestamo" id="pagoPrestamoForm" oninput="validarFormulario()">  
                                <input type="hidden" name="idPrestamo" value="<%= prestamo.getId() %>">
                                

                                <!-- Selección de cuenta -->
                               <select name="cuentaSelect" id="cuentaSelect" class="form-control" required>
 
                                <option value="<%= prestamo.getCuenta().getNroCuenta() %>">
                                                    Cuenta N°: <%= prestamo.getCuenta().getNroCuenta() %>
                           </option>
                             </select>

                                 
                                   <input type="hidden" id="numeroCuota" value="<%= prestamo.getCuotas() %>">
                                <!-- Importe del pago -->
                                <div class="form-group">
                                    <label for="numeroCuota">N° Cuota:</label>
                                    <input type="number" name="numeroCuota" id="numeroCuota" class="form-control" required>
                                </div>
                                  
                                  
                                <!-- Campo oculto para el total de las cuotas del préstamo -->
                                <input type="hidden" id="totalCuotas" value="<%= prestamo.getImporte() %>">
                                    
                                <div class="form-group">
                                    <label for="importePago">Importe a Pagar:</label>
                                    <input type="number" name="importePago" id="importePago" class="form-control" required>
                                </div>

                             
                                    

                                <div class="form-group">
                                    <label for="detallePago">Detalle del Pago:</label>
                                    <select name="detallePago" id="detallePago" class="form-control" required>
                                        <option value="PagoParcial">Pago Parcial</option>
                                        <option value="PagoTotal">Pago Total</option>
                                    </select>
                                </div>

                                <!-- Botón para pagar el préstamo -->
                                <input type="submit" value="Pagar Préstamo" class="btn btn-custom" id="btnPagar" disabled>
    
                            </form>  
                            
     
                        </div>  
                    </div>  
                </div>  
            <%  
                    }  
                }  
            %>  
        </div>  
                                                                               <a class="btn btn-secondary" href="Home.jsp"   
       style="width: 165px; height: 38px; font-size: 18px; display: block; margin: 0 auto;" title="Volver al Home" >  
       Volver  
    </a> 
        <!-- Mensaje de éxito o error -->  
        <div class="row mt-4">  
            <%  
                String mensaje = (String) request.getAttribute("mensaje");  
                if (mensaje != null) {  
            %>  
                <div class="alert alert-info" role="alert">  
                    <%= mensaje %>  
                </div>  
            <% } %>  
        </div>  
    </div>  

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>  
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>  
    <script>
        function validarFormulario() {
            var form = document.getElementById('pagoPrestamoForm');
            var btnPagar = document.getElementById('btnPagar');
            var todosCamposCompletos = true;

            // Verificar si todos los campos con 'required' están completos
            var camposRequeridos = form.querySelectorAll('[required]');
            for (var i = 0; i < camposRequeridos.length; i++) {
                if (!camposRequeridos[i].value) {
                    todosCamposCompletos = false;
                    break;
                }
            }

            // Habilitar el botón solo si todos los campos están completos
            btnPagar.disabled = !todosCamposCompletos;
        }

        // Función para verificar el importe y la cuota
            function verificarPago() {
    const cuota = parseInt(document.getElementById("numeroCuota").value);
    const importeTotal = parseFloat(document.getElementById("totalCuotas").value);
    const importePago = parseFloat(document.getElementById("importePago").value);
    const cuotastotal = parseFloat(document.getElementById("Cuotastotal").value);
    
    Cuotastotal

    // Limpiar el campo si el valor es menor a cero
    if (importePago < 0) {
        document.getElementById("importePago").value = "";
    }

    if (cuota < 0) {
        document.getElementById("numeroCuota").value = "";
    }

    // Validar si el importePago supera lo correspondiente a la cuota
    if (importePago > (importeTotal / cuota)) {
        alert("El importe no puede superar lo correspondiente.");
    }

    // Validar si la cuota supera el importe total
    if (cuota < cuotastotal) {
        alert("La cuota no puede ser mayor.");
    }
}



        // Añadir evento para validar el importe y las cuotas
        document.getElementById("importePago").addEventListener("input", verificarPago);
        document.getElementById("numeroCuota").addEventListener("input", verificarPago);
    </script>

</body>  
</html>  

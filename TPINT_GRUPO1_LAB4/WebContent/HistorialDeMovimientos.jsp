<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<title>Historial de movimientos</title>
</head>
<body>

	<!-- SE TIENE QUE DESARROLLAR LA LÓGICA DE ESTA PÁGINA EN LA QUE
		 SE RECIBA POR PARAMETRO LA CUENTA DESDE LA CUÁL FUE ABIERTA
		 ESTA PÁGINA. SUMAR EN LA PÁGINA VerCuentasAsociadas.jsp 
		 EL ENVÍO DE LOS PARÁMETROS. TAMBIÉN EL SERVLET Y LA LOGICA
		 PARA QUE FUNCIONE FILTRAR POR FECHA Y LA OBTENCION DE DATOS.  -->

	<div class="container mt-5">
        <h2 class="text-center mb-4">Historial de movimientos</h2>
        
        <!-- Formulario de busqueda -->
       <form action="ServletACrear" method="get" class="form-inline mb-4" id="formBusqueda">
            <input type="date" name="criterio" class="form-control mr-2" id="criterio">
            <button type="submit" class="btn btn-primary mr-2">Buscar</button>
        </form>
        
        <!-- Fecha, Detalle, importe, tipo de movimiento,  -->
        
        <!-- Tabla de movimientos -->
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>Importe</th>
                    <th>Tipo de movimiento</th>
                    <th>Detalle</th>
                    <th>Fecha</th>
                    
                </tr>
            </thead>
            <tbody>
    <tr>
        <td>$10.000,00</td>
        <td>Alta de cuenta</td>
        <td>Apertura de cuenta</td>
        <td>20-10-2024</td>
    </tr>
    <tr>
        <td>$200.777,00</td>
        <td>Transferencia</td>
        <td>Recibiste una transferencia de 54121314879162 por ARS 200.777</td>
        <td>24-10-2024</td>
    </tr>
    <tr>
        <td>$940.000,00</td>
        <td>Alta de préstamo</td>
        <td>Recibiste ARS 940.000 vía prestamo</td>
        <td>24-10-2024</td>
    </tr>
    <tr>
        <td>$79.548,00</td>
        <td>Pago de préstamo</td>
        <td>Hiciste pago de cuota de prestamo por ARS 79.548</td>
        <td>05-11-2024</td>
    </tr>
    <tr>
        <td>$34.000,00</td>
        <td>Transferencia</td>
        <td>Recibiste una transferencia de 54121314842698 por ARS 34.000</td>
        <td>07-11-2024</td>
    </tr>
</tbody>

        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pagar Cuotas de Préstamo</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Pagar Cuotas de Préstamo</h2>
        <form action="servletPagoCuotas" method="POST">
            <div class="form-group">
                <label for="numeroPrestamo">Préstamo a pagar:</label>
                
                <select class="form-control" id="prestamos" name="prestamos" required >
                <option value="1">Préstamo 1 (cuota 3/3)</option>
                <option value="2">Préstamo 2 (cuota 1/5)</option>
                <option value="3">Préstamo 3 (cuota 6/12)</option>
                </select>
            </div>
            <div class="form-group">
                <label for="montoCuota">Monto de la Cuota:</label>
                <input type="number" class="form-control" id="montoCuota" name="montoCuota" readonly placeholder="Ingrese el monto a pagar" required>
            </div>
            <div class="form-group">
                <label for="fechaPago">Fecha de Pago:</label>
                <input type="date" class="form-control" id="fechaPago" name="fechaPago" readonly required>
            </div>
            <div class="form-group">
                <label for="metodoPago">Cuenta seleccionada:</label>
                <select class="form-control" id="cuentaSeleccionada" name="cuentaSeleccionada" required>
                    <option value="">Seleccionar</option>
                    <option value="15648945648651">15648945648651</option>
                    <option value="18797894565641">18797894565641</option>
                    <option value="18797894533333">1879789453333</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Pagar Cuota</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

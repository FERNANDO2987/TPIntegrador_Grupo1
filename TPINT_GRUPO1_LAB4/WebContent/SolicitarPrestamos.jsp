<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Solicitar Préstamo</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Solicitar Préstamo</h2>
        <form action="servletPrestamo" method="POST">
            <div class="form-group">
                <label for="tipoPrestamo">Tipo de Préstamo:</label>
                <select class="form-control" id="tipoPrestamo" name="tipoPrestamo" required>
                    <option value="">Seleccionar</option>
                    <option value="Personal">Préstamo Personal</option>
                    <option value="Hipotecario">Préstamo Hipotecario</option>
                    <option value="Automotriz">Préstamo Automotriz</option>
                </select>
            </div>
            <div class="form-group">
                <label for="monto">Monto Solicitado:</label>
                <input type="number" class="form-control" id="monto" name="monto" placeholder="Ingrese el monto a solicitar" required>
            </div>
            <div class="form-group">
                <label for="plazo">Plazo (en meses):</label>
                <input type="number" class="form-control" id="plazo" name="plazo" placeholder="Ingrese el plazo en meses" required>
            </div>
            
            <div class="form-group">
                <label for="metodoPago">Cuenta destino:</label>
                <select class="form-control" id="cuentaDestino" name="cuentaDestino" required>
                    <option value="">Seleccionar</option>
                    <option value="15648945648651">15648945648651</option>
                    <option value="18797894565641">18797894565641</option>
                    <option value="18797894533333">1879789453333</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Solicitar Préstamo</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

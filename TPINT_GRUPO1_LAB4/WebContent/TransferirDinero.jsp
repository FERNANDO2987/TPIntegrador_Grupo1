<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Transferir Dinero</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Transferir Dinero</h2>
        <form action="servletTransferencia" method="POST">
            <div class="form-group">
                <label for="cuentaOrigen">Cuenta Origen:</label>
                <select class="form-control" id="cuentaOrigen" name="cuentaOrigen" required>
                    <option value="">Seleccionar</option>
                    <option value="12345678">12345678 - Cuenta Ahorros</option>
                    <option value="87654321">87654321 - Cuenta Corriente</option>
                </select>
            </div>
            <div class="form-group">
                <label for="cuentaDestino">Cuenta Destino:</label>
                <input type="text" class="form-control" id="cuentaDestino" name="cuentaDestino" placeholder="Ingrese el número de cuenta" required>
            </div>
            <div class="form-group">
                <label for="importe">Importe:</label>
                <input type="number" class="form-control" id="importe" name="importe" placeholder="Ingrese la cantidad a transferir" required>
            </div>
            <div class="form-group">
                <label for="detalle">Detalle (opcional):</label>
                <input type="text" class="form-control" id="detalle" name="detalle" placeholder="Descripción de la transferencia">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Transferir</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

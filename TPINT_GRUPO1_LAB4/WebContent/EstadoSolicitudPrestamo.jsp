<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ver Estado de Solicitud de Préstamo</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Estado de Solicitud de Préstamo</h2>
        <form action="servletEstadoSolicitud" method="POST">
            <div class="form-group">
                <label for="numeroSolicitud">Número de Solicitud:</label>
                <input type="text" class="form-control" id="numeroSolicitud" name="numeroSolicitud" placeholder="Ingrese el número de solicitud" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Consultar Estado</button>
        </form>

        <!-- Sección para mostrar el estado de la solicitud -->
        <div class="mt-4">
            <h5>Estado de la Solicitud</h5>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Fecha de Solicitud</th>
                        <th>Numero de prestamo</th>
                        <th>Numero de cuenta</th>
                        <th>Importe</th>
                        <th>Estado</th>
                        <th>Comentario</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>01/11/2024</td>
                        <td>1</td>
                        <td>123</td>
                        <td>$18.500,00</td>
                        <td>Aprobada</td>
                        <td>Su préstamo ha sido aprobado. Se le enviará una notificación con los detalles.</td>
                    </tr>
                    <tr>
                        <td>28/10/2024</td>
                        <td>2</td>
                        <td>128</td>
                        <td>$2.500,00</td>
                        <td>En Proceso</td>
                        <td>Su solicitud está siendo revisada.</td>
                    </tr>
                    <tr>
                        <td>25/10/2024</td>
                        <td>3</td>
                        <td>124</td>
                        <td>$12.500,00</td>
                        <td>Rechazada</td>
                        <td>Se necesita más información para procesar su solicitud.</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

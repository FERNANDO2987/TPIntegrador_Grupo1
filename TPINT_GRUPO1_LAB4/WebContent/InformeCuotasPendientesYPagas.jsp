<%@ page import="java.net.URLEncoder" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Informe de Ingresos y Egresos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        #chartTorta {
            width: 300px;
            height: 300px;
            margin: auto;
        }
    </style>
 
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Informe de Cuotas Pagas y Pendientes</h2>

        <!-- Formulario para ingresar las fechas -->
        <form method="GET" action="servletGenerarReporteCuotas" class="mb-4">
            <div class="form-row">
                <div class="col-md-5">
                    <label for="fechaInicio">Fecha Inicio:</label>
                    <input type="date" id="fechaInicio" name="fechaInicio" class="form-control"
                           value="<%= request.getAttribute("fechaInicio") != null ? request.getAttribute("fechaInicio") : "" %>" onchange="habilitarBotones()">
                </div>
                <div class="col-md-5">
                    <label for="fechaFin">Fecha Fin:</label>
                    <input type="date" id="fechaFin" name="fechaFin" class="form-control"
                           value="<%= request.getAttribute("fechaFin") != null ? request.getAttribute("fechaFin") : "" %>" onchange="habilitarBotones()">
                </div>
                <div class="col-md-2 align-self-end">
                    <button type="submit" class="btn btn-primary btn-block" id="btnGenerar" disabled>Generar Informe</button>
                </div>
            </div>
        </form>

        <%   
            Double[] totalPagadoPorMes = (Double[]) request.getAttribute("totalPagadoPorMes");
            Double[] totalPendientePorMes = (Double[]) request.getAttribute("totalPendientePorMes");
            String fechaInicio = (String) request.getAttribute("fechaInicio");
            String fechaFin = (String) request.getAttribute("fechaFin");

            if (totalPagadoPorMes == null || totalPendientePorMes == null) {
                totalPagadoPorMes = new Double[12];
                totalPendientePorMes = new Double[12];
                for (int i = 0; i < 12; i++) {
                    totalPagadoPorMes[i] = 0.0;
                    totalPendientePorMes[i] = 0.0;
                }
            }

            if (fechaInicio == null) fechaInicio = "";
            if (fechaFin == null) fechaFin = "";
        %>

        <!-- Mostrar los resultados si existen -->
        <p><strong>Periodo:</strong> <%= fechaInicio %> a <%= fechaFin %></p>

        <div class="row">
            <div class="col-md-6">
                <canvas id="chartIngresos"></canvas>
            </div>
            <div class="col-md-6">
                <canvas id="chartEgresos"></canvas>
            </div>
        </div>

        <!-- Nuevo gráfico de torta -->
        <div class="row mt-4">
            <div class="col-md-12 text-center">
                <h3>Distribución de Pagos y Pendientes</h3>
                <canvas id="chartTorta"></canvas>
            </div>
        </div>

<br>
        <div class="row mt-4">
            <div class="col-md-4">
                <button class="btn btn-success w-100"
                        id="btnGenerarPDF"
                        onclick="window.location.href='servletGenerarPDFCuotasPagas?fechaInicio=<%= URLEncoder.encode(fechaInicio, "UTF-8") %>&fechaFin=<%= URLEncoder.encode(fechaFin, "UTF-8") %>'"
                        disabled>Generar Reporte PDF
                </button>
            </div>
            <div class="col-md-4">
                <a href="InformeCuotasPendientesYPagas.jsp" class="btn btn-danger w-100"
                   id="btnLimpiar"
                   onclick="return confirm('¿Estás seguro de que deseas limpiar el reporte? Perderás los datos.')">
                    Limpiar
                </a>
            </div>
            <div class="col-md-4">
                <a href="Home.jsp" class="btn btn-secondary w-100"
                   onclick="return confirm('¿Estás seguro de que deseas volver al Home? Perderás los datos no guardados.')">
                    Volver
                </a>
            </div>
        </div>
        <br>
    </div>

    <script>
    
    // Función para habilitar el botón de generar informe y el de generar PDF
    function habilitarBotonGenerar() {
        const fechaInicio = document.getElementById('fechaInicio').value;
        const fechaFin = document.getElementById('fechaFin').value;
        const btnGenerar = document.getElementById('btnGenerar');
        const btnGenerarPDF = document.getElementById('btnGenerarPDF');

        // Si ambas fechas están completas, habilitamos los botones
        if (fechaInicio && fechaFin) {
            btnGenerar.disabled = false;
            btnGenerarPDF.disabled = false;
        } else {
            btnGenerar.disabled = true;
            btnGenerarPDF.disabled = true;
        }
    }
    
    // Llamamos a la función al cargar la página
    habilitarBotonGenerar();
        // Habilitar botones dinámicamente
        function habilitarBotones() {
            const fechaInicio = document.getElementById('fechaInicio').value;
            const fechaFin = document.getElementById('fechaFin').value;
            const btnGenerar = document.getElementById('btnGenerar');
            const btnGenerarPDF = document.getElementById('btnGenerarPDF');

            const habilitado = fechaInicio && fechaFin;
            btnGenerar.disabled = !habilitado;
            btnGenerarPDF.disabled = !habilitado;
        }

        habilitarBotones();

        // Data para gráficos
        const totalPagadoPorMes = <%= java.util.Arrays.toString(totalPagadoPorMes).replace("[", "[").replace("]", "]") %>;
        const totalPendientePorMes = <%= java.util.Arrays.toString(totalPendientePorMes).replace("[", "[").replace("]", "]") %>;
        const labels = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

        // Gráficos
        new Chart(document.getElementById('chartIngresos'), {
            type: 'bar',
            data: {
                labels,
                datasets: [{
                    label: 'Pagos Realizados',
                    data: totalPagadoPorMes,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: { scales: { y: { beginAtZero: true } } }
        });

        new Chart(document.getElementById('chartEgresos'), {
            type: 'bar',
            data: {
                labels,
                datasets: [{
                    label: 'Pendientes de Pago',
                    data: totalPendientePorMes,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }]
            },
            options: { scales: { y: { beginAtZero: true } } }
        });

        const totalPagado = totalPagadoPorMes.reduce((a, b) => a + b, 0);
        const totalPendiente = totalPendientePorMes.reduce((a, b) => a + b, 0);

        new Chart(document.getElementById('chartTorta'), {
            type: 'pie',
            data: {
                labels: ['Pagos Realizados', 'Pendientes de Pago'],
                datasets: [{
                    data: [totalPagado, totalPendiente],
                    backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)'],
                    borderColor: ['rgba(75, 192, 192, 1)', 'rgba(255, 99, 132, 1)'],
                    borderWidth: 1.5
                }]
            },
            options: {
                responsive: true,
                plugins: { legend: { position: 'top' } }
            }
        });
    </script>
</body>
</html>

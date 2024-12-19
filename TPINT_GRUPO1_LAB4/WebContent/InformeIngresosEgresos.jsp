<%@ page import="java.net.URLEncoder" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Informe de Ingresos y Egresos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Informe de Ingresos y Egresos</h2>

        <!-- Formulario para ingresar las fechas -->
        <form method="GET" action="servletGenerarInformes" class="mb-4">
            <div class="form-row">
                <div class="col-md-5">
                    <label for="fechaInicio">Fecha Inicio:</label>
                    <input type="date" id="fechaInicio" name="fechaInicio" class="form-control"
                           value="<%= request.getAttribute("fechaInicio") != null ? request.getAttribute("fechaInicio") : "" %>" onchange="habilitarBotonGenerar()">
                </div>
                <div class="col-md-5">
                    <label for="fechaFin">Fecha Fin:</label>
                    <input type="date" id="fechaFin" name="fechaFin" class="form-control"
                           value="<%= request.getAttribute("fechaFin") != null ? request.getAttribute("fechaFin") : "" %>" onchange="habilitarBotonGenerar()">
                </div>
                <div class="col-md-2 align-self-end">
                    <button type="submit" class="btn btn-primary btn-block" id="btnGenerar" disabled>Generar Informe</button>
                </div>
            </div>
        </form>

        <%   
            Double[] ingresosPorMes = (Double[]) request.getAttribute("ingresosPorMes");
            Double[] egresosPorMes = (Double[]) request.getAttribute("egresosPorMes");
            String fechaInicio = (String) request.getAttribute("fechaInicio");
            String fechaFin = (String) request.getAttribute("fechaFin");

            if (ingresosPorMes == null || egresosPorMes == null) {
                ingresosPorMes = new Double[12];
                egresosPorMes = new Double[12];
                for (int i = 0; i < 12; i++) {
                    ingresosPorMes[i] = 0.0;
                    egresosPorMes[i] = 0.0;
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

        <!-- Botón para generar PDF -->
     
        
        <br>
        <div class="row mt-4">
            <div class="col-md-4">
                   <button class="btn btn-success w-100"
                id="btnGenerarPDF"
                onclick="window.location.href='servletGenerarPDF?fechaInicio=<%= URLEncoder.encode(fechaInicio, "UTF-8") %>&fechaFin=<%= URLEncoder.encode(fechaFin, "UTF-8") %>'"
                disabled>Generar Reporte PDF
        </button>
            </div>
         <div class="col-md-4">
                <a href="InformeIngresosEgresos.jsp" class="btn btn-danger w-100"
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

        <!-- Botón para limpiar los gráficos y las fechas -->
        

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
            const ingresosPorMes = <%= java.util.Arrays.toString(ingresosPorMes).replace("[", "[").replace("]", "]") %>;
            const egresosPorMes = <%= java.util.Arrays.toString(egresosPorMes).replace("[", "[").replace("]", "]") %>;

            const labels = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                            'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

            const ctxIngresos = document.getElementById('chartIngresos');
            new Chart(ctxIngresos, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Ingresos',
                        data: ingresosPorMes,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: { y: { beginAtZero: true } }
                }
            });

            const ctxEgresos = document.getElementById('chartEgresos');
            new Chart(ctxEgresos, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Egresos',
                        data: egresosPorMes,
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: { y: { beginAtZero: true } }
                }
            });

            // Función para limpiar los gráficos y las fechas
            function limpiarCampos() {
                // Limpiar las fechas
                document.getElementById('fechaInicio').value = '';
                document.getElementById('fechaFin').value = '';

                // Limpiar los gráficos
                const ctxIngresos = document.getElementById('chartIngresos').getContext('2d');
                const ctxEgresos = document.getElementById('chartEgresos').getContext('2d');

                // Eliminar los gráficos
                ctxIngresos.clearRect(0, 0, ctxIngresos.canvas.width, ctxIngresos.canvas.height);
                ctxEgresos.clearRect(0, 0, ctxEgresos.canvas.width, ctxEgresos.canvas.height);

                // Re-inicializar los gráficos con valores vacíos
                new Chart(ctxIngresos, {
                    type: 'bar',
                    data: {
                        labels: [],
                        datasets: [{
                            label: 'Ingresos',
                            data: [],
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: { y: { beginAtZero: true } }
                    }
                });

                new Chart(ctxEgresos, {
                    type: 'bar',
                    data: {
                        labels: [],
                        datasets: [{
                            label: 'Egresos',
                            data: [],
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: { y: { beginAtZero: true } }
                    }
                });

                // Deshabilitar los botones
                habilitarBotonGenerar();
            }
        </script>
    </div>
</body>
</html>

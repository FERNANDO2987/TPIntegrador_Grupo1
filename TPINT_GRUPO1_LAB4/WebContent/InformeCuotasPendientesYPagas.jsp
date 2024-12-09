<%@ page import="java.net.URLEncoder" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Informe de Ingresos y Egresos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
    
        <style>  
    #chartTorta {  
        width: 1px; /* Cambia este valor al tamaño deseado */  
        height: 1px; /* Cambia este valor al tamaño deseado */  
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
            <div class="col-md-12">
                <h3 class="text-center">Distribución de Pagos y Pendientes</h3>
                <!-- Asegúrate de que el tamaño del canvas sea adecuado -->
                <canvas id="chartTorta" width="10" height="10"></canvas>
            </div>
        </div>

        <!-- Botón para generar PDF -->
        <button class="btn btn-success mt-4"
                id="btnGenerarPDF"
                onclick="window.location.href='servletGenerarPDFCuotasPagas?fechaInicio=<%= URLEncoder.encode(fechaInicio, "UTF-8") %>&fechaFin=<%= URLEncoder.encode(fechaFin, "UTF-8") %>'"
                disabled>Generar Reporte PDF
        </button>

        <!-- Botón para limpiar los gráficos y las fechas -->
        <button type="button" class="btn btn-secondary mt-4" id="btnLimpiar" onclick="limpiarCampos()">Limpiar</button>

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

            const totalPagadoPorMes = <%= java.util.Arrays.toString(totalPagadoPorMes).replace("[", "[").replace("]", "]") %>;
            const totalPendientePorMes = <%= java.util.Arrays.toString(totalPendientePorMes).replace("[", "[").replace("]", "]") %>;

            const labels = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
                            'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

            const ctxIngresos = document.getElementById('chartIngresos');
            new Chart(ctxIngresos, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Pagos Realizados',
                        data: totalPagadoPorMes,
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
                        label: 'Pendientes de Pago',
                        data: totalPendientePorMes,
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: { y: { beginAtZero: true } }
                }
            });

            // Gráfico de torta para distribución de pagos y pendientes  
            const totalPagado = totalPagadoPorMes.reduce((acc, curr) => acc + curr, 0);  
            const totalPendiente = totalPendientePorMes.reduce((acc, curr) => acc + curr, 0);  
            
            const ctxTorta = document.getElementById('chartTorta').getContext('2d');  
            new Chart(ctxTorta, {  
                type: 'pie',  
                data: {  
                    labels: ['Pagos Realizados', 'Pendientes de Pago'],  
                    datasets: [{  
                        data: [totalPagado, totalPendiente],  
                        backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)'],  
                        borderColor: ['rgba(75, 192, 192, 1)', 'rgba(255, 99, 132, 1)'],  
                        borderWidth: 1  
                    }]  
                },  
                options: {  
                    responsive: true,  

                    aspectRatio: 3.5, // Ajusta este valor según sea necesario  
                    plugins: {  
                        legend: {  
                            position: 'top',  
                        },  
                        tooltip: {  
                            callbacks: {  
                                label: function(tooltipItem) {  
                                    return tooltipItem.label + ': ' + tooltipItem.raw.toFixed(2);  
                                }  
                            }  
                        }  
                    }  
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
                            label: 'Pagos Realizados',
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
                            label: 'Pendientes de Pago',
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

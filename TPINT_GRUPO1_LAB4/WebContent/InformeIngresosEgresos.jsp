
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
                           value="<%= request.getAttribute("fechaInicio") != null ? request.getAttribute("fechaInicio") : "" %>">  
                </div>  
                <div class="col-md-5">  
                    <label for="fechaFin">Fecha Fin:</label>  
                    <input type="date" id="fechaFin" name="fechaFin" class="form-control"   
                           value="<%= request.getAttribute("fechaFin") != null ? request.getAttribute("fechaFin") : "" %>">  
                </div>  
                <div class="col-md-2 align-self-end">  
                    <button type="submit" class="btn btn-primary btn-block">Generar Informe</button>  
                </div>  
            </div>  
        </form>  

        <!-- Mostrar los resultados si existen -->  
        <%   
            Double[] ingresosPorMes = (Double[]) request.getAttribute("ingresosPorMes");  
            Double[] egresosPorMes = (Double[]) request.getAttribute("egresosPorMes");  
            String fechaInicio = (String) request.getAttribute("fechaInicio");  
            String fechaFin = (String) request.getAttribute("fechaFin");  
            
            String fechaInicioEncoded = (fechaInicio != null) ? URLEncoder.encode(fechaInicio, "UTF-8") : "";  
            String fechaFinEncoded = (fechaFin != null) ? URLEncoder.encode(fechaFin, "UTF-8") : "";  

            if (ingresosPorMes != null && egresosPorMes != null) {  
        %>  
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
        <button class="btn btn-success mt-4"   
                onclick="window.location.href='servletGenerarPDF?fechaInicio=<%= fechaInicioEncoded %>&fechaFin=<%= fechaFinEncoded %>'">  
            Generar Reporte PDF  
        </button>  
          
        <script>  
            
            const ingresosPorMes = [<%  
                for (int i = 0; i < ingresosPorMes.length; i++) {  
                    out.print(ingresosPorMes[i]);  
                    if (i < ingresosPorMes.length - 1) {  
                        out.print(", ");  
                    }  
                }  
            %>];  

            const egresosPorMes = [<%  
                for (int i = 0; i < egresosPorMes.length; i++) {  
                    out.print(egresosPorMes[i]);  
                    if (i < egresosPorMes.length - 1) {  
                        out.print(", ");  
                    }  
                }  
            %>];  

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
        </script>  
        <%   
            } else {  
        %>  
        <p class="text-center text-muted">Por favor, ingresa un rango de fechas para generar el informe.</p>  
        <%   
            }  
        %>  
    </div>  
</body>  
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Informes de Ingresos y Egresos</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Icono -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<div class="container mt-5">
		<div class="row" style="padding: 10px">
			<div class="col-4">
				<a class= "col btn btn-danger" href="#" style="display: inline;">Volver</a>
			</div>	
			<div class="col-8">
				<h2 class="text-center mb-4">Informe de Ingresos y Egresos</h2>
			</div>					
		</div>
		<div class="row" style="padding: 10px">
			<div class="col-4">
				<a class= "col btn btn-primary" href="#">Generar Informe</a>
			</div>
			<div class="col-8">
				<canvas id="myChart_ingresos" ></canvas>
			</div>							
		</div>
		<div class="row" style="padding: 10px">
			<div class="col-4">
				<a class= "col btn btn-primary" href="#">Generar Informe</a>
			</div>
			<div class="col-8">
				<canvas id="myChart_egresos" ></canvas>
			</div>	
		</div>
	</div>
	
	<!-- Graficas de ingresos y egresos -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script>
		const ctx1 = document.getElementById('myChart_ingresos');
		new Chart(ctx1, {
		  type: 'bar',
		  data: {
		    labels: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
		    datasets: [{
		      label: ' Ingresos',
		      data: [12, 19, 3, 5, 2, 3, 12, 19, 3, 5, 2, 3],  //Ejemplos de valor
		      backgroundColor:['rgba(1, 210, 70, 0.2)'],
		      borderWidth: 1
		    }]
		  },
		  options: {
		    scales: {
		      y: {
		        beginAtZero: true,
		        max: 20
		      }
		    }
		  }
		});
	</script>
	<script>
		const ctx2 = document.getElementById('myChart_egresos');
		new Chart(ctx2, {
		  type: 'bar',
		  data: {
		    labels: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
		    datasets: [{
		      label: ' Egresos',
		      data: [1, 10, 5, 5, 1, 3, 1, 10, 5, 5, 1, 3], //Ejemplos de valor
		      backgroundColor:['rgba(255, 82, 84, 0.2)'],
		      borderWidth: 1
		    }]
		  },
		  options: {
		    scales: {
		      y: {
		        beginAtZero: true,
		        max: 20
		      }
		    }
		  }
		});
	</script>
</body>
</html>
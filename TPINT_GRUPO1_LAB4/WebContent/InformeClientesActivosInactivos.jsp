<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Informes de Clientes Activos e Inactivos</title>
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
				<h2 class="text-center mb-4">Informe de Clientes Activos e Inactivos</h2>   
			</div>					
		</div>
		
		<div class="row" style="padding: 10px">
			<div class="col-4">
				<canvas id="myChart_Clientes" ></canvas>
			</div>	
			<div class="col-8">
				<canvas id="myChart_Clientes2" ></canvas>
			</div>					
		</div>
		<div class="row" style="padding: 10px">
			<div class="col-5">
				<form action="#" method="POST">
            		<div class="form-group">
            			<div class="row">
            				<div class="col">
	            				<input type="text" class="form-control" id="anio" name="anio" required placeholder="Año">
            				</div>
            				<div class="col">
            					<button type="submit" class="btn btn-dark">Aplicar cambios</button>
            				</div>
            			</div>
            		</div>
            	</form>
			</div>
			<div class="col-3">
				<label id="anio" style="font-size: 24px;">Año:</label>
			</div>	
			<div class="col-4">
				<a class= "col btn btn-primary" href="#">Generar Informe</a>
			</div>	
		</div>
	</div>	
	
	<!-- Graficas de Clientes -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script>
		const ctx1 = document.getElementById('myChart_Clientes');
		new Chart(ctx1, {
		  type: 'doughnut',
		  data: {
		    labels: ['Activos', 'Inactivos'],
		    datasets: [{
		      label: ' Ingresos',
		      data: [12, 5],  //Ejemplos de valor
		      backgroundColor:[
		          'rgb(54, 162, 235)',
		    	  'rgb(255, 99, 132)'
		      ],
		      borderWidth: 1
		    }]
		  },
		  options: {
		    scales: {
		      y: {
		        beginAtZero: true,
		      }
		    }
		  }
		});
	</script>
	<script>
		const ctx2 = document.getElementById('myChart_Clientes2');
		new Chart(ctx2, {
		  type: 'line',
		  data: {
		    labels: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
		    datasets: [{
		      label: 'Activos',
		      data: [12, 5, 1, 4, 5, 9, 3, 10, 10, 12, 15, 10],  //Ejemplos de valor
		      borderColor: 'rgb(117, 117, 117)',
		      backgroundColor:['rgb(54, 162, 235)'],
		      borderWidth: 1
		    }]
		  },
		  options: {
		    scales: {
		      y: {
		        beginAtZero: true,
		      }
		    }
		  }
		});
	</script>
</body>
</html>
// script.js  

// Función para ocultar el mensaje después de un tiempo  
function ocultarMensaje() {  
    var mensaje = document.getElementById("successMessage");  
    if (mensaje) {  
        setTimeout(function() {  
            mensaje.style.display = "none";  
        }, 3000); // Cambia 3000 por la cantidad de milisegundos que desees  
    }  
}  

// Función para mostrar el mensaje y luego ocultarlo  
function mostrarMensaje(tipo) {  
    var mensaje = document.getElementById(tipo);  
    if (mensaje) {  
        mensaje.style.display = "block"; // Mostrar el mensaje  
        // Ocultar el mensaje después de 3 segundos (3000 milisegundos)  
        setTimeout(function() {  
            mensaje.style.display = "none";  
        }, 3000);  
    }  
}  

document.getElementById('fechaNacimiento').addEventListener('input', function (event) {  
    const fecha = event.target.value;  
    const regex = /^\d{4}-\d{2}-\d{2}$/;  

    if (!regex.test(fecha)) {  
        alert('Selecciona una fecha válida desde el calendario.');  
        event.target.value = '';  
    }  
});  

const fechaNacimientoInput = document.getElementById('fechaNacimiento');  
const hoy = new Date().toISOString().split('T')[0]; // Obtiene la fecha actual en formato 'yyyy-MM-dd'  
fechaNacimientoInput.setAttribute('max', hoy);
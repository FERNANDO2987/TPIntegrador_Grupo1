//Habilitar el botón aceptar solo si ambos campos tienen texto
const usuarioInput = document.getElementById('usuario');
const contraseniaInput = document.getElementById('contrasenia');
const btnAceptar = document.getElementById('btnAceptar');

function toggleButtonState() {
    if (usuarioInput.value.trim() !== "" && contraseniaInput.value.trim() !== "") {
        btnAceptar.disabled = false;
    } else {
        btnAceptar.disabled = true;
    }
}

usuarioInput.addEventListener('input', toggleButtonState);
contraseniaInput.addEventListener('input', toggleButtonState);

// Inicializar el estado del botón al cargar la página
document.addEventListener('DOMContentLoaded', toggleButtonState);
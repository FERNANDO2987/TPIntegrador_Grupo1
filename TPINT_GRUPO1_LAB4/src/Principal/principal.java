package Principal;

<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.List;

import datosImpl.PrestamoDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import nogocioImpl.ClienteNegImpl;
import nogocioImpl.CuentaNegImpl;
=======
import datosImpl.PrestamoDaoImpl;
>>>>>>> Stashed changes

public class principal {

	public static void main(String[] args) {

<<<<<<< Updated upstream
		CuentaNeg cuentaNeg = new CuentaNegImpl();
		ClienteNeg clienteNeg = new ClienteNegImpl();
		List<Cuenta> lista = new ArrayList<Cuenta>();
		
		Cliente clienteLogeado =  new Cliente();
		clienteLogeado.setUsuario("juanp");
		clienteLogeado.setPassword("password123");
		clienteLogeado = clienteNeg.obtenerClientePorUsuario(clienteLogeado.getUsuario());
		System.out.println(clienteLogeado.toString());
		
		lista = cuentaNeg.obtenerCuentasXIdCliente(clienteLogeado.getId());
        for(Cuenta cuenta : lista)
        {
        	System.out.println(cuenta.toString());
        	
=======
		   // Crear una instancia de PrestamoDaoImpl (aseg�rate de que el m�todo aprobarPrestamo est� en esta clase)
        PrestamoDaoImpl prestamoDao = new PrestamoDaoImpl();
        
        
        // Definir el id del pr�stamo y el comentario
        int idPrestamo = 20; // Ejemplo de ID de pr�stamo
        String comentario = "Pr�stamo rechazado."; // Ejemplo de comentario
        
        // Llamar al m�todo aprobarPrestamo y obtener el resultado
        boolean resultado = prestamoDao.rechazarPrestamo(idPrestamo, comentario);
        
        // Imprimir el resultado
        if (resultado) {
            System.out.println("El pr�stamo fue rechazado exitosamente.");
        } else {
            System.out.println("No se pudo rechazado el pr�stamo.");
>>>>>>> Stashed changes
        }
    

	}
}


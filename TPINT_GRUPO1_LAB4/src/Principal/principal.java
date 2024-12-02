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
		   // Crear una instancia de PrestamoDaoImpl (asegúrate de que el método aprobarPrestamo esté en esta clase)
        PrestamoDaoImpl prestamoDao = new PrestamoDaoImpl();
        
        
        // Definir el id del préstamo y el comentario
        int idPrestamo = 20; // Ejemplo de ID de préstamo
        String comentario = "Préstamo rechazado."; // Ejemplo de comentario
        
        // Llamar al método aprobarPrestamo y obtener el resultado
        boolean resultado = prestamoDao.rechazarPrestamo(idPrestamo, comentario);
        
        // Imprimir el resultado
        if (resultado) {
            System.out.println("El préstamo fue rechazado exitosamente.");
        } else {
            System.out.println("No se pudo rechazado el préstamo.");
>>>>>>> Stashed changes
        }
    

	}
}


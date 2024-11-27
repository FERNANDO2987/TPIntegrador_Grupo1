package Principal;


import java.util.List;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoCuenta;

import datosImpl.ClienteDaoImpl;
import entidad.Cliente;
import entidad.Pais;

import negocio.ClienteNeg;
import negocio.TipoCuentaNeg;
import nogocioImpl.ClienteNegImpl;
import nogocioImpl.TipoCuentaNegImpl;

public class principal {

	public static void main(String[] args) {

	    TipoCuentaNeg clienteNeg = new TipoCuentaNegImpl(); 
	    List<TipoCuenta> cliente = clienteNeg.obtenerCuentas();
	    for(TipoCuenta cuenta : cliente)
	    {
	    	System.out.println(cuenta.toString());
	    }

	      // Crear un objeto Cliente para modificar  
        Cliente cliente2 = new Cliente();  
        cliente2.setId(1); // Suponiendo que el ID del cliente a modificar es 1  
        cliente2.setDni("12345678");  
        cliente2.setCuil("203123456");  
        cliente2.setNombre("Juan");  
        cliente2.setApellido("P�rezwwwwwwwww");  
        cliente2.setSexo("M");  
        cliente2.setUsuario("juanpwwwwwwww");  
        cliente2.setPassword("password123wwwwww");  
        
        // Crear un objeto Pais (suponiendo que el pa�s ya existe y tiene ID)  
        Pais paisNacimiento = new Pais();  
        paisNacimiento.setId(1); // Suponiendo que el ID del pa�s es 1  
        paisNacimiento.setNombre("Brasil");  
        cliente2.setPaisNacimiento(paisNacimiento);  
        
        // Establecer otros campos  
        cliente2.setFechaNacimiento(java.sql.Date.valueOf("1990-01-01")); // Formato yyyy-mm-dd  
        cliente2.setCorreo("juan.perez@example.com");  
        cliente2.setTelefono("12345678");  
        cliente2.setCelular("987654321");  
        cliente2.setAdmin(false); // Suponiendo que no es admin  

        // Crear una instancia de ClienteDaoImpl  
        ClienteDaoImpl clienteDao = new ClienteDaoImpl();  

        // Llamar al m�todo modificarCliente y capturar el resultado  
        boolean resultado = clienteDao.modificarCliente(cliente2);  

        // Mostrar el resultado de la operaci�n  
        if (resultado) {  
            System.out.println("Cliente modificado exitosamente.");  
        } else {  
            System.out.println("Error al modificar el cliente.");  
        } 

	}
}

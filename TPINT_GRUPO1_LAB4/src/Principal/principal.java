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
        Cliente cliente = new Cliente();  
        cliente.setId(1); // Suponiendo que el ID del cliente a modificar es 1  
        cliente.setDni("12345678");  
        cliente.setCuil("203123456");  
        cliente.setNombre("Juan");  
        cliente.setApellido("P�rezwwwwwwwww");  
        cliente.setSexo("M");  
        cliente.setUsuario("juanpwwwwwwww");  
        cliente.setPassword("password123wwwwww");  
        
        // Crear un objeto Pais (suponiendo que el pa�s ya existe y tiene ID)  
        Pais paisNacimiento = new Pais();  
        paisNacimiento.setId(1); // Suponiendo que el ID del pa�s es 1  
        paisNacimiento.setNombre("Brasil");  
        cliente.setPaisNacimiento(paisNacimiento);  
        
        // Establecer otros campos  
        cliente.setFechaNacimiento(java.sql.Date.valueOf("1990-01-01")); // Formato yyyy-mm-dd  
        cliente.setCorreo("juan.perez@example.com");  
        cliente.setTelefono("12345678");  
        cliente.setCelular("987654321");  
        cliente.setAdmin(false); // Suponiendo que no es admin  

        // Crear una instancia de ClienteDaoImpl  
        ClienteDaoImpl clienteDao = new ClienteDaoImpl();  

        // Llamar al m�todo modificarCliente y capturar el resultado  
        boolean resultado = clienteDao.modificarCliente(cliente);  

        // Mostrar el resultado de la operaci�n  
        if (resultado) {  
            System.out.println("Cliente modificado exitosamente.");  
        } else {  
            System.out.println("Error al modificar el cliente.");  
        } 

	}
}

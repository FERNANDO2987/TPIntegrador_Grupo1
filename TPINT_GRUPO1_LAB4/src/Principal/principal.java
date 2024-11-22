package Principal;

import java.util.Date;

import datosImpl.ClienteDaoImpl;
import entidad.Cliente;
import entidad.Pais;

public class principal {

	public static void main(String[] args) {
	    
	    ClienteDaoImpl clienteDao = new ClienteDaoImpl();

        // Crear un objeto Cliente con datos de prueba
        Cliente cliente = new Cliente();
        cliente.setDni("33420650");
        cliente.setCuil("20334206508");
        cliente.setNombre("Juan2");
        cliente.setApellido("Pérez2");
        cliente.setSexo("M");
        cliente.setUsuario("juan.perez123");
        cliente.setPassword("password1233");
        cliente.setPaisNacimiento(new Pais(1, "Argentina")); // Asume que 1 corresponde a un país existente en la base de datos
        cliente.setFechaNacimiento(new Date()); // Fecha de hoy como ejemplo
        cliente.setCorreo("juan.perez@example.com");
        cliente.setTelefono("011-1234-5678");
        cliente.setCelular("15-1234-5678");
        cliente.setAdmin(false); // No es administrador

        // Llamar al método agregarCliente
        boolean resultado = clienteDao.agregarCliente(cliente);

        // Verificar el resultado
        if (resultado) {
            System.out.println("Cliente agregado con éxito. ID generado: " + cliente.getId());
        } else {
            System.out.println("Error al agregar el cliente.");
        }
	

	}

}

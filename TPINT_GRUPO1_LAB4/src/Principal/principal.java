package Principal;

import entidad.Cliente;
import negocio.ClienteNeg;
import nogocioImpl.ClienteNegImpl;

public class principal {

	public static void main(String[] args) {
	    ClienteNeg clienteNeg = new ClienteNegImpl(); 
	    Cliente cliente = clienteNeg.obtenerUnCliente(1);
	    System.out.println(cliente.toString());
	}
}

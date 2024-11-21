package negocio;

import java.util.ArrayList;

import entidad.Cliente;

public interface ClienteNeg {
	public boolean insertarCliente(Cliente cliente );
	 public Cliente obtenerUnCliente(long id);
	 public ArrayList<Cliente> listarClientes();
	 public boolean editarCliente(Cliente cliente);
	 public boolean eliminarCliente(int idCliente);
	

}

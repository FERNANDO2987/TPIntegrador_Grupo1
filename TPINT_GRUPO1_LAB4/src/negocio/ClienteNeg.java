package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;

public interface ClienteNeg {
	public boolean insertarCliente(Cliente cliente );
	 public Cliente obtenerUnCliente(int id);
	 public ArrayList<Cliente> listarClientes();
	 public boolean editarCliente(Cliente cliente);
	 public boolean eliminarCliente(int idCliente);
	 public Cliente iniciarSesion(String nombreUsuario, String contrasena);
	 public Cliente obtenerClientePorUsuario(String usuario);
	 public List<Cliente> obtenerClientesConFiltro(int criterio, String filtro);
	

}

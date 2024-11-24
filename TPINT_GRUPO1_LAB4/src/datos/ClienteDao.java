package datos;

import java.util.List;

import entidad.Cliente;

public interface ClienteDao {
	public List<Cliente> obtenerClientes();
	public boolean modificarCliente(Cliente cliente);
	public boolean darDeBajaCliente(int idCliente);
	public Cliente loguear(Cliente usuario);
	public boolean agregarCliente(Cliente cliente);
	public Cliente obtenerClientePorId(int id);
	

}

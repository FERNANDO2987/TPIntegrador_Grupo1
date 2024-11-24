package nogocioImpl;

import java.util.ArrayList;

import datos.ClienteDao;
import datosImpl.ClienteDaoImpl;
import entidad.Cliente;
import entidad.Pais;
import negocio.ClienteNeg;

public class ClienteNegImpl implements ClienteNeg {

private ClienteDao  clienteDao = new ClienteDaoImpl();
	
	public  ClienteNegImpl(ClienteDao clienteDao)
	{
		this.clienteDao = clienteDao;
	}
	
	
	public ClienteNegImpl()
	{
		
	}
	
	@Override
	public boolean insertarCliente(Cliente cliente) {
		return clienteDao.agregarCliente(cliente);
	}

	@Override
	public Cliente obtenerUnCliente(int id) {
		
		return (Cliente) clienteDao.obtenerClientePorId(id);
	}

	@Override
	public ArrayList<Cliente> listarClientes() {
		 return (ArrayList<Cliente>) clienteDao.obtenerClientes();
		
	}

	@Override
	public boolean editarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarCliente(int idCliente) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
package nogocioImpl;

import java.util.ArrayList;

import datos.ClienteDao;
import datosImpl.ClienteDaoImpl;
import entidad.Cliente;
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
	public Cliente obtenerUnCliente(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cliente> listarClientes() {
		// TODO Auto-generated method stub
		return null;
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
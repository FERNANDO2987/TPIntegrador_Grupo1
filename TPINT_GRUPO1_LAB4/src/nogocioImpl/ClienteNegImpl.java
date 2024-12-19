package nogocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.ClienteDao;
import datosImpl.ClienteDaoImpl;
import entidad.Cliente;
import negocio.ClienteNeg;
import excepciones.ClienteNoLogueadoException;

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
	public Cliente iniciarSesion(String nombreUsuario, String contrasena) throws ClienteNoLogueadoException
	{
	    Cliente usuario = new Cliente();
	    usuario.setUsuario(nombreUsuario);
	    usuario.setPassword(contrasena);
	    
	   Cliente usuarioValido = clienteDao.loguear(usuario);
	    
	    if (usuarioValido != null) {
	        return usuarioValido;
	    } else {
	    	ClienteNoLogueadoException clienteNoLogueadoException = new ClienteNoLogueadoException()
	    	{private static final long serialVersionUID = 1L;};
	    	throw clienteNoLogueadoException;
	    }
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
		return clienteDao.modificarCliente(cliente);
	}

	@Override
	public boolean eliminarCliente(int idCliente) {
	   
		return clienteDao.darDeBajaCliente(idCliente);
		
	}


	@Override
	public Cliente obtenerClientePorUsuario(String usuario) {
		return clienteDao.obtenerClientePorUsuario(usuario);
	}


	@Override
	public List<Cliente> obtenerClientesConFiltro(int criterio, String filtro) {
		return clienteDao.obtenerClientesConFiltro(criterio, filtro);
	}
	
}
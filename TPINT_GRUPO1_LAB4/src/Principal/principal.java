package Principal;

import java.util.ArrayList;
import java.util.List;

import datosImpl.PrestamoDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import nogocioImpl.ClienteNegImpl;
import nogocioImpl.CuentaNegImpl;

public class principal {

	public static void main(String[] args) {

		CuentaNeg cuentaNeg = new CuentaNegImpl();
		ClienteNeg clienteNeg = new ClienteNegImpl();
		List<Cuenta> lista = new ArrayList<Cuenta>();
		
		Cliente clienteLogeado =  new Cliente();
		clienteLogeado.setUsuario("juanp");
		clienteLogeado.setPassword("password123");
		clienteLogeado = clienteNeg.obtenerClientePorUsuario(clienteLogeado.getUsuario());
		System.out.println(clienteLogeado.toString());
		
		lista = cuentaNeg.obtenerCuentasXIdCliente(clienteLogeado.getId());
        
    

	}
}


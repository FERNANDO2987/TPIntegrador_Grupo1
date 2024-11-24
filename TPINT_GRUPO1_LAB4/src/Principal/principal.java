package Principal;

import java.util.List;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoCuenta;
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
	}
}

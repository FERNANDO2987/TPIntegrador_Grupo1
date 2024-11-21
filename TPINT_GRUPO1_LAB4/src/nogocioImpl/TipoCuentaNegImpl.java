package nogocioImpl;

import java.util.List;

import datos.TipoCuentaDao;
import datosImpl.TipoCuentaDaoImpl;
import entidad.TipoCuenta;
import negocio.TipoCuentaNeg;

public class TipoCuentaNegImpl implements TipoCuentaNeg {

	@Override
	public List<TipoCuenta> obtenerCuentas() {
		TipoCuentaDao tipoCuentaDao = new TipoCuentaDaoImpl();
		List<TipoCuenta> lista = tipoCuentaDao.obtenerCuentas();
		return lista;
	}
	
}

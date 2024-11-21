package nogocioImpl;

import java.util.List;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.CuentaNeg;

public class CuentaNegImpl implements CuentaNeg{

	@Override
	public boolean agregarCuenta(Cuenta cuenta) {
		CuentaDao cuentaDao = new CuentaDaoImpl();	
		return cuentaDao.agregarCuenta(cuenta);
	}

	@Override
	public List<Cuenta> obtenerCuentas() {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		List<Cuenta> lista = cuentaDao.obtenerCuentas();
		return lista;
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		CuentaDao cuentaDao = new CuentaDaoImpl();	
		return cuentaDao.modificarCuenta(cuenta);
	}

	@Override
	public boolean darDeBajaCuenta(Long nroCuenta) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		return cuentaDao.darDeBajaCuenta(nroCuenta);
	}

	@Override
	public Cuenta obtenerCuentaXNroCuenta(Long nroCuenta) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		return cuentaDao.obtenerCuentaXNroCuenta(nroCuenta);
	}

}

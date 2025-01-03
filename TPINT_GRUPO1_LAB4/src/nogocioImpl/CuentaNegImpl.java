package nogocioImpl;

import java.util.List;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
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

	
	@Override
	public List<Cuenta> obtenerCuentasXIdCliente(int id) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		return cuentaDao.obtenerCuentasXIdCliente(id);
	}
	
	@Override
	public List<Cuenta> obtenerCuentasXIdCliente_2(int idCliente) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		return cuentaDao.obtenerCuentasXIdCliente_2(idCliente);
	}

	@Override
	public Cuenta obtenerCuentaXCBU(String cbu) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		return cuentaDao.obtenerCuentaXCBU(cbu);
	}

	@Override
	public int obtenerCountCuentasXCliente(int idCliente) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		return cuentaDao.obtenerCountCuentasXCliente(idCliente);
	}
	
	@Override
	public List<Movimiento> listarMovimientosXCuenta(long idCuenta) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		return cuentaDao.listarMovimientosXCuenta(idCuenta);
	}

	@Override
	public List<Cuenta> obtenerCuentasConFiltro(int criterio, String filtro) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		return cuentaDao.obtenerCuentasConFiltro(criterio, filtro);
	}
}

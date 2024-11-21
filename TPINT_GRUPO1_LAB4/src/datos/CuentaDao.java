package datos;

import java.util.List;

import entidad.Cuenta;

public interface CuentaDao {
	public boolean agregarCuenta(Cuenta cuenta);
	public List<Cuenta> obtenerCuentas();
	public boolean modificarCuenta(Cuenta cuenta);
	public boolean darDeBajaCuenta(Long nroCuenta);
	public Cuenta obtenerCuentaXNroCuenta(Long nroCuenta);
}

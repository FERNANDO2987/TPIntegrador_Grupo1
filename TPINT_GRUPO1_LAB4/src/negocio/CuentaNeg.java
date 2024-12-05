package negocio;

import java.util.List;

import entidad.Cuenta;

public interface CuentaNeg {
	
	public boolean agregarCuenta(Cuenta cuenta);
	public List<Cuenta> obtenerCuentas();
	public boolean modificarCuenta(Cuenta cuenta);
	public boolean darDeBajaCuenta(Long nroCuenta);
	public Cuenta obtenerCuentaXNroCuenta(Long nroCuenta);
	public List<Cuenta> obtenerCuentasXIdCliente_2(int idCliente);
	public List<Cuenta> obtenerCuentasXIdCliente(int id);
	public Cuenta obtenerCuentaXCBU(String cbu);
	public int obtenerCountCuentasXCliente(int idCliente);
	
}

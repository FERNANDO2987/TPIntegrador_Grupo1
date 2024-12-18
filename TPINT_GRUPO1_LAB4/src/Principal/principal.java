package Principal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import datosImpl.InformesDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.ReporteMovimientos;
import entidad.TipoMovimiento;
import entidad.Transferencia;
import negocio.CuentaNeg;
import negocio.TransferenciaNeg;
import nogocioImpl.CuentaNegImpl;
import nogocioImpl.TransferenciaNegImpl;

public class principal {

	public static void main(String[] args) {

		CuentaDaoImpl cuentaDao = new CuentaDaoImpl();
		System.out.println(cuentaDao.validarCBU("20241210212023"));
	}
}


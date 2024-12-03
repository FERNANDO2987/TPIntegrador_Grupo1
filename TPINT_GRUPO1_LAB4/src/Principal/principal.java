package Principal;

import java.time.LocalDate;

import datosImpl.InformesDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.ReporteMovimientos;
import negocio.CuentaNeg;
import nogocioImpl.CuentaNegImpl;

public class principal {

	public static void main(String[] args) {


		  CuentaNeg cuentaNeg = new CuentaNegImpl();
		  Cuenta cuenta = cuentaNeg.obtenerCuentaXCBU("20-00003-0000");
		  System.out.println(cuenta.toString());

	}
}


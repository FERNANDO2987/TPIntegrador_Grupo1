package Principal;

import java.math.BigDecimal;
import java.time.LocalDate;

import datosImpl.InformesDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.ReporteMovimientos;
import entidad.Transferencia;
import negocio.CuentaNeg;
import negocio.TransferenciaNeg;
import nogocioImpl.CuentaNegImpl;
import nogocioImpl.TransferenciaNegImpl;

public class principal {

	public static void main(String[] args) {


		  CuentaNeg cuentaNeg = new CuentaNegImpl();
		  Cuenta cuenta = new Cuenta();
		  cuenta.getTipoCuenta().setId(1);
		  cuenta.getCliente().setId(1);
		  cuentaNeg.agregarCuenta(cuenta);
		  
		  

	}
}


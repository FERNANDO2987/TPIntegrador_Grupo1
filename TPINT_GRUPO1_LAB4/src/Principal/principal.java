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
		  Cuenta cuentaOrigen = new Cuenta();
		  cuentaOrigen.setNroCuenta(101010101);
		  Cuenta cuenta = cuentaNeg.obtenerCuentaXCBU("20-00003-0000");
		  
		  Transferencia transferencia = new Transferencia();
		  transferencia.setCuentaOrigen(cuentaOrigen);
		  transferencia.setCuentaDestino(cuenta);
		  transferencia.setMonto(new BigDecimal("100"));
		  transferencia.setDetalle("Mi primer Transferencia");
		  
		  TransferenciaNeg transferenciaNeg = new TransferenciaNegImpl();
		  boolean exito = transferenciaNeg.agregarTransferencia(transferencia);
		  if(exito)
		  {
			  System.out.println("SI");
		  }
		  
		  

	}
}


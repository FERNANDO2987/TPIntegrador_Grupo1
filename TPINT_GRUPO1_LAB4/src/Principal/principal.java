package Principal;


import java.math.BigDecimal;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import datosImpl.InformesDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import entidad.Movimiento;
import entidad.ReporteMovimientos;
import entidad.TipoMovimiento;
import entidad.Transferencia;
import negocio.PrestamoNeg;
import negocio.CuentaNeg;
import negocio.TransferenciaNeg;
import nogocioImpl.CuentaNegImpl;
import nogocioImpl.PrestamoNegImpl;
import nogocioImpl.TransferenciaNegImpl;

import entidad.ReporteCuotas;

public class principal {

    public static void main(String[] args) {
    	
    	CuentaNeg cuentaNeg = new CuentaNegImpl();
    	List<Cuenta> lista = cuentaNeg.obtenerCuentasConFiltro(1, "j");
    	
    	for(Cuenta cuenta : lista)
    	{
    		System.out.println(cuenta.toString());
    	}
    	
        
    }

}


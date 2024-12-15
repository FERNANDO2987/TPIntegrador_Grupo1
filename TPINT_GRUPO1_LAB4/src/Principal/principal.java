package Principal;


import java.math.BigDecimal;


import java.time.LocalDate;
import java.util.List;

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
    	
    	PrestamoNeg neg = new PrestamoNegImpl();
    	Prestamo p = new Prestamo();
    	
    	p.setCuenta(new Cuenta(222222222));
    	p.setCliente(new Cliente(2));
    	p.setImporte(BigDecimal.valueOf(200770));
    	p.setCuotas(12);
    	
    	boolean a = neg.agregarPrestamo(p);
    	
    	if(a)
    	{
    		System.out.println("todo ok");
    	}else {
    		
    		System.out.println("todo mal");
    	}
    	
        
    }

}


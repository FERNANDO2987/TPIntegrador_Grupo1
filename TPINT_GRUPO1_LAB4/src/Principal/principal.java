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
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocio.TransferenciaNeg;
import nogocioImpl.ClienteNegImpl;
import nogocioImpl.CuentaNegImpl;
import nogocioImpl.PrestamoNegImpl;
import nogocioImpl.TransferenciaNegImpl;

import entidad.ReporteCuotas;

public class principal {

    public static void main(String[] args) {
    	
    	ClienteNeg clienteNeg = new ClienteNegImpl();
    	List<Cliente> lista = clienteNeg.obtenerClientesConFiltro(1, "1");
    	
    	for(Cliente cliente : lista)
    	{
    		System.out.println(cliente.toString());
    	}
    	
        
    }

}


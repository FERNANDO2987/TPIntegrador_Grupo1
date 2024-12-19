package Principal;

import java.math.BigDecimal;



import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;


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

import datosImpl.PrestamoDaoImpl;
import entidad.Movimiento;
import entidad.Prestamo;


public class principal {

    public static void main(String[] args) {
    	

    	ClienteNeg clienteNeg = new ClienteNegImpl();
    	List<Cliente> lista = clienteNeg.obtenerClientesConFiltro(1, "1");
    	
    	for(Cliente cliente : lista)
    	{
    		System.out.println(cliente.toString());
    	}
    	

 
    	
    	PrestamoDaoImpl prestamo = new PrestamoDaoImpl();
    	  // Definir los par�metros de la llamada
        int cuotasAActualizar = 2;           // Ejemplo: 3 cuotas a actualizar
        BigDecimal montoAActualizar = new BigDecimal("200.00"); // Ejemplo: monto a actualizar
        int idPrestamo = 20;              // Ejemplo: ID de pr�stamo
        int nroCuenta = 111111111;               // Ejemplo: n�mero de cuenta
        String detalle = "Actualizaci�n de cuota"; // Ejemplo: detalle de la actualizaci�n

        // Llamar al m�todo y mostrar el resultado
        boolean resultado = prestamo.actualizarCuota(cuotasAActualizar, montoAActualizar, idPrestamo, nroCuenta, detalle);

        
        // Mostrar el resultado
        if (resultado) {
            System.out.println("La cuota se actualiz� correctamente.");
        } else {
            System.out.println("Hubo un error al actualizar la cuota.");
        }
      }

}


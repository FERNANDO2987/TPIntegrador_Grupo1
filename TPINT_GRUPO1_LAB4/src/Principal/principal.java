package Principal;


import java.math.BigDecimal;


import java.time.LocalDate;
import java.util.List;

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

import entidad.ReporteCuotas;

public class principal {

    public static void main(String[] args) {
    	
    	 // Crear instancia de InformesDaoImpl
        // Crear instancia de InformesDaoImpl
        InformesDaoImpl informesDao = new InformesDaoImpl();
        
        // Definir las fechas de inicio y fin
        LocalDate fechaDesde = LocalDate.of(2024, 1, 1);  // Ejemplo: 1 de enero de 2023
        LocalDate fechaHasta = LocalDate.of(2024, 12, 31); // Ejemplo: 31 de diciembre de 2023
        
        // Llamar al m�todo obtenerCuotasPagadasYPendientesPorMes con las fechas
        List<ReporteCuotas> reporteCuotas = informesDao.obtenerCuotasPagadasYPendientesPorMes(fechaDesde, fechaHasta);

        // Imprimir los resultados
        if (reporteCuotas.isEmpty()) {
            System.out.println("No se encontraron datos.");
        } else {
            System.out.println("Reporte de Cuotas Pagadas y Pendientes:");
            for (ReporteCuotas cuota : reporteCuotas) {
                // Imprimir el a�o, mes, total pagado y total pendiente
                System.out.println("A�o: " + cuota.getAnio());
                System.out.println("Mes: " + cuota.getMes());
                System.out.println("Total Pagado: " + cuota.getTotalPagado());
                System.out.println("Total Pendiente: " + cuota.getTotalPendiente());
                System.out.println("------------------------");
            }
        }
    	
    	
        
    }

}


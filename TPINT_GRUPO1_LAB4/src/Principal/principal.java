package Principal;

import java.time.LocalDate;

import datosImpl.InformesDaoImpl;
import entidad.Movimiento;
import entidad.ReporteMovimientos;

public class principal {

	public static void main(String[] args) {


		  InformesDaoImpl movimientoDao = new InformesDaoImpl();
	        LocalDate fechaInicio = LocalDate.of(2024, 1, 1);
	        LocalDate fechaFin = LocalDate.of(2024, 12, 31);

	        ReporteMovimientos reporte = movimientoDao.generarReporteMovimientos(fechaInicio, fechaFin);

	        if (reporte != null) {
	            System.out.println("Reporte generado: " + reporte);
	        } else {
	            System.out.println("No se pudo generar el reporte.");
	        }

	}
}


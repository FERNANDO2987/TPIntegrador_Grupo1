package datos;

import java.time.LocalDate;
import java.util.List;

import entidad.ReporteCuotas;
import entidad.ReporteMovimientos;

public interface InformesDao {
	
	  public List<ReporteMovimientos> generarReporteMovimientos(LocalDate fechaInicio, LocalDate fechaFin);

		 public List<ReporteCuotas> obtenerCuotasPagadasYPendientesPorMes(LocalDate fechaDesde, LocalDate fechaHasta);
}

package datos;

import java.time.LocalDate;

import entidad.ReporteMovimientos;

public interface InformesDao {
	
	  public ReporteMovimientos generarReporteMovimientos(LocalDate fechaInicio, LocalDate fechaFin);

}

package datos;

import java.time.LocalDate;
import java.util.List;

import entidad.ReporteMovimientos;

public interface InformesDao {
	
	  public List<ReporteMovimientos> generarReporteMovimientos(LocalDate fechaInicio, LocalDate fechaFin);

}

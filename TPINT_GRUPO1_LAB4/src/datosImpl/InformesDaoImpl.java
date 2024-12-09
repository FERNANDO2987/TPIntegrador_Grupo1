package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import datos.InformesDao;
import entidad.ReporteCuotas;
import entidad.ReporteMovimientos;

public class InformesDaoImpl implements InformesDao{
	
	
	private Conexion cn;

	public InformesDaoImpl()
	{
		 cn = new Conexion();
	}
	
	 public List<ReporteCuotas> obtenerCuotasPagadasYPendientesPorMes(LocalDate fechaDesde, LocalDate fechaHasta) {
	        List<ReporteCuotas> reporteList = new ArrayList<>();
	        cn = new Conexion();
	        cn.Open();

	        // Modificado para incluir los parámetros de fecha
	        String query = "{CALL ObtenerCuotasPagadasYPendientesPorMes(?, ?)}";

	        try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	            // Establecer los parámetros de fecha en el CallableStatement
	            stmt.setDate(1, java.sql.Date.valueOf(fechaDesde));
	            stmt.setDate(2, java.sql.Date.valueOf(fechaHasta));

	            // Ejecutar el procedimiento almacenado
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    int anio = rs.getInt("Anio");  // Obtener el año como entero
	                    int mes = rs.getInt("Mes");    // Obtener el mes como entero
	                    double totalPagado = rs.getDouble("TotalPagado");
	                    double totalPendiente = rs.getDouble("TotalPendiente");

	                    // Crear un objeto ReporteCuotas con los datos obtenidos
	                    ReporteCuotas reporte = new ReporteCuotas(anio, mes, totalPagado, totalPendiente);
	                    reporteList.add(reporte);
	                }
	            }

	        } catch (SQLException e) {
	            System.out.println("Error al obtener cuotas pagadas y pendientes: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }

	        return reporteList;
	    }
	
	
	  public List<ReporteMovimientos> generarReporteMovimientos(LocalDate fechaInicio, LocalDate fechaFin) {
	        List<ReporteMovimientos> reporteList = new ArrayList<>();
	        cn = new Conexion();
	        cn.Open();

	        String query = "{CALL GenerarReporteMovimientosMesPorMes(?, ?)}";

	        try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	           
	            stmt.setDate(1, java.sql.Date.valueOf(fechaInicio));
	            stmt.setDate(2, java.sql.Date.valueOf(fechaFin));

	            
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    double totalIngresos = rs.getDouble("Total_Ingresos");
	                    double totalEgresos = rs.getDouble("Total_Egresos");
	                    int anio = rs.getInt("Anio");
	                    int mes = rs.getInt("Mes");

	                  
	                    ReporteMovimientos reporte = new ReporteMovimientos(anio, mes, totalIngresos, totalEgresos);
	                    reporteList.add(reporte);
	                }
	            }

	        } catch (SQLException e) {
	            System.out.println("Error al generar el reporte de movimientos: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }

	        return reporteList;
	    }

	

}

package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import datos.InformesDao;
import entidad.ReporteMovimientos;

public class InformesDaoImpl implements InformesDao{
	
	
	private Conexion cn;

	public InformesDaoImpl()
	{
		 cn = new Conexion();
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

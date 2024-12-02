package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import datos.InformesDao;
import entidad.ReporteMovimientos;

public class InformesDaoImpl implements InformesDao{
	
	
	private Conexion cn;

	public InformesDaoImpl()
	{
		 cn = new Conexion();
	}
	
	
	  public ReporteMovimientos generarReporteMovimientos(LocalDate fechaInicio, LocalDate fechaFin) {
	        ReporteMovimientos reporte = null;
	        cn = new Conexion();
	        cn.Open();

	        String query = "{CALL GenerarReporteMovimientos(?, ?)}";

	        try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	            // Establecer los parámetros de entrada
	            stmt.setDate(1, java.sql.Date.valueOf(fechaInicio));
	            stmt.setDate(2, java.sql.Date.valueOf(fechaFin));

	            // Ejecutar el procedimiento y procesar el resultado
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    double totalIngresos = rs.getDouble("Total_Ingresos");
	                    double totalEgresos = rs.getDouble("Total_Egresos");

	                    // Crear instancia de ReporteMovimientos
	                    reporte = new ReporteMovimientos(totalIngresos, totalEgresos);
	                }
	            }

	        } catch (SQLException e) {
	            System.out.println("Error al generar el reporte de movimientos: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }

	        return reporte;
	    }

	

}

package datosImpl;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import datos.PrestamoDao;
import entidad.Prestamo;

public class PrestamoDaoImpl  implements PrestamoDao{
	private Conexion cn;

	public PrestamoDaoImpl()
	{
		
	}


	@Override
	public boolean agregarPrestamo(Prestamo prestamo) {
		boolean estado = true;
	    cn = new Conexion();
	    cn.Open();
	    
	    //Call AgregarPrestamo (nro_cuenta, id_cliente , importe, cuotas);
	    String query = "{CALL AgregarPrestamo(?, ?, ?, ?)}";
	    
	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	        // Establecer los parámetros
	        stmt.setLong(1, prestamo.getCuenta().getNroCuenta()); // p_nro_cuenta
	        stmt.setLong(2, prestamo.getCliente().getId()); // p_id_cliente
	        stmt.setBigDecimal(3, prestamo.getImporte()); // p_importe
	        stmt.setInt(4, prestamo.getCuotas()); // p_cuotas
	        
	        // Ejecutar el procedimiento
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        estado = false;
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
		
		return estado;
	}




	@Override
	public boolean aprobarPrestamo(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rechazarPrestamo(Long id) {
		boolean estado = true;
	    cn = new Conexion();
	    cn.Open();
	    
	    //Call RechazarPrestamo (id_prestamo);
	    String query = "{CALL RechazarPrestamo(?)}";
	    
	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	        // Establecer los parámetros
	        
	        stmt.setLong(1, id); // p_id_prestamo
	        // Ejecutar el procedimiento
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        estado = false;
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
		
		return estado;
	}


	@Override
	public Prestamo obtenerPrestamoxId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Prestamo> obtenerPrestamos() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
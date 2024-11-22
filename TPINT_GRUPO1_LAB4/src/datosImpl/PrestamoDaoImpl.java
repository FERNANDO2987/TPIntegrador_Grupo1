package datosImpl;


import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import datos.PrestamoDao;
import entidad.Cliente;
import entidad.Cuenta;
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
	public boolean aprobarPrestamo(Long id) {
		boolean estado = true;
	    cn = new Conexion();
	    cn.Open();
	    
	    //Call RechazarPrestamo (id_prestamo);
	    String query = "{CALL AprobarPrestamo(?)}";
	    
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
		Prestamo aux = new Prestamo();
		Cuenta cuenta = new Cuenta();
		Cliente cliente = new Cliente();
		cn = new Conexion();
		cn.Open();
		
		String query = "select id, nro_cuenta, id_cliente, fecha_solicitud, importe, cuotas from prestamos where id = ?;";
		
		try {
			PreparedStatement prst = cn.connection.prepareStatement(query);	
			prst.setLong(1, id);
			ResultSet rs = prst.executeQuery();
			rs.next();
			
			aux.setId(rs.getLong("id"));
			
			cuenta.setNroCuenta(rs.getLong("nro_cuenta"));
			aux.setCuenta(cuenta);
			
			cliente.setId(rs.getInt("id_cliente"));
			aux.setCliente(cliente);
			
			aux.setFechaSolicitud(rs.getDate("fecha_solicitud").toLocalDate());
			aux.setImporte(rs.getBigDecimal("importe"));
			aux.setCuotas(rs.getInt("cuotas"));
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Close connection
		        cn.close();
		    }
		return aux;
	}


	@Override
	public List<Prestamo> obtenerPrestamos() {
		List<Prestamo> prestamos = new ArrayList<>();
		String query = "select id, nro_cuenta, id_cliente, fecha_solicitud, importe, cuotas from prestamos where estado is null;";
		
		 try {
		        // Open connection
		        cn = new Conexion();
		        cn.Open();
		        ResultSet rs = cn.query(query);
		        if (rs != null) {
			        while (rs != null && rs.next()) {
			            long id = rs.getLong("id");
			            long nro_cuenta = rs.getLong("nro_cuenta");
			            int id_cliente = rs.getInt("id_cliente");
			            LocalDate fechaSolicitud = rs.getDate("fecha_solicitud").toLocalDate();
			            BigDecimal importe = rs.getBigDecimal("importe");
			            int cuotas = rs.getInt("cuotas");  
	
			            Prestamo prestamo = new Prestamo();
			            Cuenta cuenta = new Cuenta();
			            cuenta.setNroCuenta(nro_cuenta);
			            Cliente cliente = new Cliente();
			            cliente.setId(id_cliente);
			            
			            prestamo.setId(id);
			            prestamo.setCuenta(cuenta);
			            prestamo.setCliente(cliente);
			            prestamo.setImporte(importe);
			            prestamo.setCuotas(cuotas);
			            prestamo.setFechaSolicitud(fechaSolicitud);
			            
			            prestamos.add(prestamo);
			        }
				 } else {
		             System.out.println("El ResultSet está vacío.");
		         }
		        System.out.println("Prestamos cargados: " + prestamos.size());
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Close connection
		        cn.close();
		    }
		return prestamos;
	}
	
}
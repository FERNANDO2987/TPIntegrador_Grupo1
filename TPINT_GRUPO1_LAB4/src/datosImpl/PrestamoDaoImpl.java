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
	    
	   
	    String query = "{CALL AgregarPrestamo(?, ?, ?, ?)}";
	    
	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	        // Establecer los parámetros
	        stmt.setLong(1, prestamo.getCuenta().getNroCuenta()); 
	        stmt.setLong(2, prestamo.getCliente().getId()); 
	        stmt.setBigDecimal(3, prestamo.getImporte()); 
	        stmt.setInt(4, prestamo.getCuotas());
	        
	      
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
	    
	  
	    String query = "{CALL AprobarPrestamo(?)}";
	    
	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	       
	        
	        stmt.setLong(1, id); 
	       
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
	    
	   
	    String query = "{CALL RechazarPrestamo(?)}";
	    
	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	      
	        
	        stmt.setLong(1, id); 
	       
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
	
	@Override  
	public List<Prestamo> obtenerDatosPrestamos() {  
	    List<Prestamo> prestamos = new ArrayList<>();  
	    cn = new Conexion();  
	    cn.Open();  

	    String query = "{CALL ObtenerDatosPrestamos()}"; 

	    try (CallableStatement stmt = cn.connection.prepareCall(query);  
	         ResultSet rs = stmt.executeQuery()) {  

	        while (rs.next()) {  
	            Prestamo prestamo = new Prestamo();  
	            Cliente cliente = new Cliente();  
	            Cuenta cuenta = new Cuenta();  

	            
	            cliente.setNombre(rs.getString("Nombre"));  
	            cliente.setApellido(rs.getString("Apellido"));  
	            cliente.setCorreo(rs.getString("Correo"));  
	            cliente.setTelefono(rs.getString("Telefono"));  

	           
	            cuenta.setCbu(rs.getString("CBU"));  

	          
	            prestamo.setId(rs.getLong("id"));   
	            
	            prestamo.setCuenta(cuenta);  
	            prestamo.setCliente(cliente);  
	            prestamo.setFechaSolicitud(rs.getDate("FechaSolicitud").toLocalDate());  
	            prestamo.setImporte(rs.getBigDecimal("Importe"));  
	            prestamo.setCuotas(rs.getInt("TotalCuotas"));
	            prestamo.setObservaciones(rs.getString("Observaciones"));  

  
	            String estado = rs.getString("Estado");
	            if ("Autorizado".equals(estado)) {
	                prestamo.setEstado(true);  // Authorized
	            } else if ("Rechazado".equals(estado)) {
	                prestamo.setEstado(false); // Rejected
	            } else {
	                prestamo.setEstado(false); // Pending (default to false for now)
	            }
	            
	            
	            prestamos.add(prestamo);  
	        }  
	    } catch (SQLException e) {  
	        e.printStackTrace();  
	    } finally {  
	        cn.close(); 
	    }  

	    return prestamos;  
	}

	
	@Override
	public boolean aprobarPrestamo(int id, String comentario) {
	    boolean estado = true;
	    cn = new Conexion();
	    cn.Open();
	    
	   
	    String query = "UPDATE prestamos SET estado = 1, observaciones = ? WHERE id = ? ";   
	    try (PreparedStatement stmt = cn.connection.prepareStatement(query)) {
	       
	        stmt.setString(1, comentario);
	        stmt.setInt(2, id);            
	        
	   
	        int rowsAffected = stmt.executeUpdate();
	        
	        if (rowsAffected == 0) {
	            
	            System.out.println("No se encontró un préstamo pendiente con ID: " + id);
	            estado = false;
	        } else {
	            System.out.println("Préstamo con ID: " + id + " aprobado exitosamente.");
	        }
	    } catch (SQLException e) {
	        estado = false;
	        System.out.println("Error al aprobar el préstamo: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    
	    return estado;
	}
	
	
	@Override
	public boolean rechazarPrestamo(int id, String comentario) {
	    boolean estado = true;
	    cn = new Conexion();
	    cn.Open();
	    
	  
	    String query = "UPDATE prestamos SET estado = 0, observaciones = ? WHERE id = ? ";   
	    
	    try (PreparedStatement stmt = cn.connection.prepareStatement(query)) {
	     
	        stmt.setString(1, comentario); 
	        stmt.setInt(2, id);           
	        
	      
	        int rowsAffected = stmt.executeUpdate();
	        
	        if (rowsAffected == 0) {
	         
	            System.out.println("No se encontró un préstamo pendiente con ID: " + id);
	            estado = false;
	        } else {
	            System.out.println("Préstamo con ID: " + id + " rechazado exitosamente.");
	        }
	    } catch (SQLException e) {
	        estado = false;
	        System.out.println("Error al rechazado el préstamo: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    
	    return estado;
	}



	
	
}
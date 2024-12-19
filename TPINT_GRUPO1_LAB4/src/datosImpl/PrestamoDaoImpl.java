package datosImpl;


import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.PrestamoDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Cuota;
import entidad.Movimiento;
import entidad.Prestamo;
import entidad.TipoMovimiento;

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
	public boolean aprobarPrestamo_2(Long id, String observacion) {
		boolean estado = true;
	    cn = new Conexion();
	    cn.Open();
	    
	  
	    String query = "{CALL AprobarPrestamo(?, ?)}";
	    
	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	       
	        
	        stmt.setLong(1, id); 
	        stmt.setString(2, observacion); 
	       
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
	public List<Prestamo> obtenerPrestamosXCliente(int idCliente) {
	    List<Prestamo> prestamos = new ArrayList<>();
	    String query = "SELECT id, nro_cuenta, id_cliente, fecha_solicitud, importe, cuotas, estado, observaciones FROM prestamos WHERE id_cliente = ?;";
	    cn = new Conexion();
	    cn.Open();
	    
	    try (PreparedStatement stmt = cn.connection.prepareStatement(query)) {
	        // Establecer el parámetro para el idCliente
	        stmt.setInt(1, idCliente); 
	        
	        // Ejecutar la consulta
	        try (ResultSet rs = stmt.executeQuery()) {
	            // Verificar si el ResultSet contiene datos
	            if (rs != null) {
	                while (rs.next()) {
	                    long id = rs.getLong("id");
	                    long nroCuenta = rs.getLong("nro_cuenta");
	                    int idClienteDb = rs.getInt("id_cliente");
	                    LocalDate fechaSolicitud = rs.getDate("fecha_solicitud").toLocalDate();
	                    BigDecimal importe = rs.getBigDecimal("importe");
	                    int cuotas = rs.getInt("cuotas");  
	                    Boolean estado = rs.getBoolean("estado");
	                    String observacion = rs.getString("observaciones");
	                    

	                    // Crear los objetos correspondientes
	                    Prestamo prestamo = new Prestamo();
	                    Cuenta cuenta = new Cuenta();
	                    cuenta.setNroCuenta(nroCuenta);
	                    Cliente cliente = new Cliente();
	                    cliente.setId(idClienteDb);

	                    // Establecer los valores en el prestamo
	                    prestamo.setId(id);
	                    prestamo.setCuenta(cuenta);
	                    prestamo.setCliente(cliente);
	                    prestamo.setImporte(importe);
	                    prestamo.setCuotas(cuotas);
	                    prestamo.setFechaSolicitud(fechaSolicitud);
	                    prestamo.setEstado(estado);
	                    prestamo.setObservaciones(observacion);
	                    

	                    // Agregar el prestamo a la lista
	                    prestamos.add(prestamo);
	                }
	            } else {
	                System.out.println("El ResultSet está vacío.");
	            }
	            System.out.println("Prestamos cargados: " + prestamos.size());
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar la conexión
	        cn.close();
	    }
	    return prestamos;
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


	public Map<Prestamo, List<Movimiento>> obtenerDatosClientePrestamo(int clienteId) {
	    Map<Prestamo, List<Movimiento>> prestamosConMovimientos = new HashMap<>();
	    cn = new Conexion();
	    cn.Open();

	    String query = "{CALL ObtenerDatosClientePrestamo(?)}";

	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	        stmt.setInt(1, clienteId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                // Crear instancias necesarias
	                Prestamo prestamo = new Prestamo();
	                Cliente cliente = new Cliente();
	                Cuenta cuenta = new Cuenta();
	                Movimiento movimiento = new Movimiento();
	                TipoMovimiento tipoMovimiento = new TipoMovimiento();
	                Cuota cuota = new Cuota();

	                // Configurar cliente
	                cliente.setId(rs.getInt("cliente_id"));
	                prestamo.setCliente(cliente);

	                // Configurar préstamo
	                prestamo.setId(rs.getLong("prestamo_id"));
	                Date fechaSolicitud = rs.getDate("fecha_solicitud");
	                prestamo.setFechaSolicitud(fechaSolicitud != null ? fechaSolicitud.toLocalDate() : null);
	                prestamo.setCuotas(rs.getInt("cuotas_prestamo"));
	                prestamo.setImporte(rs.getBigDecimal("monto_cuota") != null ? rs.getBigDecimal("monto_cuota") : BigDecimal.ZERO);
	                
	  
	
	                // Configurar cuenta
	                cuenta.setNroCuenta(rs.getLong("numero_cuenta"));
	                cuenta.setSaldo(rs.getBigDecimal("cuenta_saldo"));
	                prestamo.setCuenta(cuenta);

	                // Configurar movimiento
	                movimiento.setImporte(rs.getBigDecimal("importe_movimiento"));
	                movimiento.setDetalle(rs.getString("detalle"));
	                tipoMovimiento.setDescripcion(rs.getString("descripcion_movimiento"));
	                tipoMovimiento.setId(rs.getInt("tipo_id"));
	                movimiento.setTipoMovimiento(tipoMovimiento);

	                // Asociar préstamo con movimientos
	                prestamosConMovimientos.computeIfAbsent(prestamo, k -> new ArrayList<>()).add(movimiento);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return prestamosConMovimientos;
	}


	@Override
	public boolean actualizarCuota(int cuotasAActualizar, BigDecimal montoAActualizar, int idPrestamo, int nroCuenta, String detalle) {
	    boolean estado = true;
	    cn = new Conexion();
	    cn.Open();
	    
	    // Llamar al procedimiento almacenado
	    String query = "{CALL ActualizarCuota(?, ?, ?, ?, ?)}";
	    
	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	        // Establecer los parámetros
	        stmt.setInt(1, cuotasAActualizar);              // p_cuotas_a_actualizar
	        stmt.setBigDecimal(2, montoAActualizar);         // p_monto_a_actualizar
	        stmt.setInt(3, idPrestamo);                      // p_id_prestamo
	        stmt.setInt(4, nroCuenta);                       // nro_cuenta
	        stmt.setString(5, detalle);                      // detalle

	        // Ejecutar la actualización
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        estado = false;
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    
	    return estado;
	}


	
	
}
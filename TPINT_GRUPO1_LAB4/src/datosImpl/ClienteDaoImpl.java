package datosImpl;


import java.sql.CallableStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;



import datos.ClienteDao;
import entidad.Cliente;
import entidad.Pais;

public class ClienteDaoImpl implements ClienteDao{
	
	private Conexion cn;

	public ClienteDaoImpl()
	{
		 cn = new Conexion();
	}
	
	

	public boolean agregarCliente(Cliente cliente) {
	    boolean estado = true;
	    cn.Open();

	    String query = "{CALL AgregarCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	        // Establecer los parámetros
	        if (cliente.getId() > 0) {
	            stmt.setInt(1, cliente.getId()); // ID existente
	        } else {
	            stmt.setNull(1, Types.INTEGER); // ID nulo para nuevo cliente
	        }
	        stmt.setString(2, cliente.getDni());
	        stmt.setString(3, cliente.getCuil());
	        stmt.setString(4, cliente.getNombre());
	        stmt.setString(5, cliente.getApellido());
	        stmt.setString(6, cliente.getSexo());
	        stmt.setString(7, cliente.getUsuario());
	        stmt.setString(8, cliente.getPassword());
	        stmt.setObject(9, cliente.getPaisNacimiento() != null ? cliente.getPaisNacimiento().getId() : null, Types.INTEGER);
	        stmt.setDate(10, cliente.getFechaNacimiento() != null ? new java.sql.Date(cliente.getFechaNacimiento().getTime()) : null);
	        stmt.setString(11, cliente.getCorreo());
	        stmt.setString(12, cliente.getTelefono());
	        stmt.setString(13, cliente.getCelular());
	        stmt.setBoolean(14, cliente.getAdmin());

	        // Ejecutar la sentencia
	        stmt.executeUpdate();

	        // Capturar el ID generado si es un nuevo cliente
	        if (cliente.getId() <= 0) {
	            try (ResultSet rs = stmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    int nuevoId = rs.getInt(1); // Obtener el ID generado
	                    cliente.setId(nuevoId); // Asignar el nuevo ID al objeto cliente
	                }
	            }
	        }

	    } catch (SQLException e) {
	        estado = false;
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return estado;
	}


	

	
	@Override
	public boolean darDeBajaCliente(int idCliente) {
	    boolean estado = true;
	    cn.Open(); 

	   
	    String query = "{CALL EliminarCliente(?)}"; 

	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	    
	        stmt.setInt(1, idCliente);

	     
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        
	        estado = false; 
	        e.printStackTrace();
	    } finally {
	        // Cerramos la conexión
	        cn.close();
	    }

	    return estado; 
	}


	




	@Override
	public List<Cliente> obtenerClientes() {
	    List<Cliente> clientes = new ArrayList<>(); // Lista para almacenar los clientes
	    cn.Open(); // Abrimos la conexión

	    // Llamada al procedimiento almacenado
	    String query = "CALL ObtenerClientes()"; // Llamada al procedimiento almacenado

	    try (CallableStatement stmt = cn.connection.prepareCall(query);
	         ResultSet rs = stmt.executeQuery()) { // Ejecutar el procedimiento almacenado

	        // Verificar si hay resultados
	        if (!rs.isBeforeFirst()) {
	            System.out.println("No se encontraron clientes.");
	            return clientes; // Si no hay resultados, retornamos la lista vacía
	        }

	        // Iteramos sobre los resultados del ResultSet
	        while (rs.next()) {
	            // Crear un objeto Cliente y llenar sus propiedades
	            Cliente cliente = new Cliente();
	            cliente.setId(rs.getInt("id"));
	            cliente.setDni(rs.getString("dni"));
	            cliente.setCuil(rs.getString("cuil"));
	            cliente.setNombre(rs.getString("nombre"));
	            cliente.setApellido(rs.getString("apellido"));
	            cliente.setSexo(rs.getString("sexo"));
	            cliente.setUsuario(rs.getString("usuario"));
	            cliente.setPassword(rs.getString("password"));

	            // Llenar el objeto Pais si existe
	            // Ahora solo se debe manejar el campo 'pais', ya no 'PaisNacimientoId'
	            Pais paisNacimiento = new Pais();
	            paisNacimiento.setNombre(rs.getString("pais"));
	            cliente.setPaisNacimiento(paisNacimiento);

	            cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
	            cliente.setCorreo(rs.getString("correo"));
	            cliente.setTelefono(rs.getString("telefono"));
	            cliente.setCelular(rs.getString("celular"));
	            cliente.setAdmin(rs.getBoolean("admin"));

	            // Agregar el cliente a la lista
	            clientes.add(cliente);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close(); // Cerramos la conexión
	    }

	    return clientes; // Retornamos la lista de clientes
	}




	public Cliente obtenerClientePorId(int id) {
	    Cliente cliente = null; // Inicializamos el cliente como null
	    cn.Open(); // Abrimos la conexión

	    String query = "{CALL ObtenerClientePorId(?)}"; // Llamada al procedimiento almacenado

	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	        // Establecer el parámetro de entrada
	        stmt.setInt(1, id);

	        // Ejecutar el procedimiento almacenado y obtener el resultado
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                // Crear un objeto Cliente y llenar sus propiedades
	                cliente = new Cliente();
	                cliente.setId(rs.getInt("Id"));
	                cliente.setDni(rs.getString("Dni"));
	                cliente.setCuil(rs.getString("Cuil"));
	                cliente.setNombre(rs.getString("Nombre"));
	                cliente.setApellido(rs.getString("Apellido"));
	                cliente.setSexo(rs.getString("Sexo"));
	                cliente.setUsuario(rs.getString("Usuario"));
	                cliente.setPassword(rs.getString("Password"));

	                // Llenar el objeto Pais si existe
	                if (rs.getObject("PaisNacimientoId") != null) {
	                    Pais paisNacimiento = new Pais();
	                    paisNacimiento.setId(rs.getInt("PaisNacimientoId"));
	                    paisNacimiento.setNombre(rs.getString("PaisNacimientoNombre"));
	                    cliente.setPaisNacimiento(paisNacimiento);
	                } else {
	                    cliente.setPaisNacimiento(null);
	                }

	                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
	                cliente.setCorreo(rs.getString("Correo"));
	                cliente.setTelefono(rs.getString("Telefono"));
	                cliente.setCelular(rs.getString("Celular"));
	                cliente.setAdmin(rs.getBoolean("Admin"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close(); // Cerramos la conexión
	    }

	    return cliente; // Retornamos el cliente encontrado o null si no existe
	}


	


	@Override  
	public boolean modificarCliente(Cliente cliente) {  
	    boolean estado = true;  
	    cn.Open();  

	    String query = "{CALL AgregarCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";  

	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {  
	        // Establecer los parámetros  
	        stmt.setInt(1, cliente.getId());  
	        stmt.setString(2, cliente.getDni());  
	        stmt.setString(3, cliente.getCuil());  
	        stmt.setString(4, cliente.getNombre());  
	        stmt.setString(5, cliente.getApellido());  
	        stmt.setString(6, cliente.getSexo());  
	        stmt.setString(7, cliente.getUsuario());  
	        stmt.setString(8, cliente.getPassword());  
	        // Cambiado de setObject a setString  
	        stmt.setString(9, cliente.getPaisNacimiento() != null ? cliente.getPaisNacimiento().getNombre() : null);  
	        stmt.setDate(10, cliente.getFechaNacimiento() != null ? new java.sql.Date(cliente.getFechaNacimiento().getTime()) : null);  
	        stmt.setString(11, cliente.getCorreo());  
	        stmt.setString(12, cliente.getTelefono());  
	        stmt.setString(13, cliente.getCelular());  
	        stmt.setBoolean(14, cliente.getAdmin());  

	        // Ejecutar la sentencia  
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
	   public Cliente loguear(Cliente usuario) {
	       Cliente usuarioBD = null;
	       cn = new Conexion();
	       ResultSet rs = null;
	       cn.Open();
	       String query = "{CALL ValidarUsuario(?, ?)}";
	       try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	           stmt.setString(1, usuario.getUsuario());
	           stmt.setString(2, usuario.getPassword()); 
	           rs = stmt.executeQuery();
	           if (rs != null && rs.next()) {
	        	   usuarioBD = new Cliente();
	        	   usuarioBD.setId(rs.getInt("id"));
	        	   usuarioBD.setUsuario(rs.getString("usuario"));
	        	   usuarioBD.setPassword(rs.getString("password"));
	        	   usuarioBD.setNombre(rs.getString("nombre"));
	        	   usuarioBD.setApellido(rs.getString("apellido"));
	        	   usuarioBD.setAdmin(rs.getBoolean("admin"));
	        	   usuarioBD.setCelular(rs.getString("Celular"));
	        	   usuarioBD.setTelefono(rs.getString("Telefono"));
	        	   usuarioBD.setCuil(rs.getString("cuil"));
	        	   usuarioBD.setPaisNacimiento(new Pais(rs.getInt("id_pais"),rs.getString("pais")));
	        	   usuarioBD.setDni(rs.getString("dni"));
	        	   usuarioBD.setCorreo(rs.getString("correo"));
	        	   usuarioBD.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
	        	   usuarioBD.setSexo(rs.getString("sexo"));
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           try {
	               if (rs != null) {
	                   rs.close();
	               }
	               cn.close();
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	       }
	       return usuarioBD;
	   }



	@Override
	public Cliente obtenerClientePorUsuario(String usuario) {
		Cliente cliente = null; 
	    cn.Open(); 

	    String query = "{CALL obtenerClienteXUsuario(?)}"; 

	    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	        stmt.setString(1, usuario);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                cliente = new Cliente();
	                cliente.setId(rs.getInt("Id"));
	                cliente.setDni(rs.getString("Dni"));
	                cliente.setCuil(rs.getString("Cuil"));
	                cliente.setNombre(rs.getString("Nombre"));
	                cliente.setApellido(rs.getString("Apellido"));
	                cliente.setSexo(rs.getString("Sexo"));
	                cliente.setUsuario(rs.getString("Usuario"));
	                cliente.setPassword(rs.getString("Password"));

	                cliente.getPaisNacimiento().setNombre(rs.getString("pais"));

	                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
	                cliente.setCorreo(rs.getString("Correo"));
	                cliente.setTelefono(rs.getString("Telefono"));
	                cliente.setCelular(rs.getString("Celular"));
	                cliente.setAdmin(rs.getBoolean("Admin"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close(); 
	    }

	    return cliente; 
	}
}


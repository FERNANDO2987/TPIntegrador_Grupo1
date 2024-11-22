package datosImpl;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
	    cn = new Conexion();
	    cn.Open();

	    // Consulta para actualizar el estado del cliente a inactivo
	    String query = "UPDATE clientes SET Deleted = TRUE, DeleteDate = NOW() WHERE id = " + idCliente;

	    System.out.println(query);

	    try {
	        estado = cn.execute(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        estado = false;
	    } finally {
	        cn.close();
	    }

	    return estado;
	}


	




	@Override
	public List<Cliente> obtenerClientes() {
		// TODO Auto-generated method stub
		return null;
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

	                cliente.setFechaNacimiento(rs.getDate("FechaNacimiento"));
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
		// TODO Auto-generated method stub
		return false;
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
	        	   usuarioBD.setAdmin(rs.getBoolean("admin"));
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

	
}


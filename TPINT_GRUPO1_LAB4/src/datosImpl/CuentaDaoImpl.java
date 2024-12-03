package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.ClienteDao;
import datos.CuentaDao;
import entidad.Cuenta;

public class CuentaDaoImpl implements CuentaDao {
	
	private Conexion cn;
	
	@Override
	public boolean agregarCuenta(Cuenta cuenta) {
		boolean exito = true;
		cn = new Conexion();
		cn.Open();
		
		String query = "{CALL AgregarCuenta (?,?)}";
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setLong(1, cuenta.getCliente().getId());
			cst.setInt(2, cuenta.getTipoCuenta().getId());
			cst.execute();
			
		}
		catch(Exception e)
		{
			exito = false;
	        e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return exito;
	}

	@Override
	public List<Cuenta> obtenerCuentas() {
		List<Cuenta> lista = new ArrayList<Cuenta>();
		cn = new Conexion();
		cn.Open();
		
		
		String query = "{CALL ListarCuentas()}";
		try 
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			boolean hayResultados = cst.execute();
			
			if(hayResultados)
			{
				ResultSet rs = cst.getResultSet();
				while (rs.next())
				{
					ClienteDao clienteDao = new ClienteDaoImpl();
					Cuenta aux = new Cuenta();
					aux.setNroCuenta(rs.getLong("nro_cuenta"));
					aux.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
					aux.setCbu(rs.getString("cbu")); //CBU UNIQUE
					aux.setSaldo(rs.getBigDecimal("saldo"));
					aux.setEstado(rs.getBoolean("borrado"));
					
					aux.getTipoCuenta().setId(rs.getInt("id_tipo_cuenta"));
					aux.getTipoCuenta().setDescripcion(rs.getString("descripcion"));
					aux.setCliente(clienteDao.obtenerClientePorId(rs.getInt("id_cliente")));
					
					
					lista.add(aux);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			cn.close();
		}
		return lista;
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		boolean exito = true;
		cn = new Conexion();
		cn.Open();
		
		String query = "{CALL ModificarCuenta(?,?,?)}";
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setLong(1, cuenta.getNroCuenta());
			cst.setInt(2, cuenta.getTipoCuenta().getId());
			cst.setBoolean(3, cuenta.getEstado());
			cst.execute();
		}
		catch(Exception e)
		{
			exito = false;
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return exito;
	}

	@Override
	public boolean darDeBajaCuenta(Long nroCuenta) {
		boolean exito = true;
		cn = new Conexion();
		cn.Open();
		
		String query = "{CALL EliminarCuenta(?)}";
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setLong(1, nroCuenta);
			cst.execute();
		}
		catch(Exception e)
		{
			exito = false;
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return exito;
	}

	@Override
	public Cuenta obtenerCuentaXNroCuenta(Long nroCuenta) {
		Cuenta cuentaSeleccionada = new Cuenta();
		cn = new Conexion();
		cn.Open();
		
		
		String query = "{CALL ListarCuentaXNroCuenta(?)}";
		try 
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setLong(1, nroCuenta);
			boolean hayResultados = cst.execute();
			
			if(hayResultados)
			{
				ResultSet rs = cst.getResultSet();
				rs.next();
				
				ClienteDao clienteDao = new ClienteDaoImpl();
				cuentaSeleccionada.setNroCuenta(rs.getLong("nro_cuenta"));
				cuentaSeleccionada.setCbu(rs.getString("cbu")); //CBU UNIQUE
				cuentaSeleccionada.setEstado(rs.getBoolean("deleted"));
				
				cuentaSeleccionada.getTipoCuenta().setId(rs.getInt("id_tipo_cuenta"));
				cuentaSeleccionada.getTipoCuenta().setDescripcion(rs.getString("descripcion"));
				cuentaSeleccionada.setCliente(clienteDao.obtenerClientePorId(rs.getInt("id_cliente")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			cn.close();
		}
		return cuentaSeleccionada;
	}

	@Override
	public List<Cuenta> obtenerCuentasXIdCliente_2(int idCliente) {
		List<Cuenta> lista = new ArrayList<Cuenta>();
		cn = new Conexion();
		cn.Open();
		
		
		String query = "{CALL ListarCuentasXCliente(?)}";
		try 
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setLong(1, idCliente);
			boolean hayResultados = cst.execute();
			
			if(hayResultados)
			{
				ResultSet rs = cst.getResultSet();
				while (rs.next())
				{
					ClienteDao clienteDao = new ClienteDaoImpl();
					Cuenta aux = new Cuenta();
					aux.setNroCuenta(rs.getLong("nro_cuenta"));
					aux.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
					aux.setCbu(rs.getString("cbu")); //CBU UNIQUE
					aux.setSaldo(rs.getBigDecimal("saldo"));
					aux.setEstado(rs.getBoolean("borrado"));
					
					aux.getTipoCuenta().setId(rs.getInt("id_tipo_cuenta"));
					aux.getTipoCuenta().setDescripcion(rs.getString("descripcion"));
					aux.setCliente(clienteDao.obtenerClientePorId(rs.getInt("id_cliente")));
					
					
					lista.add(aux);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			cn.close();
		}
		return lista;
	}
	
	@Override
	public List<Cuenta> obtenerCuentasXIdCliente(int id) {
		List<Cuenta> lista = new ArrayList<Cuenta>();
		cn = new Conexion();
		cn.Open();
		String query = "{CALL ObtenerCuentasXIdCliente(?)}";
		try 
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setInt(1, id);
			boolean hayResultados = cst.execute();
			
			if(hayResultados)
			{
				ResultSet rs = cst.getResultSet();
				while (rs.next())
				{
					ClienteDao clienteDao = new ClienteDaoImpl();
					Cuenta aux = new Cuenta();
					aux.setNroCuenta(rs.getLong("nro_cuenta"));
					aux.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
					aux.setCbu(rs.getString("cbu")); //CBU UNIQUE
					aux.setSaldo(rs.getBigDecimal("saldo"));
					aux.setEstado(rs.getBoolean("deleted"));
					
					aux.getTipoCuenta().setId(rs.getInt("id_tipo_cuenta"));
					aux.setCliente(clienteDao.obtenerClientePorId(rs.getInt("id_cliente")));
					
					
					lista.add(aux);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			cn.close();
		}
		return lista;
	}

	@Override
	public Cuenta obtenerCuentaXCBU(String cbu) {
		Cuenta cuentaSeleccionada = new Cuenta();
		cn = new Conexion();
		cn.Open();
		
		
		String query = "{CALL obtenerCuentaXCBU(?)}";
		try 
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setString(1, cbu);
			boolean hayResultados = cst.execute();
			
			if(hayResultados)
			{
				ResultSet rs = cst.getResultSet();
				rs.next();
				
				ClienteDao clienteDao = new ClienteDaoImpl();
				cuentaSeleccionada.setNroCuenta(rs.getLong("nro_cuenta"));
				cuentaSeleccionada.setCbu(rs.getString("cbu")); //CBU UNIQUE
				cuentaSeleccionada.setEstado(rs.getBoolean("deleted"));
				
				cuentaSeleccionada.getTipoCuenta().setId(rs.getInt("id_tipo_cuenta"));
				cuentaSeleccionada.getTipoCuenta().setDescripcion(rs.getString("descripcion"));
				cuentaSeleccionada.setCliente(clienteDao.obtenerClientePorId(rs.getInt("id_cliente")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			cn.close();
		}
		return cuentaSeleccionada;
	}
	

}


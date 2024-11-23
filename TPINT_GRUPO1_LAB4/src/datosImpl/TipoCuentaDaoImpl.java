package datosImpl;


import java.util.List;

import datos.TipoCuentaDao;
import entidad.TipoCuenta;

public class TipoCuentaDaoImpl implements TipoCuentaDao {
	
	public TipoCuentaDaoImpl()
	{
		
	}

	@Override
	public List<TipoCuenta> obtenerCuentas() {
		
		return null;
	}
	
//	private Conexion cn;
//	private final String querySeleccion = "Select * from tiposcuenta";
//	@Override
//	public List<TipoCuenta> obtenerCuentas() {
//		List<TipoCuenta> lista = new ArrayList<TipoCuenta>();
//		cn = new Conexion();
//		cn.Open();
//		
//		try
//		{
//			PreparedStatement prst = cn.connection.prepareStatement(querySeleccion);
//			prst.execute();
//			ResultSet rs = prst.getResultSet();
//			while(rs.next())
//			{
//				TipoCuenta aux = new TipoCuenta();
//				aux.setId(rs.getInt("id"));
//				aux.setDescripcion(rs.getString("descripcion"));
//				
//				lista.add(aux);
//			}
//                 
//		}
//    } catch (SQLException e) {
//     
//        e.printStackTrace();
//    }
//}
//}
}
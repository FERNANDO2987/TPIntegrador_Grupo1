package datosImpl;

import java.sql.CallableStatement;

import datos.TransferenciaDao;
import entidad.Transferencia;

public class TransferenciaDaoImpl implements TransferenciaDao {
	
	private Conexion cn;

	@Override
	public boolean agregarTransferencia(Transferencia transferencia) {
		boolean exito = true;
		cn = new Conexion();
		cn.Open();
		
		String query = "{CALL AgregarCuenta (?,?)}";
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setInt(1, transferencia.getCuentaOrigen());
			cst.setInt(2, transferencia.getCuentaDestino());
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

}

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
		
		String query = "{CALL transferir (?,?,?,?)}";
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setInt(1, (int) transferencia.getCuentaOrigen().getNroCuenta());
			cst.setInt(2, (int) transferencia.getCuentaDestino().getNroCuenta());
			cst.setBigDecimal(3, transferencia.getMonto());
			cst.setString(4, transferencia.getDetalle());
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

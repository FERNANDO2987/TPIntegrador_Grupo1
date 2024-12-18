package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import datos.CuentaDao;
import datos.TransferenciaDao;
import entidad.Transferencia;

public class TransferenciaDaoImpl implements TransferenciaDao {
	
	private Conexion cn;

	@Override
	public boolean agregarTransferencia(Transferencia transferencia) {
		boolean exito = false;
		cn = new Conexion();
		cn.Open();	
		String query = "{CALL transferir (?,?,?,?)}";
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			
			if(validarCBU(transferencia.getCuentaDestino().getCbu()))
			{
				System.out.println("el CBU es valido");
				CuentaDao cuentaDao = new CuentaDaoImpl();
				transferencia.setCuentaDestino(cuentaDao.obtenerCuentaXCBU(transferencia.getCuentaDestino().getCbu()));
			
			
				if(validarMonto(transferencia))
				{
					System.out.println("el monto no supera el saldo");
					cst.setInt(1, (int) transferencia.getCuentaOrigen().getNroCuenta());
					cst.setInt(2, (int) transferencia.getCuentaDestino().getNroCuenta());
					cst.setBigDecimal(3, transferencia.getMonto());
					cst.setString(4, transferencia.getDetalle());
					cst.execute();
					exito = true;
				}
				System.out.println("el monto supera el saldo");
			}
			
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
	
	private boolean validarCBU(String cbu){
		cn = new Conexion();
		cn.Open();
		String query = "SELECT 1 FROM Cuentas WHERE cbu = ? AND deleted = 0";
		try 
		{
			PreparedStatement prst = (PreparedStatement) cn.connection.prepareStatement(query);
			prst.setString(1, cbu);
			try(ResultSet resultset = prst.executeQuery())
			{
				return resultset.next();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			cn.close();
		}
	}
	
	private boolean validarMonto(Transferencia transferencia) {
		System.out.println(transferencia.getCuentaOrigen().getSaldo());
	    return transferencia.getCuentaOrigen().getSaldo().compareTo(transferencia.getMonto()) >= 0;
	}


}

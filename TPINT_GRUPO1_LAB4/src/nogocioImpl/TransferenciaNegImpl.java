package nogocioImpl;

import datos.TransferenciaDao;
import datosImpl.TransferenciaDaoImpl;
import entidad.Transferencia;
import negocio.TransferenciaNeg;

public class TransferenciaNegImpl implements TransferenciaNeg {
	@Override
	public boolean agregarTransferencia(Transferencia transferencia) {
		TransferenciaDao transferenciaDao = new TransferenciaDaoImpl();
		return transferenciaDao.agregarTransferencia(transferencia);
	}

}

package nogocioImpl;

import java.util.ArrayList;

import datos.PaisDao;
import datosImpl.PaisDaoImpl;
import entidad.Pais;
import negocio.PaisNeg;

public class PaisNegImpl implements PaisNeg {
	
	
	private PaisDao  paisDao = new PaisDaoImpl();
	
	public  PaisNegImpl(PaisDao paisDao)
	{
		this.paisDao = paisDao;
	}
	
	
	public PaisNegImpl()
	{
		
	}
	
	@Override
	 public ArrayList<Pais> listarPaises()
	 {
		 return (ArrayList<Pais>) paisDao.obtenerPaises();
	 }
	
	

}
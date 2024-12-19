package nogocioImpl;

import java.util.ArrayList;

import datos.PaisDao;
import datosImpl.PaisDaoImpl;
import entidad.Pais;
import excepciones.PaisNoEncontradoException;
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
	
    @Override  
    public Pais obtenerPaisPorId(int id) throws PaisNoEncontradoException {  
        for (Pais pais : paisDao.obtenerPaises()) {  
            if (pais.getId() == id) {  
                return pais;  
            }  
        }
        throw new PaisNoEncontradoException();  //return null; // o lanzar una excepción si no se encuentra  
    }

}
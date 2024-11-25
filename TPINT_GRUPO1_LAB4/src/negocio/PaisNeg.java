package negocio;

import java.util.ArrayList;

import entidad.Pais;

public interface PaisNeg {
	 public ArrayList<Pais> listarPaises();
	    public Pais obtenerPaisPorId(int id);

}

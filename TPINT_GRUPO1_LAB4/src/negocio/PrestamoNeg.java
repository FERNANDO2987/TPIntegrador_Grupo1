package negocio;


import java.util.List;


import java.util.ArrayList;



import entidad.Prestamo;

public interface PrestamoNeg {

	public boolean agregarPrestamo(Prestamo prestamo);
	public Prestamo obtenerPrestamoxId(Long id);
	public List<Prestamo> obtenerPrestamos();
	public boolean aprobarPrestamoCliente(int id, String comentario);
	public boolean rechazarPrestamoCliente(int id, String comentario);

	
	 public ArrayList<Prestamo> listarPrestamos();


}

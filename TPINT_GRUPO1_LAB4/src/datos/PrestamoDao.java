package datos;

import java.util.List;


import entidad.Prestamo;

public interface PrestamoDao {
	public boolean agregarPrestamo(Prestamo prestamo);
	public Prestamo obtenerPrestamoxId(Long id);
	public List<Prestamo> obtenerPrestamos();
	public boolean aprobarPrestamo_2(Long id, String observacion);
	public boolean rechazarPrestamo(Long id);
	public List<Prestamo> obtenerDatosPrestamos();
	public boolean aprobarPrestamo(int id, String comentario);
	public boolean rechazarPrestamo(int id, String comentario);
	public List<Prestamo> obtenerPrestamosXCliente(int idCliente);
	
	
}
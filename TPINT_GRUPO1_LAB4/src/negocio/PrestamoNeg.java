package negocio;

import java.util.List;

import entidad.Prestamo;

public interface PrestamoNeg {
	public boolean agregarPrestamo(Prestamo prestamo);
	public Prestamo obtenerPrestamoxId(Long id);
	public List<Prestamo> obtenerPrestamos();
	public boolean aprobarPrestamo(Long id);
	public boolean rechazarPrestamo(Long id);
}

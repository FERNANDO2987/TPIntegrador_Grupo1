package nogocioImpl;


import java.util.List;

import datosImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import negocio.PrestamoNeg;


public class PrestamoNegImpl implements PrestamoNeg{

	PrestamoDaoImpl dao = new PrestamoDaoImpl();

	@Override
	public boolean agregarPrestamo(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return dao.agregarPrestamo(prestamo);
	}

	@Override
	public Prestamo obtenerPrestamoxId(Long id) {
		// TODO Auto-generated method stub
		return dao.obtenerPrestamoxId(id);
	}

	@Override
	public List<Prestamo> obtenerPrestamos() {
		// TODO Auto-generated method stub
		return dao.obtenerPrestamos();
	}

	@Override
	public boolean aprobarPrestamoCliente(int id, String comentario) {
		// TODO Auto-generated method stub
		return dao.aprobarPrestamo(id, comentario);
	}

	@Override
	public boolean rechazarPrestamoCliente(int id, String comentario) {
		// TODO Auto-generated method stub
		return dao.rechazarPrestamo(id, comentario);
	}

	@Override
	public List<Prestamo> obtenerDatosPrestamosCliente() {
		
		return dao.obtenerDatosPrestamos();
	}









}

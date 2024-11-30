package nogocioImpl;


import java.util.List;


import java.util.ArrayList;

import datos.PrestamoDao;

import datosImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import negocio.PrestamoNeg;


public class PrestamoNegImpl implements PrestamoNeg{

	PrestamoDaoImpl dao = new PrestamoDaoImpl();
	
	@Override
	public boolean agregarPrestamo(Prestamo prestamo) {
		return dao.agregarPrestamo(prestamo);
	}

	@Override
	public Prestamo obtenerPrestamoxId(Long id) {
		return (Prestamo)dao.obtenerPrestamoxId(id);
	}

	@Override
	public List<Prestamo> obtenerPrestamos() {
		return (List<Prestamo>)dao.obtenerPrestamos();
	}

	@Override
	public boolean aprobarPrestamo(Long id) {
		return dao.aprobarPrestamo(id);
	}

	@Override
	public boolean rechazarPrestamo(Long id) {
		return dao.rechazarPrestamo(id);
	}

public class PrestamoNegImpl implements PrestamoNeg {
	
	
private PrestamoDao  prestamoDao = new PrestamoDaoImpl();
	
	public  PrestamoNegImpl(PrestamoDao prestamoDao)
	{
		this.prestamoDao = prestamoDao;
	}
	
	
	public PrestamoNegImpl()
	{
		
	}
	

	
	@Override
	public ArrayList<Prestamo> listarPrestamos() {
		 return (ArrayList<Prestamo>) prestamoDao.obtenerDatosPrestamos();
		
	}



}

package datos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import entidad.Movimiento;
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
	

	public Map<Prestamo, List<Movimiento>> obtenerDatosClientePrestamo(int clienteId);
	public boolean actualizarCuota(int cuotasAActualizar, BigDecimal montoAActualizar, int idPrestamo, int nroCuenta, String detalle);

	
}
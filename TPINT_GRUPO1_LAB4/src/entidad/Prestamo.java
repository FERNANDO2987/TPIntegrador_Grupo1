package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Prestamo {
	
	   private long id;
	    private Cuenta cuenta;
	    private Cliente cliente;
	    private LocalDate fechaSolicitud;
	    private BigDecimal importe;
	    private int cuotas;
	    private boolean estado;

	    // Constructor vacío
	    public Prestamo() {}

	    // Constructor con parámetros
	    public Prestamo(long id, Cuenta cuenta, Cliente cliente, LocalDate fechaSolicitud, BigDecimal importe, int cuotas, boolean estado) {
	        this.id = id;
	        this.cuenta = cuenta;
	        this.cliente = cliente;
	        this.fechaSolicitud = fechaSolicitud;
	        this.importe = importe;
	        this.cuotas = cuotas;
	        this.estado = estado;
	    }
	    
	    // Getters y Setters
	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public Cuenta getCuenta() {
	        return cuenta;
	    }

	    public void setCuenta(Cuenta cuenta) {
	        this.cuenta = cuenta;
	    }

	    public Cliente getCliente() {
	        return cliente;
	    }

	    public void setCliente(Cliente cliente) {
	        this.cliente = cliente;
	    }

	    public LocalDate getFechaSolicitud() {
	        return fechaSolicitud;
	    }

	    public void setFechaSolicitud(LocalDate fechaSolicitud) {
	        this.fechaSolicitud = fechaSolicitud;
	    }

	    public BigDecimal getImporte() {
	        return importe;
	    }

	    public void setImporte(BigDecimal importe) {
	        this.importe = importe;
	    }

	    public int getCuotas() {
	        return cuotas;
	    }

	    public void setCuotas(int cuotas) {
	        this.cuotas = cuotas;
	    }

	    public boolean isEstado() {
	        return estado;
	    }

	    public void setEstado(boolean estado) {
	        this.estado = estado;
	    }

		@Override
		public String toString() {
			return "Prestamo [id=" + id + ", cuenta=" + cuenta + ", cliente=" + cliente + ", fechaSolicitud="
					+ fechaSolicitud + ", importe=" + importe + ", cuotas=" + cuotas + ", estado=" + estado + "]";
		}
	    
	    

}
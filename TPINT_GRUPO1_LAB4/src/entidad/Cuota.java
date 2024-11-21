package entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cuota {
	
	  private long id;
	    private int nroCuota;
	    private BigDecimal monto;
	    private LocalDateTime fechaPago;
	    private Prestamo prestamo;

	    // Constructor vacío
	    public Cuota() {}

	    // Constructor con parámetros
	    public Cuota(long id, int nroCuota, BigDecimal monto, LocalDateTime fechaPago, Prestamo prestamo) {
	        this.id = id;
	        this.nroCuota = nroCuota;
	        this.monto = monto;
	        this.fechaPago = fechaPago;
	        this.prestamo = prestamo;
	    }
	    
	    
	    // Getters y Setters
	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public int getNroCuota() {
	        return nroCuota;
	    }

	    public void setNroCuota(int nroCuota) {
	        this.nroCuota = nroCuota;
	    }

	    public BigDecimal getMonto() {
	        return monto;
	    }

	    public void setMonto(BigDecimal monto) {
	        this.monto = monto;
	    }

	    public LocalDateTime getFechaPago() {
	        return fechaPago;
	    }

	    public void setFechaPago(LocalDateTime fechaPago) {
	        this.fechaPago = fechaPago;
	    }

	    public Prestamo getPrestamo() {
	        return prestamo;
	    }

	    public void setPrestamo(Prestamo prestamo) {
	        this.prestamo = prestamo;
	    }

		@Override
		public String toString() {
			return "Cuota [id=" + id + ", nroCuota=" + nroCuota + ", monto=" + monto + ", fechaPago=" + fechaPago
					+ ", prestamo=" + prestamo + "]";
		}
	    
	    
	    

}
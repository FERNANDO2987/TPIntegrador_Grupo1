package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Movimiento {
	
	 private long id;
	    private LocalDate fecha;
	    private String detalle;
	    private BigDecimal importe;
	    private TipoMovimiento tipoMovimiento;
	    private boolean estado;

	    // Constructor vacío
	    public Movimiento() {}

	    // Constructor con parámetros
	    public Movimiento(long id, LocalDate fecha, String detalle, BigDecimal importe, TipoMovimiento tipoMovimiento, boolean estado) {
	        this.id = id;
	        this.fecha = fecha;
	        this.detalle = detalle;
	        this.importe = importe;
	        this.tipoMovimiento = tipoMovimiento;
	        this.estado = estado;
	    }
	    
	    
	    // Getters y Setters
	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public LocalDate getFecha() {
	        return fecha;
	    }

	    public void setFecha(LocalDate fecha) {
	        this.fecha = fecha;
	    }

	    public String getDetalle() {
	        return detalle;
	    }

	    public void setDetalle(String detalle) {
	        this.detalle = detalle;
	    }

	    public BigDecimal getImporte() {
	        return importe;
	    }

	    public void setImporte(BigDecimal importe) {
	        this.importe = importe;
	    }

	    public TipoMovimiento getTipoMovimiento() {
	        return tipoMovimiento;
	    }

	    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
	        this.tipoMovimiento = tipoMovimiento;
	    }

	    public boolean isEstado() {
	        return estado;
	    }

	    public void setEstado(boolean estado) {
	        this.estado = estado;
	    }

		@Override
		public String toString() {
			return "Movimiento [id=" + id + ", fecha=" + fecha + ", detalle=" + detalle + ", importe=" + importe
					+ ", tipoMovimiento=" + tipoMovimiento + ", estado=" + estado + "]";
		}
	    
	    
	    
	

}
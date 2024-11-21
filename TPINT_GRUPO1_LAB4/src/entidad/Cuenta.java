package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cuenta {
	
    private long nroCuenta;
    private Cliente cliente;
    private LocalDate fechaCreacion;
    private TipoCuenta tipoCuenta;
    private String cbu;
    private BigDecimal saldo;
    private boolean estado;

    // Constructor vacío
    public Cuenta() {
    	cliente = new Cliente();
    	tipoCuenta = new TipoCuenta();
    }

    // Constructor con parámetros
    public Cuenta(long nroCuenta, Cliente cliente, LocalDate fechaCreacion, TipoCuenta tipoCuenta, String cbu, BigDecimal saldo, boolean estado) {
        this.nroCuenta = nroCuenta;
        this.cliente = cliente;
        this.fechaCreacion = fechaCreacion;
        this.tipoCuenta = tipoCuenta;
        this.cbu = cbu;
        this.saldo = saldo;
        this.estado = estado;
    }
    
    
    // Getters y Setters
    public long getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(long nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

	@Override
	public String toString() {
		return "Cuenta [nroCuenta=" + nroCuenta + ", cliente=" + cliente + ", fechaCreacion=" + fechaCreacion
				+ ", tipoCuenta=" + tipoCuenta + ", cbu=" + cbu + ", saldo=" + saldo + "]";
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
    
    

}

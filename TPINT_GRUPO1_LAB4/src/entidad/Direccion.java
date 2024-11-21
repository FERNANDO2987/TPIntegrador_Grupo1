package entidad;

public class Direccion {
	
	   private long id;
	    private String calle;
	    private int numeroCasa;
	    private Localidad localidad;
	    private Cliente cliente;
	    
	    
	    // Constructor vacío
	    public Direccion() {}

	    // Constructor con parámetros
	    public Direccion(long id, String calle, int numeroCasa, Localidad localidad, Cliente cliente) {
	        this.id = id;
	        this.calle = calle;
	        this.numeroCasa = numeroCasa;
	        this.localidad = localidad;
	        this.cliente = cliente;
	    }
	    
	    // Getters y Setters
	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public String getCalle() {
	        return calle;
	    }

	    public void setCalle(String calle) {
	        this.calle = calle;
	    }

	    public int getNumeroCasa() {
	        return numeroCasa;
	    }

	    public void setNumeroCasa(int numeroCasa) {
	        this.numeroCasa = numeroCasa;
	    }

	    public Localidad getLocalidad() {
	        return localidad;
	    }

	    public void setLocalidad(Localidad localidad) {
	        this.localidad = localidad;
	    }

	    public Cliente getCliente() {
	        return cliente;
	    }

	    public void setCliente(Cliente cliente) {
	        this.cliente = cliente;
	    }

		@Override
		public String toString() {
			return "Direccion [id=" + id + ", calle=" + calle + ", numeroCasa=" + numeroCasa + ", localidad="
					+ localidad + ", cliente=" + cliente + "]";
		}
	    
	    

}

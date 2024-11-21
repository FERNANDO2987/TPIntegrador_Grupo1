package entidad;

public class Localidad {
	
	   private long id;
	    private Provincia provincia;
	    private String nombre;
	    
	    public Localidad() {}
	    
	    public Localidad(long id, Provincia provincia, String nombre) {
	        this.id = id;
	        this.provincia = provincia;
	        this.nombre = nombre;
	    }
	    
	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public Provincia getProvincia() {
	        return provincia;
	    }

	    public void setProvincia(Provincia provincia) {
	        this.provincia = provincia;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

		@Override
		public String toString() {
			return "Localidad [id=" + id + ", provincia=" + provincia + ", nombre=" + nombre + "]";
		}
	    
	  

}
package entidad;

public class Contacto {
	
    private long id;
    private String correo;
    private String telefono;
    private Cliente cliente;
    
    // Constructor vacío
    public Contacto() {}

    // Constructor con parámetros
    public Contacto(long id, String correo, String telefono, Cliente cliente) {
        this.id = id;
        this.correo = correo;
        this.telefono = telefono;
        this.cliente = cliente;
    }
    
    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

	@Override
	public String toString() {
		return "Contacto [id=" + id + ", correo=" + correo + ", telefono=" + telefono + ", cliente=" + cliente
				+ "]";
	}
    
    
    

}
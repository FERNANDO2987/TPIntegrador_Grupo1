package entidad;

import java.util.Date;

public class Cliente {
    private int id;
    private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private String sexo;
    private String usuario;
    private String direccion;
    private Localidad localidad;
    private Provincia provincia;
    public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	private String password;
    private Pais paisNacimiento;  // Relación con la clase Pais
    private Date fechaNacimiento;
    private String correo;
    private String telefono;
    private String celular;
    private Boolean admin;
    

    
  
    // Constructor
    public Cliente(int id, String dni, String cuil, String nombre, String apellido, String sexo, String usuario, 
                   String password, Pais paisNacimiento, Date fechaNacimiento,String correo,String telefono,String celular, Boolean admin) {
        this.id = id;
        this.dni = dni;
        this.cuil = cuil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.usuario = usuario;
        this.password = password;
        this.paisNacimiento = paisNacimiento;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.telefono = telefono;
        this.celular = celular;
        this.admin = admin;
       
    }

    public Cliente(int id) {
    	this.id = id;
    }

    public Cliente()
    {
    	this.paisNacimiento = new Pais();
    }
    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String i) {
        this.dni = i;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pais getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(Pais paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
    
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    
    public String getPaisNombre() {
    	return this.paisNacimiento.getNombre();
    }
    
    public String getLocalidadNombre() {
    	return this.localidad.getNombre();
    }
    
    public String getProvinciaNombre() {
    	return this.provincia.getNombre();
    }

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", sexo=" + sexo + ", usuario=" + usuario + ", direccion=" + direccion + ", localidad=" + localidad
				+ ", provincia=" + provincia + ", password=" + password + ", paisNacimiento=" + paisNacimiento
				+ ", fechaNacimiento=" + fechaNacimiento + ", correo=" + correo + ", telefono=" + telefono
				+ ", celular=" + celular + ", admin=" + admin + "]";
	}

	

	
	
	    
	    
}

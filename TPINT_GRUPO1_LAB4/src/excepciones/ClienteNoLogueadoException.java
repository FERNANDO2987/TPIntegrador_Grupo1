package excepciones;

public class ClienteNoLogueadoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNoLogueadoException(){
		
	}

	@Override
	public String getMessage() {
		return "Usuario no logueado";
	}
	
}

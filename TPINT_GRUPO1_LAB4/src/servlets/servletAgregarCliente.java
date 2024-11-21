package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.ClienteDao;
import datos.PaisDao;
import datosImpl.ClienteDaoImpl;
import datosImpl.PaisDaoImpl;
import entidad.Cliente;
import entidad.Pais;
import negocio.ClienteNeg;
import negocio.PaisNeg;
import nogocioImpl.ClienteNegImpl;
import nogocioImpl.PaisNegImpl;



@WebServlet("/servletAgregarCliente")
public class servletAgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteDao clienteDao = new ClienteDaoImpl();
	private PaisDao paisDao = new PaisDaoImpl();
	
	private ClienteNeg clienteNeg = new ClienteNegImpl();
	private PaisNeg paisNeg = new PaisNegImpl();
	
    public servletAgregarCliente() {
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dniStr = request.getParameter("dni");
 	    String cuilStr = request.getParameter("cuil");
	    String nombre = request.getParameter("nombre");
	    String apellido = request.getParameter("apellido");
	    String sexo = request.getParameter("sexo");
	    String nacionalidad = request.getParameter("pais");
	    String fechaNacimientoStr = request.getParameter("fechaNacimiento");
	    String correoElectronico = request.getParameter("correoElectronico");
	    String telefono = request.getParameter("telefono");
	    String celular = request.getParameter("celular");
	    String usuario = request.getParameter("usuario");
	    String contraseña = request.getParameter("contraseña");
	  
	  
    
        long dni;
        long cuil;
	    try {
	    	dni = Long.parseLong(dniStr);  
	    	cuil = Long.parseLong(cuilStr);  
	    } catch (NumberFormatException e) {
	        response.getWriter().write("Error: DNI o CUIL inválido.");
	        return;
	    }
        
        
	    Date fechaNacimiento = null;
	    try {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        fechaNacimiento = formatter.parse(fechaNacimientoStr); // Convierte la cadena a un objeto Date
	    } catch (ParseException e) {
	        response.getWriter().write("Error: Formato de fecha inválido.");
	        return;
	    }

      
	   
	    List<Pais> paises = paisNeg.listarPaises();
	    Pais paisNacimiento = paises.stream()
	                                .filter(p -> p.getNombre().equalsIgnoreCase(nacionalidad))
	                                .findFirst()
	                                .orElse(null);
	    
	    

	    if (paisNacimiento == null) {
	        response.getWriter().write("Error: País de nacimiento no encontrado.");
	        return;
	    }

	    Cliente cliente = new Cliente(
	    	    0L,              // id
	    	    dni,             // dni
	    	    cuil,            // cuil
	    	    nombre,          // nombre
	    	    apellido,        // apellido
	    	    sexo,            // sexo
	    	    usuario,         // usuario
	    	    contraseña,      // password
	    	    paisNacimiento,  // paisNacimiento
	    	    fechaNacimiento, // fechaNacimiento
	    	    correoElectronico, // correo
	    	    telefono,        // telefono
	    	    celular,         // celular
	    	    false,            // admin (o true si el cliente es admin)
	    	    false			// falso si no esta borrado
	    	);

	    boolean estado = clienteNeg.insertarCliente(cliente);

	    if (estado) {
	        response.getWriter().write("Cliente agregado exitosamente.");
	    } else {
	        response.getWriter().write("Error al agregar cliente.");
	    }
	    
		
		
	}

}

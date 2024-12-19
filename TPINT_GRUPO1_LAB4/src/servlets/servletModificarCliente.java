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

import datosImpl.ClienteDaoImpl;
import entidad.Cliente;
import entidad.Pais;
import negocio.ClienteNeg;
import negocio.PaisNeg;
import nogocioImpl.ClienteNegImpl;
import nogocioImpl.PaisNegImpl;



/**
 * Servlet implementation class servletModificarCliente
 */
@WebServlet("/servletModificarCliente")
public class servletModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ClienteNeg clienteNeg = new ClienteNegImpl();
	private PaisNeg paisNeg = new PaisNegImpl();
    public servletModificarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
		
		 String idString = request.getParameter("id");  
	        String dni = request.getParameter("dni");  
	        String cuil = request.getParameter("cuil");  
	        String nombre = request.getParameter("nombre");  
	        String apellido = request.getParameter("apellido");  
	        String sexo = request.getParameter("sexo");  
	        String usuario = request.getParameter("usuario");  
	        String password = request.getParameter("password");  
	        String paisIdString = request.getParameter("pais"); // Obtener el ID del país  
	        String fechaNacimientoString = request.getParameter("fechaNacimiento");  
	        String correo = request.getParameter("correo");  
	        String telefono = request.getParameter("telefono");  
	        String celular = request.getParameter("celular");  
	        boolean esAdmin = request.getParameter("admin") != null;  

	        // Convertir entradas a sus tipos apropiados  
	        int id = Integer.parseInt(idString);  
	        int paisId = Integer.parseInt(paisIdString);  

	        // Obtener el objeto Pais usando el ID  
	        PaisNegImpl paisNeg = new PaisNegImpl();  
	     
	        // Obtener el país de nacimiento por ID
	        Pais paisNacimiento = new Pais();
	        
	        try {
	        	List<Pais> paises = paisNeg.listarPaises();
	            paisNacimiento = paises.stream()
	                    .filter(p -> p.getId() == paisId)
	                    .findFirst()
	                    .orElse(null);
	            
	        }
	        catch(Exception e) {
	        	System.out.println(e.getMessage());
	        }
	        
	        if (paisNacimiento == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Error: País de nacimiento no encontrado.");
                return;
            }


	        
	        Date fechaNacimiento;
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                fechaNacimiento = formatter.parse(fechaNacimientoString);
            } catch (ParseException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Error: Formato de fecha inválido. Use 'yyyy-MM-dd'.");
                return;
            }

	        // Crear objeto Cliente y establecer propiedades  
	        Cliente cliente = new Cliente();  
	        cliente.setId(id);  
	        cliente.setDni(dni);  
	        cliente.setCuil(cuil);  
	        cliente.setNombre(nombre);  
	        cliente.setApellido(apellido);  
	        cliente.setSexo(sexo);  
	        cliente.setUsuario(usuario);  
	        cliente.setPassword(password);  
	        cliente.setPaisNacimiento(paisNacimiento); // Ahora pasa el objeto Pais  
	        cliente.setFechaNacimiento(fechaNacimiento);  
	        cliente.setCorreo(correo);  
	        cliente.setTelefono(telefono);  
	        cliente.setCelular(celular);  
	        cliente.setAdmin(esAdmin);  

	        
	        boolean modificar = clienteNeg.editarCliente(cliente);
	        
	        if (modificar) {
	            request.getSession().setAttribute("mensajeExito", "Cliente Modificado exitosamente.");
	        } else {
	            request.getSession().setAttribute("mensajeError", "Error al Modificar cliente.");
	        }
	        response.sendRedirect("ListarClientes.jsp");

            
		} catch (Exception e) {
     	   request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());  
            e.printStackTrace();  
            request.getRequestDispatcher("ListarClientes.jsp").forward(request, response); 
     }
	     
		
	}
	
	

	

}

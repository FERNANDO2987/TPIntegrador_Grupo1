package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.ClienteDao;
import datosImpl.ClienteDaoImpl;
import entidad.Cliente;

/**
 * Servlet implementation class servletModificarCliente
 */
@WebServlet("/servletModificarCliente")
public class servletModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletModificarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
        ClienteDaoImpl clienteDao = new ClienteDaoImpl();
        List<Cliente> clientes = clienteDao.obtenerClientes();

        // Obtener el criterio de b�squeda del par�metro de solicitud
        String criterio = request.getParameter("criterio");
        
        // Filtrar los clientes por nombre o apellido si el criterio no es nulo ni vac�o
        if (criterio != null && !criterio.trim().isEmpty()) {
            clientes = clientes.stream()
                    .filter(cliente -> cliente.getNombre().toLowerCase().contains(criterio.toLowerCase()) ||
                                       cliente.getApellido().toLowerCase().contains(criterio.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Configurar la lista filtrada como atributo de la solicitud y reenviar a la vista
        request.setAttribute("clientes", clientes);
        request.setAttribute("criterio", criterio);  // para mantener el valor en el campo de b�squeda
        request.getRequestDispatcher("ListarClientes.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		int idCliente;
//
//        // Obtener y validar el ID del cliente
//        try {
//            idCliente = Integer.parseInt(request.getParameter("id")); // Asegúrate de que este nombre coincida con el del formulario
//        } catch (NumberFormatException e) {
//            request.setAttribute("error", "ID de cliente no válido.");
//            request.getRequestDispatcher("modificarCliente.jsp").forward(request, response);
//            return;
//        }
//
//		if(request.getParameter("btnModificarCliente") != null)
//		{
//			Cliente cliente = new Cliente();
//			cliente.setId(idCliente);
//			cliente.setDni(Integer.parseInt(request.getParameter("txtDni")));
//			cliente.setCuil(Integer.parseInt(request.getParameter("txtCuil")));
//			
//			cliente.setNombre(request.getParameter("txtNombre"));
//			cliente.setApellido(request.getParameter("txtApellido"));
//			cliente.setSexo(request.getParameter("txtSexo"));
//	        
//			ClienteDao clienteDao = new ClienteDaoImpl();
//			boolean exito;
//			if(clienteDao.modificarCliente(cliente))
//			{
//				exito = true;
//				System.out.println("Operacion exitosa");
//			}
//			else
//			{
//				exito = false;
//				System.out.println("Sin cambios en DB");
//			}
//			request.setAttribute("exito", exito );
//			
//		}
//		request.getRequestDispatcher("ModificarCliente.jsp").forward(request, response);
	}

	

}

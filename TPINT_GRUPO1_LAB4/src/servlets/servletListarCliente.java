package servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.ClienteDaoImpl;
import entidad.Cliente;


@WebServlet("/servletListarCliente")
public class servletListarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteDaoImpl clienteDao = new ClienteDaoImpl();
        List<Cliente> clientes = clienteDao.obtenerClientes();

        // Obtener el criterio de búsqueda del parámetro de solicitud
        String criterio = request.getParameter("criterio");
        
        // Filtrar los clientes por nombre o apellido si el criterio no es nulo ni vacío
        if (criterio != null && !criterio.trim().isEmpty()) {
            clientes = clientes.stream()
                    .filter(cliente -> cliente.getNombre().toLowerCase().contains(criterio.toLowerCase()) ||
                                       cliente.getApellido().toLowerCase().contains(criterio.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Configurar la lista filtrada como atributo de la solicitud y reenviar a la vista
        request.setAttribute("clientes", clientes);
        request.setAttribute("criterio", criterio);  // para mantener el valor en el campo de búsqueda
        request.getRequestDispatcher("ListarClientes.jsp").forward(request, response);
    }
    
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

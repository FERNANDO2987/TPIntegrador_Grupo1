package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.ClienteDaoImpl;

/**
 * Servlet implementation class servletEliminarCliente
 */
@WebServlet("/servletEliminarCliente")
public class servletEliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEliminarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        
        if (idParam != null) {
            int idCliente = Integer.parseInt(idParam);
            ClienteDaoImpl clienteDao = new ClienteDaoImpl();
            
            boolean resultado = clienteDao.darDeBajaCliente(idCliente);
            
            if (resultado) {
                request.setAttribute("mensaje", "Cliente eliminado correctamente.");
            } else {
                request.setAttribute("mensaje", "Error al eliminar el cliente.");
            }
        } else {
            request.setAttribute("mensaje", "ID de cliente no proporcionado.");
        }

        // Redirigir de nuevo a la lista de clientes
        request.getRequestDispatcher("ListarClientes.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

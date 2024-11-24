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
         if (idParam != null && !idParam.isEmpty()) {  
             try {  
                 int id = Integer.parseInt(idParam);  
                 ClienteDaoImpl clienteDao = new ClienteDaoImpl();  
                 boolean eliminado = clienteDao.darDeBajaCliente(id);  

                 if (eliminado) {  
                	    request.getSession().setAttribute("mensajeExito", "Cliente eliminado correctamente.");  
                	} else {  
                	    request.getSession().setAttribute("mensajeError", "No se pudo eliminar el cliente.");  
                	}  

             } catch (Exception e) {  
                 request.getSession().setAttribute("mensajeError", "Error inesperado: " + e.getMessage());  
                 e.printStackTrace();  
             }  
         } else {  
             request.getSession().setAttribute("mensajeError", "No se proporcionó un ID de cliente para eliminar.");  
         }  

         // Redirigir al listado de clientes  
         response.sendRedirect("ListarClientes.jsp");  
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlets;

import java.io.IOException;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidad.Prestamo;
import nogocioImpl.PrestamoNegImpl;

/**
 * Servlet implementation class servletListarPrestamos
 */
@WebServlet("/servletListarPrestamos")
public class servletListarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	PrestamoNegImpl prestamoNeg = new PrestamoNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListarPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		  if (request.getParameter("btnListarPrestamos") != null) {
	            try {
	                List<Prestamo> prestamos = prestamoNeg.listarPrestamos();

	                request.setAttribute("prestamos", prestamos);

	                // Redirigir a la vista JSP
	                request.getRequestDispatcher("ListarPrestamos.jsp").forward(request, response);
	            } catch (Exception e) {
	                e.printStackTrace();

	                request.setAttribute("error", "Ocurrió un error al procesar la solicitud.");
	                request.getRequestDispatcher("Error.jsp").forward(request, response);
	            }
		  }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}

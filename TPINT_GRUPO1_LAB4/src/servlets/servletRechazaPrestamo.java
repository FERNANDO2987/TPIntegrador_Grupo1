package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import negocio.PrestamoNeg;
import nogocioImpl.PrestamoNegImpl;

/**
 * Servlet implementation class servletRechazaPrestamo
 */
@WebServlet("/servletRechazaPrestamo")
public class servletRechazaPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	  PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	
    public servletRechazaPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int prestamoId = Integer.parseInt(request.getParameter("prestamoIdRechazar"));
	        String comentarios = request.getParameter("comentarios");

	        
	       
	        if (comentarios == null || comentarios.trim().isEmpty()) {
	            request.setAttribute("errorMessage", "El campo de comentarios es obligatorio.");
	            request.getRequestDispatcher("ListarPrestamos.jsp").forward(request, response);  // Redirigir de vuelta al formulario
	            return;  // Terminar ejecución del método
	        }
	        
	        
	        boolean exito = prestamoNeg.rechazarPrestamoCliente(prestamoId, comentarios);

	        if (exito) {
	            request.setAttribute("successMessage", "Préstamo Rechazado con éxito.");
	        } else {
	            request.setAttribute("errorMessage", "Error al Rechazado el préstamo.");
	        }

	        // Redirige al JSP con la lista de préstamos actualizada
	        request.getRequestDispatcher("ListarPrestamos.jsp").forward(request, response);

	}

}

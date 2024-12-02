package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import negocio.PrestamoNeg;
import nogocioImpl.PrestamoNegImpl;

/**
 * Servlet implementation class servletAutorizarPrestamo
 */
@WebServlet("/servletAutorizarPrestamo")
public class servletAutorizarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  PrestamoNeg prestamoNeg = new PrestamoNegImpl();
    
    public servletAutorizarPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 int prestamoId = Integer.parseInt(request.getParameter("prestamoId"));
	        String comentarios = request.getParameter("comentarios");

	        if (comentarios == null || comentarios.trim().isEmpty()) {
	            request.setAttribute("errorMessage", "El campo de comentarios es obligatorio.");
	            request.getRequestDispatcher("ListarPrestamos.jsp").forward(request, response);  // Redirigir de vuelta al formulario
	            return;  // Terminar ejecución del método
	        }
	        
	        
	        boolean exito = prestamoNeg.aprobarPrestamoCliente(prestamoId, comentarios);

	        if (exito) {
	            request.setAttribute("successMessage", "Préstamo Autorizado con éxito.");
	        } else {
	            request.setAttribute("errorMessage", "Error al autorizar el préstamo.");
	        }

	        // Redirige al JSP con la lista de préstamos actualizada
	        request.getRequestDispatcher("ListarPrestamos.jsp").forward(request, response);
		
	}

}

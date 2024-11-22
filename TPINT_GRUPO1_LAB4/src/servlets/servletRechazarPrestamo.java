package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.PrestamoDaoImpl;

/**
 * Servlet implementation class servletRechazarPrestamo
 */
@WebServlet("/servletRechazarPrestamo")
public class servletRechazarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletRechazarPrestamo() {
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
		// TODO Auto-generated method stub
		if (request.getParameter("btnRechazar") != null) 
		{
	        System.out.println("Botón Rechazar fue presionadoooooooooooooooo");
	        Long id = Long.parseLong(request.getParameter("id"));
            PrestamoDaoImpl dao = new PrestamoDaoImpl();
            dao.rechazarPrestamo(id);
            response.sendRedirect(request.getContextPath() + "/servletListarPrestamos?btnListarPrestamos=Traer+prestamos");
		}
		if (request.getParameter("btnAceptar") != null) 
		{
	        System.out.println("Botón Aceptar fue presionadooooooooooooooo");
	        Long id = Long.parseLong(request.getParameter("id"));
            PrestamoDaoImpl dao = new PrestamoDaoImpl();
            dao.aprobarPrestamo(id);
            response.sendRedirect(request.getContextPath() + "/servletListarPrestamos?btnListarPrestamos=Traer+prestamos");
		}
		doGet(request, response);
	}

}

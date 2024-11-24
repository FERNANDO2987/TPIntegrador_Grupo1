package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.PrestamoDaoImpl;
import entidad.Prestamo;

/**
 * Servlet implementation class servletListarPrestamos
 */
@WebServlet("/servletListarPrestamos")
public class servletListarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// TODO Auto-generated method stub
		if(request.getParameter("btnListarPrestamos")!=null)
		{
			System.out.println("AGUANTE BOOOOOCAAAAAAAAAAAAAAAAAAAAAAA");
			PrestamoDaoImpl dao = new PrestamoDaoImpl();
			ArrayList<Prestamo> lista = (ArrayList<Prestamo>) dao.obtenerPrestamos();
			
			request.setAttribute("ListaP", lista);

			RequestDispatcher rd;
			 
            rd=request.getRequestDispatcher("ListarPrestamos.jsp");
            rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}
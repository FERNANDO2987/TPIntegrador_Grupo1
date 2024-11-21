package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.PaisDaoImpl;
import entidad.Pais;

/**
 * Servlet implementation class servletPais
 */
@WebServlet("/servletPais")
public class servletPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletPais() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 PaisDaoImpl paisDao = new PaisDaoImpl();
		    List<Pais> paises = paisDao.obtenerPaises();

		    // Imprimir la lista de países en la consola
		    for (Pais pais : paises) {
		        System.out.println("ID: " + pais.getId() + ", Nombre: " + pais.getNombre());
		    }

		    // Agregar la lista de países al request para ser utilizada en la página
		    request.setAttribute("paises", paises);

		    // Redirigir a la página JSP que maneje la presentación
		    request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
   	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

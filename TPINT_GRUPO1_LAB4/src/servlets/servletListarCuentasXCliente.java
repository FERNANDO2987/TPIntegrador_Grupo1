package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import negocio.CuentaNeg;
import nogocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class servletListarCuentasXCliente
 */
@WebServlet("/servletListarCuentasXCliente")
public class servletListarCuentasXCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListarCuentasXCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnListarCuentasXCliente") != null)
		{			
			int idCliente = Integer.parseInt(request.getParameter("idusuario"));
			CuentaNeg cuentaNeg = new CuentaNegImpl();
			List<Cuenta> listado = new ArrayList<Cuenta>();
			listado = cuentaNeg.obtenerCuentasXIdCliente_2(idCliente);
			
			request.setAttribute("listado", listado);
			request.getRequestDispatcher("VerCuentasAsociadas.jsp").forward(request, response);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

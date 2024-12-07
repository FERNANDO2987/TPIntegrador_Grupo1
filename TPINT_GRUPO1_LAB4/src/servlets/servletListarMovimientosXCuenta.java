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
import entidad.Movimiento;
import negocio.CuentaNeg;
import nogocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class servletListarMovimientosXCuenta
 */
@WebServlet("/servletListarMovimientosXCuenta")
public class servletListarMovimientosXCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListarMovimientosXCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnListarMovimientosXCuenta") != null)
		{			
			long nroCuenta = Long.parseLong(request.getParameter("nroCuenta"));
			CuentaNeg cuentaNeg = new CuentaNegImpl();
			List<Movimiento> listaM = new ArrayList<Movimiento>();
			listaM = cuentaNeg.listarMovimientosXCuenta(nroCuenta);
			
			request.setAttribute("listado", listaM);
			request.getRequestDispatcher("HistorialDeMovimientos.jsp").forward(request, response);
		}
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

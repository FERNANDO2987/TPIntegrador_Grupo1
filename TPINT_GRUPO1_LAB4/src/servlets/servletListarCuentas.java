package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.TipoCuenta;
import negocio.CuentaNeg;
import negocio.TipoCuentaNeg;
import nogocioImpl.CuentaNegImpl;
import nogocioImpl.TipoCuentaNegImpl;


/**
 * Servlet implementation class servletListarCuentas
 */
@WebServlet("/servletListarCuentas")
public class servletListarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListarCuentas() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//variable exito proveniente del servlet modificarCuenta
		if (session.getAttribute("exito") != null)
		{
			String exito = (String) session.getAttribute("exito");
		}	
		CuentaNeg cuentaNeg = new CuentaNegImpl();
		List<Cuenta> listado = new ArrayList<Cuenta>();
		listado = cuentaNeg.obtenerCuentas();
		
		request.setAttribute("listado", listado);
		request.getRequestDispatcher("ListarCuentas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnModificar") != null)
		{
			Long nroCuenta = Long.parseLong(request.getParameter("nroCuenta"));
			
			TipoCuentaNeg tipoCuentaNeg = new TipoCuentaNegImpl();
			List<TipoCuenta> listaTipoCuenta = tipoCuentaNeg.obtenerCuentas();
			request.setAttribute("listaTiposCuentas", listaTipoCuenta);
			
			CuentaNeg cuentaNeg = new CuentaNegImpl();
			Cuenta cuenta = cuentaNeg.obtenerCuentaXNroCuenta(nroCuenta);
			request.setAttribute("cuenta", cuenta);
			
			request.getRequestDispatcher("ModificarCuenta.jsp").forward(request, response);
		}
		if(request.getParameter("btnEliminar") != null)
		{
			Long nroCuenta = Long.parseLong(request.getParameter("nroCuenta"));
			Cuenta aux = new Cuenta();
			aux.setNroCuenta(nroCuenta);
			CuentaNeg cuentaNeg = new CuentaNegImpl();
			cuentaNeg.darDeBajaCuenta(nroCuenta);
			doGet(request, response);
		}
	}

}

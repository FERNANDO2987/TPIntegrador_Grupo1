package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.ClienteDao;
import datos.CuentaDao;
import datos.TipoCuentaDao;
import datosImpl.ClienteDaoImpl;
import datosImpl.CuentaDaoImpl;
import datosImpl.TipoCuentaDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoCuenta;

/**
 * Servlet implementation class servletAgregarCuenta
 */
@WebServlet("/servletAgregarCuenta")
public class servletAgregarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAgregarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAgregar") != null)
		{
			Long idCliente = Long.parseLong(request.getParameter("cliente"));
			int idTipoCuenta = Integer.parseInt(request.getParameter("TipoCuenta"));
			
			Cuenta aux = new Cuenta();
			aux.getCliente().setId(idCliente);
			aux.getTipoCuenta().setId(idTipoCuenta);
			CuentaDao cuentaDao = new CuentaDaoImpl();
			cuentaDao.agregarCuenta(aux);
			boolean exito = true;
			request.setAttribute("exitoAlAgregar", exito);
			
		}
		
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		List<TipoCuenta> listaTiposCuenta = new ArrayList<TipoCuenta>();
		TipoCuentaDao tipoCuentaDao = new TipoCuentaDaoImpl();
		ClienteDao clienteDao = new ClienteDaoImpl();
		
		listaClientes = clienteDao.obtenerClientes();
		listaTiposCuenta = tipoCuentaDao.obtenerCuentas();
		
		request.setAttribute("listaClientes", listaClientes);
		request.setAttribute("listaTipoCuenta", listaTiposCuenta);
		request.getRequestDispatcher("AgregarCuenta.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

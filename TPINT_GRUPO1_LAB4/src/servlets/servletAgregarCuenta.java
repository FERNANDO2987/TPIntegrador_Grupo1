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
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocio.TipoCuentaNeg;
import nogocioImpl.ClienteNegImpl;
import nogocioImpl.CuentaNegImpl;
import nogocioImpl.TipoCuentaNegImpl;

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
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAgregar") != null)
		{
			CuentaNeg cuentaNeg = new CuentaNegImpl();
			int idCliente = Integer.parseInt(request.getParameter("cliente"));
			int idTipoCuenta = Integer.parseInt(request.getParameter("TipoCuenta"));
			boolean exito;
			//validar
			if(cuentaNeg.obtenerCountCuentasXCliente(idCliente) < 3)
			{
				Cuenta aux = new Cuenta();
				aux.getCliente().setId(idCliente);
				aux.getTipoCuenta().setId(idTipoCuenta);
				cuentaNeg.agregarCuenta(aux);
				exito = true;
				System.out.println("creado");
				
			}
			else
			{
				System.out.println("excedido");
				exito = false;
			}
			
			request.setAttribute("exitoAlAgregar", exito);
			
		}
		
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		List<TipoCuenta> listaTiposCuenta = new ArrayList<TipoCuenta>();
		TipoCuentaNeg tipoCuentaNeg = new TipoCuentaNegImpl();
		ClienteNeg clienteNeg = new ClienteNegImpl();
		
		listaClientes = clienteNeg.listarClientes();
		listaTiposCuenta = tipoCuentaNeg.obtenerCuentas();
		
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

package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.TipoCuenta;
import negocio.CuentaNeg;
import negocio.TipoCuentaNeg;
import nogocioImpl.CuentaNegImpl;
import nogocioImpl.TipoCuentaNegImpl;

/**
 * Servlet implementation class servletModificarCuenta
 */
@WebServlet("/servletModificarCuenta")
public class servletModificarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletModificarCuenta() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cuenta cuenta = (Cuenta)request.getSession().getAttribute("cuentaAModificar");
		CuentaNeg cuentaNeg = new CuentaNegImpl();
		cuenta = cuentaNeg.obtenerCuentaXNroCuenta(cuenta.getNroCuenta());
		TipoCuentaNeg tipoCuentaNeg = new TipoCuentaNegImpl();
		List<TipoCuenta> lista = tipoCuentaNeg.obtenerCuentas();
		
		request.setAttribute("cuenta", cuenta);
		request.setAttribute("listaTiposCuentas", lista);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ModificarCuenta.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String exito = new String();
		if(request.getParameter("btnModificarCuenta") != null)
		{	
			CuentaNeg cuentaNeg = new CuentaNegImpl();
			// Cuenta Modificada
			Cuenta cuenta = new Cuenta();
			cuenta.setNroCuenta(Long.parseLong((String) request.getParameter("txtCuenta")));
			cuenta.getTipoCuenta().setId(Integer.parseInt(request.getParameter("TipoCuenta")));
			cuenta.getCliente().setId(Integer.parseInt(request.getParameter("idCliente")));
			String chkActivo = request.getParameter("chkActivo");
			if("Activo".equals(chkActivo))
			{
				cuenta.setEstado(false);
			}
			if("Inactivo".equals(chkActivo))
			{
				cuenta.setEstado(true);
			}
			
			System.out.println(cuenta.getNroCuenta());
			System.out.println(cuenta.getTipoCuenta().getId());
			System.out.println(cuenta.getCliente().getId());
			System.out.println("borrado: " + cuenta.getEstado());
			
			System.out.println("----------");
			// Cuenta Sin Modificar
			Cuenta cuentaEnBd = cuentaNeg.obtenerCuentaXNroCuenta(cuenta.getNroCuenta());
			System.out.println(cuentaEnBd.getNroCuenta());
			System.out.println(cuentaEnBd.getTipoCuenta().getId());
			System.out.println(cuentaEnBd.getCliente().getId());
			System.out.println("borrado: " + cuentaEnBd.getEstado());
			
			String resultadoModificacion = "error, no hubo exito en la modificacion";
			
				if(cuentaNeg.modificarCuenta(cuenta))
				{
					resultadoModificacion = "se realizo el cambio con exito";
				}
				
			request.setAttribute("resultadoModificacion", resultadoModificacion);
			
			// volver a cargar datos
			Cuenta cuentaAModificar = (Cuenta)request.getSession().getAttribute("cuentaAModificar");
			cuentaAModificar = cuentaNeg.obtenerCuentaXNroCuenta(cuenta.getNroCuenta());
			
			TipoCuentaNeg tipoCuentaNeg = new TipoCuentaNegImpl();
			List<TipoCuenta> lista = tipoCuentaNeg.obtenerCuentas();
			
			request.setAttribute("cuenta", cuentaAModificar);
			request.setAttribute("listaTiposCuentas", lista);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ModificarCuenta.jsp");
			dispatcher.forward(request, response);	
		}
	}
}

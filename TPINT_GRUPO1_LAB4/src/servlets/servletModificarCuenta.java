package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import entidad.Cuenta;

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
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String exito = new String();
		if(request.getParameter("btnModificarCuenta") != null)
		{	
			
			Cuenta cuenta = new Cuenta();
			cuenta.setNroCuenta(Long.parseLong((String) request.getParameter("txtCuenta")));
			cuenta.getTipoCuenta().setId(Integer.parseInt(request.getParameter("TipoCuenta")));
			cuenta.getCliente().setId(Integer.parseInt(request.getParameter("idCliente")));
			int chkActivo = Integer.parseInt(request.getParameter("chkActivo"));
			if(chkActivo == 1)
			{
				System.out.println("me puse true");
				cuenta.setEstado(true);
			}
			else
			{
				System.out.println("me puse false");
				cuenta.setEstado(false);
			}
			
			
			System.out.println(cuenta.getNroCuenta());
			System.out.println(cuenta.getTipoCuenta().getId());
			System.out.println(cuenta.getCliente().getId());
			System.out.println(cuenta.getEstado());
			CuentaDao cuentaDao = new CuentaDaoImpl();
			
			if(cuenta.getEstado() && cuentaDao.obtenerCountCuentasXCliente(cuenta.getCliente().getId()) < 3)
			{
				if(cuentaDao.modificarCuenta(cuenta))
				{
					exito = "Modificacion hecha con exito";
					System.out.println("Modificacion hecha con exito");
				}
				else
				{
					exito = "Modificacion rechazada";
					System.out.println("Modificacion rechazada");
				}
			}
			else
			{
				exito = "Numero de cuentas maxima superada";
				System.out.println("Numero de cuentas maxima superada");
			}
			request.setAttribute("exito", exito );
			
		}
		HttpSession session = request.getSession();
		session.setAttribute("exitoModificacion", exito);
		response.sendRedirect("servletListarCuentas");
		
	}

}

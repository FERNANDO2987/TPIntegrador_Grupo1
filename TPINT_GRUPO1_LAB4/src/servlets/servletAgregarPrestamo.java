package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import nogocioImpl.ClienteNegImpl;
import nogocioImpl.CuentaNegImpl;
import nogocioImpl.PrestamoNegImpl;

/**
 * Servlet implementation class servletAgregarPrestamo
 */
@WebServlet("/servletAgregarPrestamo")
public class servletAgregarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PrestamoNegImpl neg = new PrestamoNegImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAgregarPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNeg cuentaNeg = new CuentaNegImpl();
		ClienteNeg clienteNeg = new ClienteNegImpl();
		List<Cuenta> lista = new ArrayList<Cuenta>();
		
		Cliente clienteLogeado =  (Cliente) request.getSession().getAttribute("usuario");
		clienteLogeado = clienteNeg.obtenerClientePorUsuario(clienteLogeado.getUsuario());
		
		lista = cuentaNeg.obtenerCuentasXIdCliente_2(clienteLogeado.getId());
		request.setAttribute("listaDeMisCuentas", lista);
		request.getRequestDispatcher("SolicitarPrestamos.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			
			long cuenta;
			int cliente;
			cuenta = request.getParameter("cuentaDestino") != null ? Long.valueOf(request.getParameter("cuentaDestino")) : -1;
			cliente = request.getParameter("usuarioID") != null ? Integer.parseInt(request.getParameter("usuarioID")) : -1;
			
			String imp = request.getParameter("monto");
			String cuo = request.getParameter("cuotas");

			imp = imp.trim();
			cuo = cuo.trim();
			
			System.out.println("cuenta: " + cuenta);
			System.out.println("cliente: " + cliente);
			System.out.println("importe: " + imp);
			System.out.println("cuotas: " + cuo);
			
			
			// Validar campos obligatorios
			if (imp == null || imp.isEmpty() || 
				cuo == null || cuo.isEmpty() ||
				cliente == -1 || 
				cuenta == -1)
			{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Error: Todos los campos obligatorios deben estar presentes.");
                return;
			}
			// convertir y llamar datos de session
			int cuotas = Integer.parseInt(cuo);
			BigDecimal importe = new BigDecimal(imp);

			
			Prestamo p = new Prestamo();
			p.setCliente(new Cliente(cliente));
			p.setCuenta(new Cuenta(cuenta));
			p.setCuotas(cuotas);
			p.setImporte(importe);
			//insertar prestamo
			boolean estado = neg.agregarPrestamo(p);
			
			// Respuesta al prestamo
            if (estado) {  
                request.setAttribute("mensajeExito", "Prestamo agregado exitosamente.");  
            } else {  
                request.setAttribute("mensajeError", "Error al agregar cliente.");  
            }
            
         // Redirigir al JSP
            request.getRequestDispatcher("Home.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());  
            e.printStackTrace();  
            request.getRequestDispatcher("Home.jsp").forward(request, response); 
		}
		
		
		doGet(request, response);
	}

}

package servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			HttpSession session = request.getSession(true); // Si la sesión no existe, la crea 
			String imp = request.getParameter("importe");
			String cuo = request.getParameter("cuotas");
			imp = imp.trim();
			cuo = cuo.trim();
			
			
			// Validar campos obligatorios
			if (imp == null || imp.isEmpty() || 
				cuo == null || cuo.isEmpty() )
			{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Error: Todos los campos obligatorios deben estar presentes.");
                return;
			}
			
			// convertir y llamar datos de session
			int cuotas = Integer.parseInt(cuo);
			BigDecimal importe = new BigDecimal(imp);
			Cuenta cuenta = (Cuenta)session.getAttribute("cuenta");
			Cliente cliente = (Cliente)session.getAttribute("usuario");
			
			Prestamo p = new Prestamo();
			p.setCliente(cliente);
			p.setCuenta(cuenta);
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
            request.getRequestDispatcher("AgregarPrestamo.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());  
            e.printStackTrace();  
            request.getRequestDispatcher("AgregarPrestamo.jsp").forward(request, response); 
		}
		doGet(request, response);
	}

}

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
			String imp = request.getParameter("monto");
			String cuo = request.getParameter("cuotas");
			System.out.println("GET PARAMETER CORRECTAMENTE STRING");
			imp = imp.trim();
			cuo = cuo.trim();
			System.out.println("TRIM CORRECTAMENTE");
			
			
			// Validar campos obligatorios
			if (imp == null || imp.isEmpty() || 
				cuo == null || cuo.isEmpty() )
			{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Error: Todos los campos obligatorios deben estar presentes.");
                return;
			}
			System.out.println("VALIDAR STRINGS");
			// convertir y llamar datos de session
			int cuotas = Integer.parseInt(cuo);
			BigDecimal importe = new BigDecimal(imp);
			System.out.println("PASÓ LOS STRING A NUMERO");
			Cuenta cuenta = (Cuenta)session.getAttribute("cuenta");
			Cliente cliente = (Cliente)session.getAttribute("usuario");
			/*
			Cuenta cuenta = new Cuenta();
			cuenta.setNroCuenta(222222222);
			Cliente cliente = new Cliente();
			cliente.setId(2);
			 */
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
            request.getRequestDispatcher("SolicitarPrestamos.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());  
            e.printStackTrace();  
            request.getRequestDispatcher("SolicitarPrestamos.jsp").forward(request, response); 
		}
		doGet(request, response);
	}

}

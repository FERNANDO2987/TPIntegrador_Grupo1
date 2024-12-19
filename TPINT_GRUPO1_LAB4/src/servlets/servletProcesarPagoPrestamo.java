package servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.PrestamoDaoImpl;


/**
 * Servlet implementation class servletProcesarPagoPrestamo
 */
@WebServlet("/servletProcesarPagoPrestamo")
public class servletProcesarPagoPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletProcesarPagoPrestamo() {
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Instanciar el DAO para manejo de préstamos
	    PrestamoDaoImpl prestamoDao = new PrestamoDaoImpl();

	    try {
	        // Obtener y validar los parámetros del formulario
	        String idPrestamoParam = request.getParameter("idPrestamo");
	        String cuentaSelectParam = request.getParameter("cuentaSelect");
	        String numeroCuotaParam = request.getParameter("numeroCuota");

	        
	        BigDecimal importePago = new BigDecimal(request.getParameter("importePago"));
	        String detallePago = request.getParameter("detallePago");
	        
	        int idPrestamo = 0;
	        int idCuentaSelect =0;
	        int numeroCuota =0;
	        try {
	        	idPrestamo = Integer.parseInt(idPrestamoParam);
	        } catch (NumberFormatException e) {
	            
	            request.setAttribute("error", "La Cuenta No es Valida no válido.");
	            request.getRequestDispatcher("error.jsp").forward(request, response);
	            return;
	        }
	        
	        try {
	        	idCuentaSelect = Integer.parseInt(cuentaSelectParam);
	        } catch (NumberFormatException e) {
	            
	            request.setAttribute("error", "La CuentaSelect No es Valida.");
	            request.getRequestDispatcher("error.jsp").forward(request, response);
	            return;
	        }
	        
	        try {
	        	numeroCuota = Integer.parseInt(numeroCuotaParam);
	        } catch (NumberFormatException e) {
	            
	            request.setAttribute("error", "La Cuota No es Valida.");
	            request.getRequestDispatcher("error.jsp").forward(request, response);
	            return;
	        }
	        
	        
	        
	        
	        

	        // Imprimir los valores recibidos por consola para depuración
	        System.out.println("Datos recibidos:");
	        System.out.println("ID Préstamo: " + idPrestamo);
	        //System.out.println("Cuenta Select: " + cuentaSelect);
	        System.out.println("Número Cuota: " + numeroCuota);
	        System.out.println("Importe Pago: " + importePago);
	        System.out.println("Detalle Pago: " + detallePago);

	        // Validaciones básicas
	        if (importePago.compareTo(BigDecimal.ZERO) <= 0) {
	            throw new IllegalArgumentException("El importe del pago debe ser mayor a cero.");
	        }

	        // Llamar al método para actualizar la cuota
	        boolean pagoExitoso = prestamoDao.actualizarCuota(
	            numeroCuota, importePago, idPrestamo, idCuentaSelect, detallePago
	        );

	        // Redirigir o mostrar un mensaje según el resultado
	        if (pagoExitoso) {
	            System.out.println("Pago realizado con éxito."); // Mensaje en consola
	            request.setAttribute("mensaje", "Pago realizado con éxito.");
	        } else {
	            System.out.println("Error al procesar el pago."); // Mensaje en consola
	            request.setAttribute("mensaje", "Error al procesar el pago. Intente nuevamente.");
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("Error de formato en los datos ingresados."); // Mensaje en consola
	        request.setAttribute("mensaje", "Error en los datos ingresados. Verifique los campos.");
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        System.out.println("Validación fallida: " + e.getMessage()); // Mensaje en consola
	        request.setAttribute("mensaje", e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Error inesperado: " + e.getMessage()); // Mensaje en consola
	        request.setAttribute("mensaje", "Ocurrió un error inesperado. Intente nuevamente.");
	        e.printStackTrace();
	    }

	    // Redirigir al JSP con el mensaje correspondiente
	    request.getRequestDispatcher("PagarCuotasPrestamo.jsp").forward(request, response);
	}


}

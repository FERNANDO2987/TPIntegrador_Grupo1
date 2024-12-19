package servlets;

import java.io.IOException;
//import java.util.List;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.PrestamoDaoImpl;
import entidad.Movimiento;
//import entidad.Prestamo;
import entidad.Prestamo;

/**
 * Servlet implementation class servletPrestamoSolicitado
 */
@WebServlet("/servletPrestamoSolicitado")
public class servletPrestamoSolicitado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	PrestamoDaoImpl prestamos = new PrestamoDaoImpl();
	
    public servletPrestamoSolicitado() {
        super();
        // TODO Auto-generated constructor stub
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener el parámetro idusuario de la solicitud
        String idUsuarioParam = request.getParameter("idusuario");
        int idUsuario = 0;
        
        try {
            idUsuario = Integer.parseInt(idUsuarioParam);
        } catch (NumberFormatException e) {
            
            request.setAttribute("error", "ID de usuario no válido.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

   
        PrestamoDaoImpl prestamoDao = new PrestamoDaoImpl();
        
        // Obtener los préstamos y sus movimientos asociados
        Map<Prestamo, List<Movimiento>> prestamosConMovimientos = prestamoDao.obtenerDatosClientePrestamo(idUsuario);

    
        if (prestamosConMovimientos == null || prestamosConMovimientos.isEmpty()) {
            System.out.println("No se encontraron préstamos para el usuario con ID: " + idUsuario);
            request.setAttribute("error", "No se encontraron préstamos para el usuario especificado.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        } else {
            System.out.println("Préstamos encontrados para el usuario con ID: " + idUsuario);
         
        }

        
        request.setAttribute("prestamosConMovimientos", prestamosConMovimientos);

       
        request.getRequestDispatcher("PagarCuotasPrestamo.jsp").forward(request, response);
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

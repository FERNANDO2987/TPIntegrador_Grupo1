package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.InformesDaoImpl;
import entidad.ReporteMovimientos;

/**
 * Servlet implementation class servletGenerarInformes
 */
@WebServlet("/servletGenerarInformes")
public class servletGenerarInformes extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	InformesDaoImpl informesDao = new InformesDaoImpl();
	
    public servletGenerarInformes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     // Leer las fechas desde el JSP  
        String fechaInicioParam = request.getParameter("fechaInicio");  
        String fechaFinParam = request.getParameter("fechaFin");  

        LocalDate fechaInicio;  
        LocalDate fechaFin;  

        try {  
            // Validar y convertir las fechas proporcionadas  
            fechaInicio = (fechaInicioParam != null && !fechaInicioParam.isEmpty())  
                    ? LocalDate.parse(fechaInicioParam)  
                    : LocalDate.of(2024, 1, 1);  

            fechaFin = (fechaFinParam != null && !fechaFinParam.isEmpty())  
                    ? LocalDate.parse(fechaFinParam)  
                    : LocalDate.of(2024, 12, 31);  
        } catch (DateTimeParseException e) {  
            
            fechaInicio = LocalDate.of(2024, 1, 1);  
            fechaFin = LocalDate.of(2024, 12, 31);  
        }  

        // Llamar al DAO para generar el reporte  
        List<ReporteMovimientos> reporteList = informesDao.generarReporteMovimientos(fechaInicio, fechaFin);  

        // Crear arreglos para ingresos y egresos mensuales  
        Double[] ingresosPorMes = new Double[12];  
        Double[] egresosPorMes = new Double[12];  

        // Inicializar los arreglos a 0  
        for (int i = 0; i < 12; i++) {  
            ingresosPorMes[i] = 0.0;  
            egresosPorMes[i] = 0.0;  
        }  

        // Llenar los arreglos con datos mensuales  
        for (ReporteMovimientos reporte : reporteList) {  
            int mes = reporte.getMes() - 1; // Ajustar el índice (0-11)  
            ingresosPorMes[mes] += reporte.getTotalIngresos();  
            egresosPorMes[mes] += reporte.getTotalEgresos();  
        }  

        // Enviar los datos al JSP  
        request.setAttribute("ingresosPorMes", ingresosPorMes);  
        request.setAttribute("egresosPorMes", egresosPorMes);  
        request.setAttribute("fechaInicio", fechaInicio.toString());  
        request.setAttribute("fechaFin", fechaFin.toString());  

        // Redirigir al JSP  
        request.getRequestDispatcher("InformeIngresosEgresos.jsp").forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

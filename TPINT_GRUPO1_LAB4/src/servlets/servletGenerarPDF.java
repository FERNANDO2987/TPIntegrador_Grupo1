package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/*import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
*/
import datosImpl.InformesDaoImpl;
import entidad.ReporteMovimientos;




/**
 * Servlet implementation class servletGenerarPDF
 */
@WebServlet("/servletGenerarPDF")
public class servletGenerarPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletGenerarPDF() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	

        String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaFinStr = request.getParameter("fechaFin");

     
        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
        LocalDate fechaFin = LocalDate.parse(fechaFinStr);

 
        InformesDaoImpl informesDao = new InformesDaoImpl();
        List<ReporteMovimientos> reporteList = informesDao.generarReporteMovimientos(fechaInicio, fechaFin);

    
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"Reporte_Ingresos_Egresos.pdf\"");

        try (OutputStream out = response.getOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Título del informe
            Paragraph title = new Paragraph("Reporte de Ingresos y Egresos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
         // Agregar un espacio entre el título y la tabla
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            // Crear la tabla de ingresos y egresos por mes
            PdfPTable table = new PdfPTable(4);  // 4 columnas: Año, Mes, Ingresos, Egresos
            table.addCell(createCell("Año", BaseColor.BLUE, BaseColor.BLACK));  // Encabezado de la columna "Año"
            table.addCell(createCell("Mes", BaseColor.BLUE, BaseColor.BLACK));  // Encabezado de la columna "Mes"
            table.addCell(createCell("Ingresos", BaseColor.BLUE, BaseColor.BLACK));  // Encabezado de la columna "Ingresos"
            table.addCell(createCell("Egresos", BaseColor.BLUE, BaseColor.BLACK));  // Encabezado de la columna "Egresos"

            // Llenar la tabla con los datos de reporteList
            for (ReporteMovimientos reporte : reporteList) {
                table.addCell(createCell(String.valueOf(reporte.getAnio()), BaseColor.WHITE, BaseColor.BLACK));
                String nombreMes = getNombreMes(reporte.getMes());
                table.addCell(createCell(nombreMes, BaseColor.WHITE, BaseColor.BLACK));
                table.addCell(createCell(String.format("%.2f", reporte.getTotalIngresos()), BaseColor.WHITE, BaseColor.BLACK));
                table.addCell(createCell(String.format("%.2f", reporte.getTotalEgresos()), BaseColor.WHITE, BaseColor.BLACK));
            }

            // Agregar la tabla al documento
            document.add(table);

            // Cerrar el documento
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el PDF");
        }
    }

    // Método para conviertir el número del mes en el nombre del mes
    private String getNombreMes(int mes) {
        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        return meses[mes - 1]; // Mes es un valor entre 1 y 12
    }

    
    // Método para crear celdas con colores
    private com.itextpdf.text.pdf.PdfPCell createCell(String content, BaseColor backgroundColor, BaseColor borderColor) {
        com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph(content));
        cell.setBackgroundColor(backgroundColor);
        cell.setBorderColor(borderColor);
        cell.setPadding(5);
        return cell;
        
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	
}

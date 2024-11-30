package Principal;

import java.util.List;

import datosImpl.PrestamoDaoImpl;

import entidad.Prestamo;

public class principal {

	public static void main(String[] args) {

	  
        PrestamoDaoImpl prestamoDao = new PrestamoDaoImpl();  

      
        try {  
            List<Prestamo> prestamos = prestamoDao.obtenerDatosPrestamos();  

          
            if (prestamos != null && !prestamos.isEmpty()) {  
               
                for (Prestamo prestamo : prestamos) {  
                    System.out.println("ID: " + prestamo.getId());  
                    System.out.println("Nombre: " + prestamo.getCliente().getNombre());  
                    System.out.println("Apellido: " + prestamo.getCliente().getApellido());  
                    System.out.println("Monto: " + prestamo.getImporte());  
                    System.out.println("Cuotas: " + prestamo.getCuotas());  
                    System.out.println("Estado: " + (prestamo.isEstado() ? "Pendiente" : "Autorizado"));  
                    System.out.println("Observaciones: " + prestamo.getObservaciones());  
                    System.out.println("-----------------------------");  
                }  
            } else {  
                System.out.println("No se encontraron préstamos.");  
            }  
        } catch (Exception e) {  
             
            System.err.println("Error al obtener los datos de préstamos: " + e.getMessage());  
            e.printStackTrace();  
        }  

    

	}
}


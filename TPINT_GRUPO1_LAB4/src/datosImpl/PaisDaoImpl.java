package datosImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.PaisDao;
import entidad.Pais;

public class PaisDaoImpl  implements PaisDao{
	
	private Conexion cn;

	   public PaisDaoImpl() {
	        cn = new Conexion(); // Inicializa la conexi�n
	    }

    @Override
    public List<Pais> obtenerPaises() {
        List<Pais> paises = new ArrayList<>();
        ResultSet rs = null;

        try {
            cn.Open(); // Abrir la conexi�n
            String query = "CALL ObtenerPaises();"; // Llamar al procedimiento almacenado
            rs = cn.query(query); // Ejecutar la consulta

            // Comprobar si el ResultSet no es nulo y tiene datos
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id"); // Obtener el ID del pa�s
                    String nombre = rs.getString("nombre"); // Obtener el nombre del pa�s
                    paises.add(new Pais(id, nombre)); // Crear el objeto Pais y agregarlo a la lista
                }
            } else {
                System.out.println("El ResultSet est� vac�o.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close(); // Cerrar ResultSet
                }
                cn.close(); // Cerrar la conexi�n
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return paises; // Retornar la lista de pa�ses
    }
	

}

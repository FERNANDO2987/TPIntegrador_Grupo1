package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Pais;
import negocio.ClienteNeg;
import negocio.PaisNeg;
import nogocioImpl.ClienteNegImpl;
import nogocioImpl.PaisNegImpl;



@WebServlet("/servletAgregarCliente")
public class servletAgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	private ClienteNeg clienteNeg = new ClienteNegImpl();
	private PaisNeg paisNeg = new PaisNegImpl();
	
    public servletAgregarCliente() {
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 try {
	            // Obtener par�metros del formulario
	            String dniStr = request.getParameter("dni");
	            String cuilStr = request.getParameter("cuil");
	            String nombre = request.getParameter("nombre");
	            String apellido = request.getParameter("apellido");
	            String sexo = request.getParameter("sexo");
	            String usuario = request.getParameter("usuario");
	            String password = request.getParameter("password");
	            String nacionalidad = request.getParameter("pais"); // Se asume que es el ID del pa�s
	            String fechaNacimientoStr = request.getParameter("fechaNacimiento");
	            String correoElectronico = request.getParameter("correoElectronico");
	            String telefono = request.getParameter("telefono");
	            String celular = request.getParameter("celular");

	            // Validar campos obligatorios
	            if (dniStr == null || dniStr.isEmpty() || 
	                cuilStr == null || cuilStr.isEmpty() || 
	                nombre == null || nombre.isEmpty() || 
	                apellido == null || apellido.isEmpty() || 
	                sexo == null || sexo.isEmpty() || 
	                nacionalidad == null || nacionalidad.isEmpty() || 
	                fechaNacimientoStr == null || fechaNacimientoStr.isEmpty() || 
	                correoElectronico == null || correoElectronico.isEmpty() || 
	                usuario == null || usuario.isEmpty() || 
	                password == null || password.isEmpty()) {
	                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                response.getWriter().write("Error: Todos los campos obligatorios deben estar presentes.");
	                return;
	            }

	            // Validar y convertir DNI, CUIL y nacionalidad (ID del pa�s)
	            String dni = dniStr.trim();
	            String cuil = cuilStr.trim();
	            int idPais;
	            try {
	                idPais = Integer.parseInt(nacionalidad);
	            } catch (NumberFormatException e) {
	                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                response.getWriter().write("Error: ID del pa�s inv�lido.");
	                return;
	            }

	            // Validar fecha de nacimiento
	            Date fechaNacimiento;
	            try {
	                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	                fechaNacimiento = formatter.parse(fechaNacimientoStr);
	            } catch (ParseException e) {
	                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                response.getWriter().write("Error: Formato de fecha inv�lido. Use 'yyyy-MM-dd'.");
	                return;
	            }

	            // Obtener el pa�s de nacimiento por ID
	            List<Pais> paises = paisNeg.listarPaises();
	            Pais paisNacimiento = paises.stream()
	                    .filter(p -> p.getId() == idPais)
	                    .findFirst()
	                    .orElse(null);

	            if (paisNacimiento == null) {
	                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                response.getWriter().write("Error: Pa�s de nacimiento no encontrado.");
	                return;
	            }

	            // Crear el objeto Cliente
	            Cliente cliente = new Cliente(
	                    0, // ID inicial
	                    dni,  // DNI como String
	                    cuil, // CUIL como String
	                    nombre,
	                    apellido,
	                    sexo,
	                    usuario,
	                    password,
	                    paisNacimiento,
	                    fechaNacimiento,
	                    correoElectronico,
	                    telefono,
	                    celular,
	                    false // No admin por defecto
	            );

	            // Insertar cliente
	            boolean estado = clienteNeg.insertarCliente(cliente);

	            // Respuesta al cliente
	            if (estado) {
	                response.setStatus(HttpServletResponse.SC_OK);
	                response.getWriter().write("Cliente agregado exitosamente.");
	            } else {
	                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	                response.getWriter().write("Error al agregar cliente.");
	            }
	        } catch (Exception e) {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.getWriter().write("Error inesperado: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

		
	}



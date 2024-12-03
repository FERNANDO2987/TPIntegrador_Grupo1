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

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Transferencia;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocio.TransferenciaNeg;
import nogocioImpl.ClienteNegImpl;
import nogocioImpl.CuentaNegImpl;
import nogocioImpl.TransferenciaNegImpl;

@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletTransferencia() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			CuentaNeg cuentaNeg = new CuentaNegImpl();
			ClienteNeg clienteNeg = new ClienteNegImpl();
			List<Cuenta> lista = new ArrayList<Cuenta>();
			
			Cliente clienteLogeado =  (Cliente) request.getSession().getAttribute("usuario");
			clienteLogeado = clienteNeg.obtenerClientePorUsuario(clienteLogeado.getUsuario());
			
			lista = cuentaNeg.obtenerCuentasXIdCliente(clienteLogeado.getId());
			request.setAttribute("listaDeMisCuentas", lista);
			request.getRequestDispatcher("TransferirAOtroCliente.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(request.getParameter("btnTransferir") != null)
			{
				System.out.println("ejecute el if");
				CuentaNeg cuentaNeg = new CuentaNegImpl();
				
				int cuentaOrigen;
				String cuentaDestino;
				BigDecimal monto;
				String detalle;
				
				cuentaOrigen = request.getParameter("cuentaOrigen") != null ? Integer.parseInt(request.getParameter("cuentaOrigen")) : -1;
				cuentaDestino = request.getParameter("cbuDestino") != null ? request.getParameter("cbuDestino") : "";
				monto = request.getParameter("monto") != null ? new BigDecimal(request.getParameter("monto")) : new BigDecimal(0);
				detalle = request.getParameter("descripcion") != null ? request.getParameter("descripcion") : "";
				Transferencia transferencia = new Transferencia();
				
				transferencia.getCuentaOrigen().setNroCuenta(cuentaOrigen);
				transferencia.setCuentaDestino(cuentaNeg.obtenerCuentaXCBU(cuentaDestino));
				transferencia.setMonto(monto);
				transferencia.setDetalle(detalle);
				
				TransferenciaNeg transferenciaNeg = new TransferenciaNegImpl();
				transferenciaNeg.agregarTransferencia(transferencia);
				request.getRequestDispatcher("TransferirAOtroCliente.jsp").forward(request, response);
			}
	}

}

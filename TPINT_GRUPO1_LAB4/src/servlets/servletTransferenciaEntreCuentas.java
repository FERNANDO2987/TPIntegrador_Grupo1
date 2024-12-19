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

@WebServlet("/servletTransferenciaEntreCuentas")
public class servletTransferenciaEntreCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public servletTransferenciaEntreCuentas() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNeg cuentaNeg = new CuentaNegImpl();
		ClienteNeg clienteNeg = new ClienteNegImpl();
		List<Cuenta> lista = new ArrayList<Cuenta>();
		
		Cliente clienteLogeado =  (Cliente) request.getSession().getAttribute("usuario");
		clienteLogeado = clienteNeg.obtenerClientePorUsuario(clienteLogeado.getUsuario());
		
		lista = cuentaNeg.obtenerCuentasXIdCliente_2(clienteLogeado.getId());
		request.setAttribute("listaDeMisCuentas", lista);
		request.getRequestDispatcher("TransferirCuentasPropias.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnTransferir") != null)
		{
			CuentaNeg cuentaNeg = new CuentaNegImpl();
			int cuentaOrigen;
			int cuentaDestino;
			BigDecimal monto;
			String detalle;
			
			cuentaOrigen = request.getParameter("cuentaOrigen") != null ? Integer.parseInt(request.getParameter("cuentaOrigen")) : -1;
			cuentaDestino = request.getParameter("cuentaDestino") != null ? Integer.parseInt(request.getParameter("cuentaDestino")) : -1;
			monto = request.getParameter("monto") != null ? new BigDecimal(request.getParameter("monto")) : new BigDecimal(0);
			detalle = request.getParameter("descripcion") != null ? request.getParameter("descripcion") : "";
			Transferencia transferencia = new Transferencia();
			
			transferencia.getCuentaOrigen().setNroCuenta(cuentaOrigen);
			transferencia.setCuentaOrigen(cuentaNeg.obtenerCuentaXNroCuenta((long) cuentaOrigen));
			transferencia.getCuentaDestino().setNroCuenta(cuentaDestino);
			transferencia.setCuentaDestino(cuentaNeg.obtenerCuentaXNroCuenta((long) cuentaDestino));
			transferencia.setMonto(monto);
			transferencia.setDetalle(detalle);
			
			TransferenciaNeg transferenciaNeg = new TransferenciaNegImpl();
			String exito = new String();
			if(transferenciaNeg.agregarTransferencia(transferencia))
			{
				exito = "Transferencia realizada con exito";
			}
			else
			{
				exito = "No se pudo realizar la transferencia";
			}
			
			
			ClienteNeg clienteNeg = new ClienteNegImpl();
			List<Cuenta> lista = new ArrayList<Cuenta>();
			
			Cliente clienteLogeado =  (Cliente) request.getSession().getAttribute("usuario");
			clienteLogeado = clienteNeg.obtenerClientePorUsuario(clienteLogeado.getUsuario());
			
			lista = cuentaNeg.obtenerCuentasXIdCliente_2(clienteLogeado.getId());
			request.setAttribute("listaDeMisCuentas", lista);
			request.setAttribute("exitoTransfer", exito);
			request.getRequestDispatcher("TransferirCuentasPropias.jsp").forward(request, response);
		}
	}

}

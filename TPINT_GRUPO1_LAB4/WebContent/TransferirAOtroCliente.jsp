<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="t1ext/html; charset=ISO-8859-1">
<title>Transferencia a Cuentas Externas</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <div class="text-center mb-4">
        <h3>Transferir a otro cliente</h3>
    </div>
    
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form>
                <div class="form-group">
                    <label for="cuentaOrigen">Cuenta de origen</label>
                    <select class="form-control" id="cuentaOrigen" required>
                        <option value="">Selecciona una cuenta</option>
                        <option value="1">Cuenta corriente - 1</option>
                        <option value="3">Caja de ahorro - 3</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="cbuDestino">CBU de la cuenta de destino</label>
                    <input type="text" class="form-control" id="cbuDestino" placeholder="Ingresa el CBU de destino" required>
                </div>
                
                
                <div class="form-group">
                    <label for="monto">Monto a transferir</label>
                    <input type="number" class="form-control" id="monto" placeholder="Ingrese el monto" required>
                </div>
                
                <div class="form-group">
                    <label for="descripcion">Detalle</label>
                    <input type="text" class="form-control" id="descripcion" placeholder="Motivo de la transferencia">
                </div>
                
                <button type="submit" class="btn btn-primary btn-block">Realizar transferencia</button>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
package entidad;

public class ReporteCuotas {
    
    private int anio;
    private int mes;
    private double totalPagado;
    private double totalPendiente;

    public ReporteCuotas(int anio, int mes, double totalPagado, double totalPendiente) {
        this.anio = anio;
        this.mes = mes;
        this.totalPagado = totalPagado;
        this.totalPendiente = totalPendiente;
    }

    // Getters y setters
    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public double getTotalPendiente() {
        return totalPendiente;
    }

    public void setTotalPendiente(double totalPendiente) {
        this.totalPendiente = totalPendiente;
    }
}
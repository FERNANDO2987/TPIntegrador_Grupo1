package entidad;

public class ReporteMovimientos {
	
	   private int anio;
	    private int mes;
	    private double totalIngresos;
	    private double totalEgresos;

	    public ReporteMovimientos(int anio, int mes, double totalIngresos, double totalEgresos) {
	        this.anio = anio;
	        this.mes = mes;
	        this.totalIngresos = totalIngresos;
	        this.totalEgresos = totalEgresos;
	    }

	    // Getters y Setters
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

	    public double getTotalIngresos() {
	        return totalIngresos;
	    }

	    public void setTotalIngresos(double totalIngresos) {
	        this.totalIngresos = totalIngresos;
	    }

	    public double getTotalEgresos() {
	        return totalEgresos;
	    }

	    public void setTotalEgresos(double totalEgresos) {
	        this.totalEgresos = totalEgresos;
	    }

		@Override
		public String toString() {
			return "ReporteMovimientos [anio=" + anio + ", mes=" + mes + ", totalIngresos=" + totalIngresos
					+ ", totalEgresos=" + totalEgresos + "]";
		}
	    
	    

}

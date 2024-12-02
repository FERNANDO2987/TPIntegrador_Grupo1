package entidad;

public class ReporteMovimientos {
	
	 private double totalIngresos;
	 private double totalEgresos;
	 
	 public ReporteMovimientos(double totalIngresos, double totalEgresos) {
	        this.totalIngresos = totalIngresos;
	        this.totalEgresos = totalEgresos;
	    }

	    public double getTotalIngresos() {
	        return totalIngresos;
	    }

	    public double getTotalEgresos() {
	        return totalEgresos;
	    }

		@Override
		public String toString() {
			return "ReporteMovimientos [totalIngresos=" + totalIngresos + ", totalEgresos=" + totalEgresos + "]";
		}
	    
	    

}

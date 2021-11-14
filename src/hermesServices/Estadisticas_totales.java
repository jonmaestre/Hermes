package hermesServices;

public class Estadisticas_totales extends Estadisticas {


	private float satisfaccion_total = 0;
	private float monedas_total = 0;
	
	

	public Estadisticas_totales(float satisfaccion, float monedas, float satisfaccion_total, float monedas_total) {
		super(satisfaccion, monedas);
		this.satisfaccion_total = satisfaccion_total;
		this.monedas_total = monedas_total;
	}

	public float getSatisfaccion_total() {
		return satisfaccion_total;
	}

	public void setSatisfaccion_total(float satisfaccion_total) {
		this.satisfaccion_total = satisfaccion_total;
	}

	public float getMonedas_total() {
		return monedas_total;
	}

	public void setMonedas_total(float monedas_total) {
		this.monedas_total = monedas_total;
	}
	
	


}

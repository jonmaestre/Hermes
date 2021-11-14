package hermesServices;

public class Estadisticas_totales extends Estadisticas {
	private float satisfaccion_total = 0;
	private float monedas_tota = 0;
	
	public Estadisticas_totales(float satisfaccion_total, float monedas_tota) {
		super();
		this.satisfaccion_total = satisfaccion_total;
		this.monedas_tota = monedas_tota;
	}

	public float getSatisfaccion_total() {
		return satisfaccion_total;
	}

	public void setSatisfaccion_total(float satisfaccion_total) {
		this.satisfaccion_total = satisfaccion_total;
	}

	public float getMonedas_tota() {
		return monedas_tota;
	}

	public void setMonedas_tota(float monedas_tota) {
		this.monedas_tota = monedas_tota;
	}
	
	
	
}

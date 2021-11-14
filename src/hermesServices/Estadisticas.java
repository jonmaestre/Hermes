package hermesServices;

public class Estadisticas extends Estadisticas_totales  {
	
// Esta es para mostrar estadisticas totales y tambien los objetos guardados en cada cuenta
	private float Satisfacion = 0; // la satisfaciion media de todos los dias
	private float Monedas = 0 ; // las monedas maximas ganadas hasta ahora como en el clasroyale cuando te muestra trofeos max
	
	public Estadisticas(float satisfaccion_total, float monedas_tota, float satisfacion, float monedas) {
		super(satisfaccion_total, monedas_tota);
		Satisfacion = satisfacion;
		Monedas = monedas;
	}

	public float getSatisfacion() {
		return Satisfacion;
	}

	public void setSatisfacion(float satisfacion) {
		Satisfacion = satisfacion;
	}

	public float getMonedas() {
		return Monedas;
	}

	public void setMonedas(float monedas) {
		Monedas = monedas;
	}
	
	
	
}

package hermesServices;

public class Estadisticas   {
	
// Esta es para mostrar estadisticas totales y tambien los objetos guardados en cada cuenta
	private float satisfaccion = 0; // la satisfaciion media de todos los dias
	private float monedas = 0 ; // las monedas maximas ganadas hasta ahora como en el clasroyale cuando te muestra trofeos max
	
	public Estadisticas(float satisfa, float moneda) {
		
		this.satisfaccion = satisfa;
		this.monedas = moneda;
	}

	public float getsatisfacion() {
		return satisfaccion;
	}

	public void setsatisfacion(float satisfa) {
		satisfaccion = satisfa;
	}

	public float getMonedas() {
		return monedas;
	}

	public void setMonedas(float moneda) {
		monedas = moneda;
	}
	
	
	
}

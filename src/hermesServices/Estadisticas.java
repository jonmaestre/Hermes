package hermesServices;

public class Estadisticas   {
	
// Esta es para mostrar estadisticas totales y tambien los objetos guardados en cada cuenta
	private float satisfaccion; // la satisfaciion media de todos los dias
	private float monedas; // las monedas maximas ganadas hasta ahora como en el clasroyale cuando te muestra trofeos max
	
	public Estadisticas(float satisfaccion, float monedas) {
		super();
		this.satisfaccion = satisfaccion;
		this.monedas = monedas;
	}

	public float getSatisfaccion() {
		return satisfaccion;
	}

	public void setSatisfaccion(float satisfaccion) {
		this.satisfaccion = satisfaccion;
	}

	public float getMonedas() {
		return monedas;
	}

	public void setMonedas(float monedas) {
		this.monedas = monedas;
	}
	
	
}
package hermesServices;

public class Satisfaccion extends Estadisticas_totales {
	private String nombre_satisfaccion = ""; // estilo muy satisfactorio,satisfactorio,normal, mal, muy mal igual hay que ponerlo en un enum
	private float catidadSatisfactoria = 100 ; // he pensado en empezar con 100 % y que cuando no cumpla un requisito baje
	
	public Satisfaccion(float satisfaccion_total, float monedas_tota, String nombre_satisfaccion,
			float catidadSatisfactoria) {
		super(satisfaccion_total, monedas_tota);
		this.nombre_satisfaccion = nombre_satisfaccion;
		this.catidadSatisfactoria = catidadSatisfactoria;
	}

	public String getNombre_satisfaccion() {
		return nombre_satisfaccion;
	}

	public void setNombre_satisfaccion(String nombre_satisfaccion) {
		this.nombre_satisfaccion = nombre_satisfaccion;
	}

	public float getCatidadSatisfactoria() {
		return catidadSatisfactoria;
	}

	public void setCatidadSatisfactoria(float catidadSatisfactoria) {
		this.catidadSatisfactoria = catidadSatisfactoria;
	}
	
	

}

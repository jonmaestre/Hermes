package hermesServices;


public class Satisfaccion extends Estadisticas_totales {
		private String nombre_satisfaccion = ""; // estilo muy satisfactorio,satisfactorio,normal, mal, muy mal igual hay que ponerlo en un enum
		private float cantidadSatisfactoria = 100 ; // he pensado en empezar con 100 % y que cuando no cumpla un requisito baje
		

		public Satisfaccion(float satisfaccion, float monedas, float satisfaccion_total, float monedas_total,
				String nombre_satisfaccion, float cantidadSatisfactoria) {
			super(satisfaccion, monedas, satisfaccion_total, monedas_total);
			this.nombre_satisfaccion = nombre_satisfaccion;
			this.cantidadSatisfactoria = cantidadSatisfactoria;
		}

		public String getNombre_satisfaccion() {
			return nombre_satisfaccion;
		}

		public void setNombre_satisfaccion(String nombre_satisfaccion) {
			this.nombre_satisfaccion = nombre_satisfaccion;
		}

		public float getCantidadSatisfactoria() {
			return cantidadSatisfactoria;
		}

		public void setCantidadSatisfactoria(float cantidadSatisfactoria) {
			this.cantidadSatisfactoria = cantidadSatisfactoria;
		}

}

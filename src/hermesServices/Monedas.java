package hermesServices;


public class Monedas extends Estadisticas_totales {
		private float cantidadObtenida = 0; // LA catidad QUe tienes
		private float catidadRequerida = 0; // para realizar compras 
		private String nombre_moneda = "Kromer" ;
		
		
		

		public Monedas(float satisfaccion, float monedas, float satisfaccion_total, float monedas_total,
				float cantidadObtenida, float catidadRequerida, String nombre_moneda) {
			super(satisfaccion, monedas, satisfaccion_total, monedas_total);
			this.cantidadObtenida = cantidadObtenida;
			this.catidadRequerida = catidadRequerida;
			this.nombre_moneda = nombre_moneda;
		}

		public float getCantidadObtenida() {
			return cantidadObtenida;
		}

		public void setCantidadObtenida(float cantidadObtenida) {
			this.cantidadObtenida = cantidadObtenida;
		}

		public float getCatidadRequerida() {
			return catidadRequerida;
		}

		public void setCatidadRequerida(float catidadRequerida) {
			this.catidadRequerida = catidadRequerida;
		}

		public String getNombre_moneda() {
			return nombre_moneda;
		}


}

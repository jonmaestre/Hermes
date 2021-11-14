package hermesServices;



	public class Monedas extends Estadisticas_totales {
		private float cantidadObtenida = 0; // LA catidad QUe tienes
		private float catidadRequerida = 0; // para realizar compras 
		private String nombre_moneda = "Kromer" ;
		

	public Monedas(float satisfa, float moneda) {
		super(satisfa, moneda);
		// TODO Auto-generated constructor stub
	}


		public Monedas(float satisfaccion_total, float monedas_tota, float cantidadObtenida, float catidadRequerida,
				String nombre_moneda) {
			super(satisfaccion_total, monedas_tota);
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

		public void setNombre_moneda(String nombre_moneda) {
			this.nombre_moneda = nombre_moneda;
		}
}

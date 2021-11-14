package datos.Hermes;

public class Tiendas extends Mapas {
	private String tipo_tienda = ""; // que vende la tienda
	private String nombre_tienda = ""; 
	//private float localizacionlat_tienda = 0;
	//private float localizacionlon_tienda = 0;
	
	public Tiendas(String tipo, String nombre, String region, float localizacionlat, float localizacionlon,
			String tipo_tienda, String nombre_tienda) {
		super(tipo, nombre, region, localizacionlat, localizacionlon);
		this.tipo_tienda = tipo_tienda;
		this.nombre_tienda = nombre_tienda;
	}

	public String getTipo_tienda() {
		return tipo_tienda;
	}

	public void setTipo_tienda(String tipo_tienda) {
		this.tipo_tienda = tipo_tienda;
	}

	public String getNombre_tienda() {
		return nombre_tienda;
	}

	public void setNombre_tienda(String nombre_tienda) {
		this.nombre_tienda = nombre_tienda;
	}
	
	
}

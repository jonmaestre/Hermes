package datos.Hermes;

public class Mapas extends Datos {
	//que alguien haga el cargar y guardar mapas en esta clase tambien
	private String nombre = ""; //nombre del mapa en el que estamos 
	private String region = ""; // localizacion de la parte en la que estamos
	private float localizacionlat = 0; // esto es lo mitico del minecraft para saber en que capa del mundo estas
	private float localizacionlon = 0; // el anterior es latitud y este longitud
	
	public Mapas(String tipo, String nombre, String region, float localizacionlat, float localizacionlon) {
		super(tipo);
		this.nombre = nombre;
		this.region = region;
		this.localizacionlat = localizacionlat;
		this.localizacionlon = localizacionlon;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public float getLocalizacionlat() {
		return localizacionlat;
	}

	public void setLocalizacionlat(float localizacionlat) {
		this.localizacionlat = localizacionlat;
	}

	public float getLocalizacionlon() {
		return localizacionlon;
	}

	public void setLocalizacionlon(float localizacionlon) {
		this.localizacionlon = localizacionlon;
	}
	
}

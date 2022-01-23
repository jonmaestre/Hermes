package datos.Hermes;

public class Cliente {
	protected String nombre;
	protected tipoMueble tipoMueble;
	protected tematica tematica;
	protected color color;
	protected material material;
	protected String descripcion;
	
	public Cliente(String nombre,datos.Hermes.tipoMueble tipoMueble, datos.Hermes.tematica tematica, datos.Hermes.color color,
			datos.Hermes.material material, String descripcion) {
		super();
		this.nombre=nombre;
		this.tipoMueble = tipoMueble;
		this.tematica = tematica;
		this.color = color;
		this.material = material;
		this.descripcion=descripcion;
	}
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public tipoMueble getTipoMueble() {
		return tipoMueble;
	}
	public void setTipoMueble(tipoMueble tipoMueble) {
		this.tipoMueble = tipoMueble;
	}
	public tematica getTematica() {
		return tematica;
	}
	public void setTematica(tematica tematica) {
		this.tematica = tematica;
	}
	public color getColor() {
		return color;
	}
	public void setColor(color color) {
		this.color = color;
	}
	public material getMaterial() {
		return material;
	}
	public void setMaterial(material material) {
		this.material = material;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}

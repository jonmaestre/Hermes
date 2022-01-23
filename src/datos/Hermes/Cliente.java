package datos.Hermes;

public class Cliente {
	protected tipoMueble tipoMueble;
	protected tematica tematica;
	protected color color;
	protected material material;
	public Cliente(datos.Hermes.tipoMueble tipoMueble, datos.Hermes.tematica tematica, datos.Hermes.color color,
			datos.Hermes.material material) {
		super();
		this.tipoMueble = tipoMueble;
		this.tematica = tematica;
		this.color = color;
		this.material = material;
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
	
	
}

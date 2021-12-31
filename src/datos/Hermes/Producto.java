package datos.Hermes;

public class Producto extends Datos {

	protected String codigoObjeto;
	protected tipoMueble tipoMueble;
	protected tematica tematica;
	protected color color;
	protected material material;
	protected float precioVenta;
	protected float precioCompra;
	
	public Producto(String codigoObjeto, datos.Hermes.tipoMueble tipoMueble, datos.Hermes.tematica tematica,
			datos.Hermes.color color, datos.Hermes.material material, float precioVenta, float precioCompra) {
		super();
		this.codigoObjeto = codigoObjeto;
		this.tipoMueble = tipoMueble;
		this.tematica = tematica;
		this.color = color;
		this.material = material;
		this.precioVenta = precioVenta;
		this.precioCompra = precioCompra;
	}

	public String getCodigoObjeto() {
		return codigoObjeto;
	}

	public void setCodigoObjeto(String codigoObjeto) {
		this.codigoObjeto = codigoObjeto;
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

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}
	
	
	
	


	

	
}
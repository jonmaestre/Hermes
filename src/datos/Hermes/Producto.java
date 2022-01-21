package datos.Hermes;

public class Producto extends Datos {

	protected int codigoObjeto;
	protected tipoMueble tipoMueble;
	protected tematica tematica;
	protected color color;
	protected material material;
	protected float precioVenta;
	protected float precioCompra;
	protected int diaCompra;
	protected int tienda; //Para saber de que tienda proviene el producto correspondiente.
	protected int codU;

	

	public int getCodigoObjeto() {
		return codigoObjeto;
	}

	public void setCodigoObjeto(int codigoObjeto) {
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
	
	public float getCodU() {
		return diaCompra;
	}

	public void setCodU(int codU) {
		this.codU = codU;
	}

	public int getTienda() {
		return tienda;
	}

	public void setTienda(int tienda) {
		this.tienda = tienda;
	}
	
	public float getDiaCompra() {
		return diaCompra;
	}

	public void setDiaCompra(int diaCompra) {
		this.diaCompra = diaCompra;
	}
	
	
	public Producto(int codigoObjeto, tipoMueble tipoMueble, tematica tematica,
			color color, material material, float precioVenta, float precioCompra, int diaCompra,
			int tienda, int codU) {
		super();
		this.codigoObjeto = codigoObjeto;
		this.tipoMueble = tipoMueble;
		this.tematica = tematica;
		this.color = color;
		this.material = material;
		this.precioVenta = precioVenta;
		this.precioCompra = precioCompra;
		this.diaCompra = diaCompra;
		this.tienda = tienda;
		this.codU = codU;
	}
	
}
package datos.Hermes;

public class Producto{

	protected int codigoObjeto;
	protected tipoMueble tipoMueble;
	protected tematica tematica;
	protected color color;
	protected material material;
	protected double precioVenta;
	protected double precioCompra;
	protected int diaCompra;
	protected String tienda; //Para saber de que tienda proviene el producto correspondiente.
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

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	
	public float getCodU() {
		return diaCompra;
	}

	public void setCodU(int codU) {
		this.codU = codU;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}
	
	public float getDiaCompra() {
		return diaCompra;
	}

	public void setDiaCompra(int diaCompra) {
		this.diaCompra = diaCompra;
	}
	
	
	public Producto(int codigoObjeto, tipoMueble tipoMueble, tematica tematica,
			color color, material material, double precioVenta, double precioCompra, int diaCompra,
			String tienda, int codU) {
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
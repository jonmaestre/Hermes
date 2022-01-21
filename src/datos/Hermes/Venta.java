package datos.Hermes;

public class Venta extends Datos {
	
	protected String codigoVenta;
	protected tipoMueble tipoMueble;
	protected tematica tematica;
	protected color color;
	protected material material;
	protected float precioVenta;
	protected float precioCompra;
	protected int diaCompra;
	protected int diaVenta;
	protected int tienda; //Para saber de que tienda proviene el producto correspondiente.
	protected int codU;
	
	
	public String getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
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
	public int getDiaCompra() {
		return diaCompra;
	}
	public void setDiaCompra(int diaCompra) {
		this.diaCompra = diaCompra;
	}
	public int getDiaVenta() {
		return diaVenta;
	}
	public void setDiaVenta(int diaVenta) {
		this.diaVenta = diaVenta;
	}
	public int getTienda() {
		return tienda;
	}
	public void setTienda(int tienda) {
		this.tienda = tienda;
	}
	public int getCodU() {
		return codU;
	}
	public void setCodU(int codU) {
		this.codU = codU;
	}
	
	public Venta(String codigoVenta, datos.Hermes.tipoMueble tipoMueble, datos.Hermes.tematica tematica,
			datos.Hermes.color color, datos.Hermes.material material, float precioVenta, float precioCompra,
			int diaCompra, int diaVenta, int tienda, int codU) {
		super();
		this.codigoVenta = codigoVenta;
		this.tipoMueble = tipoMueble;
		this.tematica = tematica;
		this.color = color;
		this.material = material;
		this.precioVenta = precioVenta;
		this.precioCompra = precioCompra;
		this.diaCompra = diaCompra;
		this.diaVenta = diaVenta;
		this.tienda = tienda;
		this.codU = codU;
	}
	
	
	

}

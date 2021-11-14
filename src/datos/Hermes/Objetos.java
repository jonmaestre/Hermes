package datos.Hermes;

public class Objetos extends Datos {
//<<<<<<< HEAD
	protected tipoMueble tipoMueble;
	protected tematica tematica;
	protected color color;
	protected material material;
	protected float precioVenta;
	protected float precioCompra;
	protected String codigoObjeto;
	
//=======
	private String tipo_objeto = "" ; // mesa, silla, sofa... igual hay que hacerlo en array y es mas comodo miradlo
	private String color_objeto = ""; // igual mejor enum y el de arriba tambien pero ns como conectarlo a la clase
	private String material_objeto = ""; // pasa lo mismo que con los de arriba ^
	

	public Objetos(String tipo, String tipo_objeto, String color_objeto, String material_objeto) {
		super();
		this.tipo_objeto = tipo_objeto;
		this.color_objeto = color_objeto;
		this.material_objeto = material_objeto;
	}

	public String getTipo_objeto() {
		return tipo_objeto;
	}

	public void setTipo_objeto(String tipo_objeto) {
		this.tipo_objeto = tipo_objeto;
	}

	public String getColor_objeto() {
		return color_objeto;
	}

	public void setColor_objeto(String color_objeto) {
		this.color_objeto = color_objeto;
	}

	public String getMaterial_objeto() {
		return material_objeto;
	}

	public void setMaterial_objeto(String material_objeto) {
		this.material_objeto = material_objeto;
	}
//>>>>>>> 5859b96b132a0dcacbd5d9607db9e4b1077a7a20
	
}

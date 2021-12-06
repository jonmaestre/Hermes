package pruebaVentana;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.util.Date;


public class Producto extends Espacio implements Editable {
	
	//Parte static
	private static final long serialVersionUID = 1L; // Versión para la serializaciÃ³n
	
	protected static final Color COLOR_FONDO = new Color( 0, 255, 255, 53 ); // Color cyan traslúcido para fondo
	protected static final Color COLOR_BORDE = Color.MAGENTA; // Color visual del borde de la caja
	protected static final Color COLOR_TEXTO = Color.BLACK;   // Color visual del texto interior de la caja
	protected static final Font TIPO_TEXTO = new Font( "Arial", Font.PLAIN, 12 ); // Tipografía de los textos que se muestran
	
	// Parte no static

	protected TipoProducto tipoProducto;     // Tipo de producto

	public Producto( VentanaAg ventana, Date fechaHora, int duracionMins ) {
		super( ventana, fechaHora, duracionMins );
		setColorFondo( COLOR_FONDO );     // Por defecto
		tipoProducto = TipoProducto.SILLA;  // Por defecto
		setFont( TIPO_TEXTO );
		setForeground( COLOR_TEXTO );
	}
	
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
		setText( toString() );
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Producto)) return false;
		Producto p = (Producto) obj;
		return super.equals(obj) && tipoProducto.equals(p.tipoProducto);
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + tipoProducto;
	}

	@Override
	public void editar() {
		String entrada = JOptionPane.showInputDialog( ventana, "Edita o confirma la descripción del producto:", descripcion );
		if (entrada!=null) {
			setDescripcion( entrada );
		}
		Object entrada2 = JOptionPane.showInputDialog( ventana, "Edita o confirma el tipo de producto:", "Edición", JOptionPane.INFORMATION_MESSAGE, null, TipoProducto.values(), tipoProducto );
		if (entrada2!=null) {
			setTipoProducto( (TipoProducto) entrada2 );
		}
	}
	
}


package pruebaVentana;

import java.awt.Color;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;


public abstract class Espacio extends JLabel implements Movible, Serializable {
	
	//================= Parte static
	private static final long serialVersionUID = 1L; // Versión para la serialización
	
	protected static final Color COLOR_FONDO = new Color( 240, 240, 240 );  // Gris clarito - Color de fondo por defecto
	protected static final Color COLOR_BORDE = Color.MAGENTA; // Color visual de la caja del espacio
	protected static final float GROSOR = 1.0f;  // Grosor visual del borde del espacio
	
	protected static final SimpleDateFormat DMY_HM = new SimpleDateFormat( "dd/MM/yyyy hh:mm" );
	
	
	/** Tipos de espacios que pueden crearse  (corresponden a clases hijas de EspacioAgenda)
	 */
	public static String[] TIPOS_DE_ESPACIOS = { "Producto" };
	
	/** Constructor indirecto de espacio de agenda
	 * @param tipoEspacio	Tipo de espacio. Uno de los valores de {@link #TIPOS_DE_ESPACIOS}
	 * @param ventana	Ventana de agenda que se usa
	 * @param fecha	Fecha de inicio del espacio
	 * @param duracion	Duración en minutos del espacio
	 * @return
	 * @throws NullPointerException	Si el tipo de espacio no es uno de los valores correctos
	 */
	public static Espacio crearNuevoEspacio( String tipoEspacio, VentanaAg ventana, Date fecha, int duracion ) throws NullPointerException {
		Espacio ret = null;
		switch (tipoEspacio) {
			case "Producto":
				ret = new Producto(ventana, fecha, duracion);
				break;
		}
		return ret;
	}
	
	//================= Parte no static

	protected transient VentanaAg ventana;  // Los atributos TRANSIENT no se serializan (no tiene sentido serializar la ventana, porque va a ser distinta en cada ejecución)
	protected Date fechaHora;     // Fecha y hora de inicio del hueco
	protected int duracionMins;   // Duración en minutos del hueco de agenda
	protected Color colorFondo;   // Color de fondo del hueco de agenda
	protected String descripcion; // Descripción del slot

	/** Crea un nuevo slot de agenda sin descripción
	 * @param ventana	Ventana de agenda donde se dibujará este slot
	 * @param fechaHora	Fecha-hora de inicio
	 * @param duracionMins	Duración en minutos del slot
	 */
	public Espacio( VentanaAg ventana, Date fechaHora, int duracionMins ) {
		super( " ", JLabel.CENTER );
		this.ventana = ventana;
		this.fechaHora = fechaHora;
		this.duracionMins = duracionMins;
		setVerticalAlignment( JLabel.CENTER );
		setDescripcion( "" );
		setColorFondo( COLOR_FONDO );  // Por defecto
		setBackground( colorFondo );
		setOpaque( true );
		recalculaPosicion();  // Siempre recalcula posición en función de la fecha y duración (para la visual)
		ventana.anyadirGestorRaton( this );
	}
	
	/** Devuelve la ventana donde está este objeto
	 * @return	ventana de agenda del objeto
	 */
	public VentanaAg getVentana() {
		return ventana;
	}
	
	/** Modifica la ventana donde está este objeto
	 * @param ventana
	 */
	public void setVentana( VentanaAg ventana ) {
		this.ventana = ventana;
	}
	
	/** Modifica la posición de este objeto
	 * @param x	Nueva posición horizontal en píxels
	 */
	public void setX(int x) {
		setLocation( x, getY() );
	}

	/** Modifica la posición de este objeto
	 * @param y	Nueva posición vertical en píxels
	 */
	public void setY(int y) {
		setLocation( getX(), y );
	}
	
	public Date getFechaHora() {
		return fechaHora;
	}
	
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public int getDuracionMins() {
		return duracionMins;
	}

	public void setDuracionMins(int duracionMins) {
		this.duracionMins = duracionMins;
	}

	public Color getColorFondo() {
		return colorFondo;
	}

	public void setColorFondo(Color colorFondo) {
		this.colorFondo = colorFondo;
		setBackground( colorFondo );
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		setText( toString() ); // Cambia el texto visual
	}

	/** Recalcula la posición en la ventana de este slot en función de su fecha y duración
	 */
	public void recalculaPosicion() {
		setLocation( ventana.getXFecha(fechaHora), ventana.getYFecha(fechaHora) );
		setSize( ventana.getAnchoColumna(), ventana.getAlturaPixels( duracionMins ) );
	}
	
	public boolean contienePunto(int x, int y) {
		System.out.println( "  Contiene punto " + this );
		if (x<getX()) return false;
		else if (y<getY()) return false;
		else if (x>getX()+getWidth()) return false;
		else if (y>getY()+getHeight()) return false;
		return true;
	}
	
	/** Devuelve la información de fecha y duración del espacio en minutos
	 * @return	Formato string: dd/MM/yyyy hh:mm [dur]
	 */
	public String getFechaYDuracion() {
		return DMY_HM.format(fechaHora) + " [" + String.format( "%02d", duracionMins ) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Espacio)) return false;
		Espacio e = (Espacio) obj;
		return descripcion.equals(e.descripcion) && fechaHora.equals(e.fechaHora) && duracionMins==e.duracionMins;
	}
	
	@Override
	public String toString() {
		return descripcion;
	}

	public void mover( int x, int y ) {
		setLocation( x, y );
//		setX( x );
//		setY( y );
	}
	
}


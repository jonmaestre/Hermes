package pruebaVentana;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Gestor {

	public static void main(String[] args) {
		Gestor g = new Gestor();
		g.lanza();
	}
	
	// Constantes static
	
	public static final int ANCHURA_VENTANA = 1000;   // Píxels de ancho
	public static final int ALTURA_VENTANA = 800;     // Píxels de alto
	public static final String NOM_FICHERO = "GestorAgenda.dat";  // Fichero de datos de agenda
	
	// =================== Parte no static
	
	VentanaAg ventana;
	String nick;
	ArrayList<Espacio> listaAgenda;
	HashMap<String,ArrayList<Espacio>> mapaAgendas = new HashMap<>();
	

	public Gestor() {
		ventana = new VentanaAg( ANCHURA_VENTANA, ALTURA_VENTANA, this );
		listaAgenda = new ArrayList<>();
	}
	
	/** Devuelve la ventana de la agenda
	 * @return	ventana que corresponde
	 */
	public VentanaAg getVentana() {
		return ventana;
	}

	/** Devuelve la lista de elementos de agenda gestionada
	 * @return	Lista de slots de agenda
	 */
	public ArrayList<Espacio> getListaAgenda() {
		return listaAgenda;
	}

	/** Ejecuta el gestor de agenda, lanzando el proceso de ejecución principal
	 */
	public void lanza() {
		initDatos();
		login();
		if (nick!=null && !nick.isEmpty()) {
			ventana.setFechaInicial( new Date() );
			ventana.setMensajeSuperior( "Pulsa los botones de flecha para mover d�as en la agenda, arrastra para cambiar de fecha, a la papelera para borrar, click para crear" );
			ventana.setVisible( true );
			ventana.addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					guardaDatosFichero( new File( NOM_FICHERO ) );
				}
			});
		}
	}

	/** Proceso de login - pide el nombre de usuario
	 */
	public void login() {
		nick = JOptionPane.showInputDialog( "Bienvenido/a al gestor de la tienda de Hermes Services. �Nombre de usuario?" );
		borrarAgendaActual( false );          // Borra agenda sin pedir confirmacion para cargar o crear la agenda nueva
		if (mapaAgendas.containsKey(nick)) {  // Usuario ya existente - carga su agenda en la ventana
			listaAgenda = mapaAgendas.get(nick);
			reiniciaVentanaDeAgenda();
		} else { // Usuario nuevo - crea una agenda vac�a
			mapaAgendas.put( nick, listaAgenda );
		}
		// Segunda parte
		System.out.println( "Usuarios actualmente en agenda:" );
		for (String usu : mapaAgendas.keySet()) {
			ArrayList<Espacio> l = mapaAgendas.get(usu);
			System.out.println( usu + " - " + l.size() + " espacios: " + l );
		}
	}
	
	// Proceso de inicializar datos de la ventana de trabajo
	private void initDatos() {
		// Tarea 3
		// Se añaden los datos de agenda, o desde fichero o unos pocos por defecto para empezar si no hay ficheros
		File fichero = new File( NOM_FICHERO );
		if (fichero.exists()) { // Si hay guardado de datos previo
			cargaDatosFichero( fichero );
		} else { // Si no crea unos datos de prueba en el día actual y cercanos
			Producto producto = new Producto( ventana, new Date( System.currentTimeMillis() ), 60 );
			anyadirEspacio( producto );
		}
	}
	
	/** Añade un espacio a la agenda
	 * @param espacio	Espacio a añadir
	 */
	public void anyadirEspacio( Espacio espacio ) {
		listaAgenda.add( espacio );
		ventana.getPanel().add( espacio );
		ventana.repaint();
	}
	
	public void borrarAgendaActual( boolean pideConfirmacion ) {
		if (pideConfirmacion) {
			int resp = JOptionPane.showConfirmDialog( ventana, "�Confirmas el borrado de toda esta agenda?" );
			if (resp==JOptionPane.OK_OPTION) {
				pideConfirmacion = false;
			}
		}
		if (!pideConfirmacion) {  // Si se ha confirmado o si no se necesita confirmación de usuario se borra
			listaAgenda = new ArrayList<>();
			ventana.getPanel().removeAll();
			ventana.repaint();
		}
	}
	
	/**	Crea un espacio en la agenda pidiendo los datos interactivamente al usuario
	 * @param fecha	Fecha y hora en la que quiere crearse el espacio
	 */
	public void crearEspacioInteractivo( Date fecha ) {
		if (fecha != null) {  // Se puede crear
			// 1.- Elegir qué espacio se genera
			Object entrada = JOptionPane.showInputDialog( ventana, "Elige espacio a generar:", "Creaci�n", JOptionPane.INFORMATION_MESSAGE, null, Espacio.TIPOS_DE_ESPACIOS, "Producto" );
			if (entrada!=null) {
				// 2.- Definir duraci�n
				String duracion = JOptionPane.showInputDialog( "Introduce duraci�n en minutos: ", 60 );
				try {
					int dur = Integer.parseInt( duracion );
					// 3.- Crear espacio
					Espacio nuevo = Espacio.crearNuevoEspacio( (String)entrada, ventana, fecha, dur );
					// 4.- Pedir resto de datos (personalizados)
					if (nuevo instanceof Editable) {
						((Editable)nuevo).editar();
					}
					anyadirEspacio( nuevo );
				} catch (Exception e) {
					// Error en entrada - no se genera slot
				}
			}
		}
	}
	
	public void clickEnEspacio( Espacio espacio ) {
		if (espacio instanceof Editable) {
			((Editable) espacio).editar();
			ventana.repaint();
		}
	}
	
	public void dragEnEspacio( Movible espacioMovible, int difX, int difY ) {
		espacioMovible.mover( espacioMovible.getX()+difX, espacioMovible.getY()+difY );
	}
	
	public void finDragEnEspacio( Espacio espacio, Point puntoFinal ) {
		Date fechaEnEspacio = ventana.getFechaDePunto( espacio.getX() + espacio.getWidth()/2, espacio.getY() );
		if (fechaEnEspacio == null) { // Está fuera de la pantalla
			Rectangle rectanguloBoton = new Rectangle( ventana.getBotonTrash().getLocationOnScreen(), ventana.getBotonTrash().getSize() );
			if (rectanguloBoton.contains(puntoFinal)) { // Encima de botón trash: el espacio se borra
				listaAgenda.remove( espacio );
				ventana.getPanel().remove( espacio );
				ventana.repaint();
			} else {  // Fuera de ventana en otro lugar: el espacio se recoloca en su lugar inicial
				recolocaEspacioEnSuOrigen( espacio );
			}
		} else {
			espacio.setFechaHora( fechaEnEspacio );
			espacio.recalculaPosicion();
			ventana.repaint();
		}
	}
	
	private void recolocaEspacioEnSuOrigen( Espacio espacio ) {
		// Posible inicializaci�n de variables para los movimimentos a hacer en la animaci�n
		// double x = espacio.getX();  // x actual
		// double y = espacio.getY();  // y actual
		// double xFin = ventana.getXFecha( espacio.getFechaHora() );  // x a la que hay que llegar
		// double yFin = ventana.getYFecha( espacio.getFechaHora() );  // y a la que hay que llegar
		// double pasoX = (xFin - x) / 50;  // incremento de x a mover cincuenta veces
		// double pasoY = (yFin - y) / 50;  // incremento de y a mover cincuenta veces
		(new Thread() {
			public void run() {
				double x = espacio.getX();  // x actual
				double y = espacio.getY();  // y actual
				double xFin = ventana.getXFecha( espacio.getFechaHora() );  // x a la que hay que llegar
				double yFin = ventana.getYFecha( espacio.getFechaHora() );  // y a la que hay que llegar
				double pasoX = (xFin - x) / 50;  // incremento de x a mover cincuenta veces
				double pasoY = (yFin - y) / 50;  // incremento de y a mover cincuenta veces
				for (int i=0; i<50; i++) {
					x += pasoX;
					y += pasoY;
					espacio.setLocation( (int) x, (int) y );
					try {
						Thread.sleep( 20 );
					} catch (InterruptedException e) {}
				}
				espacio.recalculaPosicion();
			}
		}).start();
	}
	
	// Reinicia la ventana y panel visual de la agenda (listaAgenda) actual
	private void reiniciaVentanaDeAgenda() {
		for (Espacio espacio : listaAgenda) {
			espacio.setVentana( ventana );
			ventana.getPanel().add( espacio );
			espacio.recalculaPosicion();
			ventana.anyadirGestorRaton( espacio );
		}
	}
	
	@SuppressWarnings("unchecked")
	private void cargaDatosFichero( File f ) {
		try {
			ObjectInputStream ois = new ObjectInputStream( new FileInputStream( f ) );
			Object leido = ois.readObject();
			if (leido instanceof ArrayList) {  // Fichero antiguo
				listaAgenda = (ArrayList<Espacio>) leido;
			} else {  // Fichero nuevo
				mapaAgendas = (HashMap<String,ArrayList<Espacio>>) leido;
			}
			ois.close();
			reiniciaVentanaDeAgenda();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void guardaDatosFichero( File f ) {
		// Tarea 3
		try {
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( f ) );
			oos.writeObject( mapaAgendas );
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


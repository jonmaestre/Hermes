package Principal;

//import jdk.internal.misc.FileSystemOption;

public class GestorPrincipal {
	private boolean Funcionando = false;
	private String titulo;
	private int anchura;
	private int altura;
	
	private static int fps=0;
	private static int aps=0;
	
	
	public GestorPrincipal( String titulo, int anchura, int altura) {
		super();
		this.titulo = titulo;
		this.anchura = anchura;
		this.altura = altura;

	}
	public static void main(String[] args) {
		
		GestorPrincipal gp = new GestorPrincipal("Juego", Constantes.ANCHO_PANTALLA_COMPLETA , Constantes.ALTO_PANTALLA_COMPLETA);
        Constantes.ANCHO_JUEGO = 600;
        Constantes.ALTO_JUEGO = 500;
		gp.iniciarJuego();
		gp.iniciarBoolePrincipal();
	
	}
	
	private void iniciarJuego() {
		Funcionando=true;
		inicializar();
	}
	private void inicializar() {
		
		
	}
	private void iniciarBoolePrincipal() {
		
		int actualizacionAcumulada=0;
		int frameActualizado=0;
		final int NS_POR_SEGUNDO=60;
		final int APS_OBJETIVO=60;
		
		final double NS_POR_ACTUALIZACION=NS_POR_SEGUNDO / APS_OBJETIVO;
		
		long refecenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();
		double tiempoTranscurrido;
		double delta=0;
	
		while(Funcionando) {
			final long inicioBucle = System.nanoTime();
			
			tiempoTranscurrido = inicioBucle -refecenciaActualizacion;
			refecenciaActualizacion = inicioBucle;
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
			
		while(delta>=1) {
			actualizar();
			actualizacionAcumulada++;
			delta--;
			}
		dibujar();
		frameActualizado++;
		
		if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
			aps = actualizacionAcumulada;
			fps = frameActualizado;
			
			actualizacionAcumulada = 0;
			frameActualizado = 0;
			referenciaContador = System.nanoTime();
			
			
		}
		}
	
	}
	private void dibujar() {
		System.out.println(fps);
		
	}
	private void actualizar() {
		
		
	}
	
	public static int obtenerFPS() {
		return fps;
	}
	public static int obtenerAPS() {
		return aps;
	}
	
}



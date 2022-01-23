package hermesServices;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import datos.Hermes.Jugador;
import datos.Hermes.Producto;

public class ventanaStat {
	
	private static final long serialVersionUID = 1L;
	private Jugador jugador;
	private List<Producto> todoProd;
	private BDynamic bd;
	
	public ventanaStat(int ancho, int altura) {
		
		JFrame  v= new JFrame("Hermes: Estadisticas");	
		
		v.setSize(ancho, altura);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);		

		//usu cargar usuarios de la bdd en la lista
		bd = new BDynamic();
		try {
			bd.abrirBD();
			//todosJugadores = bd.getUsuarios();
			jugador = bd.selectUsuario();
			todoProd=bd.selectProducto();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

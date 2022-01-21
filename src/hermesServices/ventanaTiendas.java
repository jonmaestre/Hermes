package hermesServices;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import hermesServices.*;
import datos.Hermes.*;

public class ventanaTiendas {
	
	private static final long serialVersionUID = 1L;
	private JTable tablaVenta;
	private List<Producto> todoProd;
	private JButton btnComprar= new JButton("Comprar producto");
	private BDynamic bd;
	
	public ventanaTiendas(int ancho, int altura) {
		
		JFrame  v= new JFrame("Hermes: Tienda");	
		
		v.setSize(ancho, altura);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		
		v.add(btnComprar,BorderLayout.SOUTH);
		

		//usu cargar usuarios de la bdd en la lista
		bd = new BDynamic();
		
		tablaVenta = new JTable();
		v.add(new JScrollPane(tablaVenta), BorderLayout.CENTER);
		
		try {
			bd.abrirBD();
			//todosJugadores = bd.getUsuarios();
		} catch (Exception e) {}
		
		actualizarTabla(todoProd);
		
	}
}

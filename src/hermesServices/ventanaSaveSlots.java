package hermesServices;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import datos.Hermes.Jugador;
import hermesServices.*;
import datos.Hermes.*;

public class ventanaSaveSlots {
	
	private static final long serialVersionUID = 1L;
	private JPanel panelNorth= new JPanel();
	private JPanel panelSouth= new JPanel();
	
	private JButton btnNew= new JButton("Crear Partida");
	private JButton btnLoad= new JButton("Cargar Partida");
	private JButton btnBorrar = new JButton("Borrar Partida");
	private JList listSlot;
	protected DefaultListModel datosLista;
	protected ArrayList<Jugador> usu;
	

	public ventanaSaveSlots(int ancho, int altura, String titulo) {
		
		JFrame  v= new JFrame(titulo);
		panelNorth.setLayout(new GridLayout(1,2));
		panelSouth.setLayout(new GridLayout(1,2));
		
		
		v.setSize(ancho, altura);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		v.add(panelNorth,BorderLayout.NORTH);
		v.add(panelSouth,BorderLayout.SOUTH);
		
		
		
		panelSouth.add(btnNew);
		panelSouth.add(btnLoad);
		panelSouth.add(btnBorrar);
		panelNorth.add(listSlot);

		
		//usu cargar usuarios de la bdd en la lista
		
		datosLista = new DefaultListModel();
		JScrollPane scrollLista = new JScrollPane(listSlot);
		scrollLista.setPreferredSize(new Dimension(400, 300));
		
		
		btnNew.addMouseListener(new MouseAdapter()	{	
			@Override
			public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					//crear nuevo usuario
					System.out.println("Nueva");
			}

		});
		
		
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//cargar usuario elegido en el último punto
				System.out.println("Carga");
			}
		});
		
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//borrar usuario elegido (double check por si acaso)
				System.out.println("Borrar");
			}
		});
		
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					v.dispose();
					ventanaInicial v= new ventanaInicial(1900,800,"Hermes");
				}
			}

		});

	
	
	}
	

	
}

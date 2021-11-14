package hermesServices;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import datos.Hermes.Jugador;
import hermesServices.*;
import datos.Hermes.*;

public class ventanaSaveSlots {
	
	private static final long serialVersionUID = 1L;
	private JPanel panelNorth= new JPanel();
	private JPanel panelCenter= new JPanel();
	private JPanel panelSouth= new JPanel();
	private JPanel panelNC= new JPanel();
	private JPanel panelNE= new JPanel();
	private JPanel panelNO= new JPanel();
	private JPanel panelCC= new JPanel();
	private JPanel panelCE= new JPanel();
	private JPanel panelCO= new JPanel();
	private JPanel panelSC= new JPanel();
	private JPanel panelSE= new JPanel();
	private JPanel panelSO= new JPanel();
	
	private JButton btnNew= new JButton("Crear Partida");
	private JButton btnLoad= new JButton("Cargar Partida");
	private JButton btnBorrar = new JButton("Borrar Partida");
	private JList listSlot;
	protected DefaultListModel datosLista;
	

	public ventanaSaveSlots(int ancho, int altura, String titulo) {
		JFrame  v= new JFrame(titulo);
		panelNorth.setLayout(new GridLayout(1,2));
		panelCenter.setLayout(new GridLayout(1,2));
		panelSouth.setLayout(new GridLayout(1,2));
		panelNC.setLayout(new GridLayout(1,2));
		panelNE.setLayout(new GridLayout(1,2));
		panelNO.setLayout(new GridLayout(1,2));
		panelCC.setLayout(new GridLayout(1,2));
		panelCE.setLayout(new GridLayout(1,2));
		panelCO.setLayout(new GridLayout(1,2));
		panelSC.setLayout(new GridLayout(1,2));
		panelSE.setLayout(new GridLayout(1,2));
		
		
		v.setSize(ancho, altura);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		v.add(panelNorth,BorderLayout.NORTH);
		v.add(panelCenter,BorderLayout.CENTER);
		v.add(panelSouth,BorderLayout.SOUTH);
		panelNorth.add(panelNC,BorderLayout.CENTER);
		panelNorth.add(panelNE,BorderLayout.EAST);
		panelNorth.add(panelNO,BorderLayout.WEST);
		panelCenter.add(panelCC,BorderLayout.CENTER);
		panelCenter.add(panelCE,BorderLayout.EAST);
		panelCenter.add(panelCO,BorderLayout.WEST);
		panelSouth.add(panelSC,BorderLayout.CENTER);
		panelSouth.add(panelSE,BorderLayout.EAST);
		panelSouth.add(panelSO,BorderLayout.WEST);
		
		
		
		panelSE.add(btnNew);
		panelSC.add(btnLoad);
		panelSO.add(btnBorrar);
		panelCC.add(listSlot);

		
		datosLista = new DefaultListModel();
		listSlot = new JList(datosLista);
		JScrollPane scrollLista = new JScrollPane(listSlot);
		scrollLista.setPreferredSize(new Dimension(400, 300));
		
		/*
		listSlot = new JList();
		Juego.cargarDatosJugadores();
		
		for (Map.Entry me : Juego.clasJugador.entrySet()) {
	          System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
	        }
		for (clasJugador.getIdJugador() j : Juego.clasJugador) {
			listSlot.add(j);
		}
		*/
		
		btnNew.addMouseListener(new MouseAdapter()	{	
			@Override
			public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
		
					System.out.println("Nueva");
			}

		});
		
		
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//juego.cargar();
				System.out.println("Carga");
			}
		});
		
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//juego.cargar();
				System.out.println("Carga");
			}
		});
		
	}
	

	
}

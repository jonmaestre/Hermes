package hermesServices;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import datos.Hermes.Jugador;
import examen.ord202201.LaunchesTableModel;
import examen.ord202201.RocketLaunch;
import hermesServices.*;
import datos.Hermes.*;

public class ventanaSaveSlots {
	
	private static final long serialVersionUID = 1L;
	private JPanel panelSouth= new JPanel();
	private JTable jotaTabla;
	private List<Jugador> todosJugadores;
	private JButton btnNew= new JButton("Crear Partida");
	private JButton btnLoad= new JButton("Cargar Partida");
	private JButton btnBorrar = new JButton("Borrar Partida");
	private BaseDatos bd;
	protected ArrayList<Jugador> usu;
	

	public ventanaSaveSlots(int ancho, int altura) {
		
		JFrame  v= new JFrame("Hermes: Partidas");

		panelSouth.setLayout(new GridLayout(1,2));
		
		
		v.setSize(ancho, altura);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		
		v.add(panelSouth,BorderLayout.SOUTH);
		panelSouth.add(btnNew);
		panelSouth.add(btnLoad);
		panelSouth.add(btnBorrar);

		
		//usu cargar usuarios de la bdd en la lista
		bd = new BaseDatos();
		
		jotaTabla = new JTable();
		v.add(new JScrollPane(jotaTabla), BorderLayout.CENTER);
		
		try {
			bd.abrirConexion("???",false);
			//todosJugadores = bd.getUsuarios();
		} catch (Exception e) {}
		
		actualizarTabla(todosJugadores);
		
		
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
		
		//Evento volver a la ventana inicio
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					v.dispose();
					ventanaI v= new ventanaI(1900,800);
				}
			}

		});

	
	
	}
	
	private void actualizarTabla(List<Jugador> jugadores) {
		jotaTabla.setModel(new LaunchTab(jugadores));
		//infoLabel.setText(String.format("%d launches", launches.size()));
	}
	
	private void nuevoUsuario() {
		
		
		//infoLabel.setText(String.format("%d launches", launches.size()));
	}
	
	public class LaunchTab  extends AbstractTableModel {
		
		
		private static final long serialVersionUID = 1L;
		private final List<String> headers = Arrays.asList( "Nombre", "Dia", "Exp", "Kromer");
		private List<Jugador> jugadores;
		
		public LaunchTab(List<Jugador> jugadores1) {
			this.jugadores = jugadores1;
		}
		
		@Override
		public String getColumnName(int column) {
			return headers.get(column);
		}

		@Override
		public int getRowCount() {
			return jugadores.size();
		}

		@Override
		public int getColumnCount() {
			return headers.size(); 
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Jugador j = jugadores.get(rowIndex);
			switch (columnIndex) {
				case 0: return j.getNombre();
				case 1: return j.getDia();
				case 2: return j.getExp();
				case 3: return j.getCartera();
				default: return null;
			}
		}

	}
	
}

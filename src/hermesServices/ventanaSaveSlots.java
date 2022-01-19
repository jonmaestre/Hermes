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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import datos.Hermes.Jugador;
import examen.ord202201.LaunchesTableModel;
import examen.ord202201.RocketLaunch;
import hermesServices.*;
import datos.Hermes.*;


//NECESITO METODO PARA CREAR BORRAR Y LLAMAR A USUARIOS



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
					nuevoUsuario(v,todosJugadores);
					System.out.println("Nuevo");
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
				int row = jotaTabla.getSelectedRow();
				Jugador j= new Jugador((Integer) jotaTabla.getValueAt(row, 0),jotaTabla.getValueAt(row, 1).toString(), (Integer) jotaTabla.getValueAt(row, 2), (Integer) jotaTabla.getValueAt(row, 3), (Integer) jotaTabla.getValueAt(row, 4));
				borrarUsuario(v,j,todosJugadores);
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
	
	private void nuevoUsuario(JFrame  frame,List<Jugador> jugadores) {
		String s = "";
		while (s == "") {
			s = (String)JOptionPane.showInputDialog(frame,"Introduce el nombre de tu jugador\nQue no esté vacío, y no sea un nombre ya escogido","Nuevo jugador",JOptionPane.PLAIN_MESSAGE,null,null,"Nombre");
			for (Jugador j : jugadores) {
				if (s== j.getNombre()) {
					s="";
					JOptionPane.showMessageDialog(frame,"Ha ocurrido un error al introducir tu usuario \nPrueba otra vez","Error",JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
		int cont=0;
		for (Jugador j : jugadores) {
			if (cont==j.getIdJugador()) {
				cont++;
			}
		}
		
		Jugador n =new Jugador(cont, s, 1, 0, 0);
		jugadores.add(n);
		//METODO PARA METER USUARIO EN LA BD
		actualizarTabla(jugadores);
		JOptionPane.showMessageDialog(frame,"La operación se ha realizado exitosamente");
	}
	
	private void borrarUsuario(JFrame  frame, Jugador jug, List<Jugador> jugadores) {
		Object[] options = {"Sí","No"};
		int n = JOptionPane.showOptionDialog(frame,"La acción que vas a realizar implica eliminar datos que no se pueden recuperar. \n¿Seguro que quieres borrar el usuario "+jug.getNombre()+"?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		
		if (n == 0) {
			//jugadores.remove(jug);
			//METODO PARA ELIMINAR USUARIO DE LA BD
			//actualizarTabla(jugadores);
		}
		
		
	}
	
	
	public class LaunchTab  extends AbstractTableModel {
		
		
		private static final long serialVersionUID = 1L;
		private final List<String> headers = Arrays.asList("Codigo", "Nombre", "Dia", "Exp", "Kromer");
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
				case 0: return j.getIdJugador();
				case 1: return j.getNombre();
				case 2: return j.getDia();
				case 3: return j.getExp();
				case 4: return j.getCartera();
				default: return null;
			}
		}

	}
	
}

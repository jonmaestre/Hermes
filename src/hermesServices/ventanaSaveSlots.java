package hermesServices;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
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

import juego.Constantes;
import juego.Juego;
import datos.Hermes.*;


//NECESITO METODO PARA CREAR BORRAR Y LLAMAR A USUARIOS



public class ventanaSaveSlots extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JFrame v= new JFrame("Hermes: Partidas");
	private JPanel panelSouth= new JPanel();
	private JTable jotaTabla;
	private List<Jugador> todosJugadores;
	private JButton btnNew= new JButton("Crear Partida");
	private JButton btnLoad= new JButton("Cargar Partida");
	private JButton btnBorrar = new JButton("Borrar Partida");
	private BDStatic bds;
	private BDynamic bdd;
	protected ArrayList<Jugador> usu;
	

	public ventanaSaveSlots() {

		panelSouth.setLayout(new GridLayout(1,2));
		
		
		v.setSize(1900, 800);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		
		v.add(panelSouth,BorderLayout.SOUTH);
		panelSouth.add(btnNew);
		panelSouth.add(btnLoad);
		panelSouth.add(btnBorrar);

		
		//usu cargar usuarios de la bdd en la lista
		
		jotaTabla = new JTable();
		v.add(new JScrollPane(jotaTabla), BorderLayout.CENTER);
		bds = new BDStatic();
		try {
			bds.abrirConexion();
			todosJugadores = bds.seleccionarUsuario();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actualizarTabla(todosJugadores);
		
		jotaTabla.getColumnModel().getColumn(0).setMinWidth(100);
		jotaTabla.getColumnModel().getColumn(1).setMinWidth(200);
		jotaTabla.getColumnModel().getColumn(2).setMinWidth(100);
		jotaTabla.getColumnModel().getColumn(3).setMinWidth(100);
		jotaTabla.getColumnModel().getColumn(4).setMinWidth(100);
		
		btnNew.addMouseListener(new MouseAdapter()	{	
			@Override
			public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					try {
						nuevoUsuario(v,todosJugadores);
						todosJugadores = bds.seleccionarUsuario();
						actualizarTabla(todosJugadores);
						System.out.println("Nuevo");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
			}
		});
		
		
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//cargar usuario elegido en el último punto
				int row = jotaTabla.getSelectedRow();
				Jugador j= new Jugador((Integer) jotaTabla.getValueAt(row, 0),jotaTabla.getValueAt(row, 1).toString(), (Integer) jotaTabla.getValueAt(row, 2), (Integer) jotaTabla.getValueAt(row, 3), (Integer) jotaTabla.getValueAt(row, 4));
				cargarUsuario(v, j);
				try {
					bds.cerrarConexion();
					bdd.cerrarConexion();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				//Para OpenGL en Mac/Linux
				//System.setProperty("sun.java2d.opengl", "True");
				
				
				  //Para Directx en Windows
				  System.setProperty("sun.java2d.d3d", "True");
				  System.setProperty("sun.java2d.ddforcevram", "True");
				 
				
				//System.setProperty("sun.java2d.transaccel", "True");
				
				Juego gp = new Juego("JUEGO", Constantes.ANCHO_PANTALLA_COMPLETA,
						Constantes.ALTO_PANTALLA_COMPLETA);

				gp.iniciarJuego();
				gp.iniciarBuclePrincipal();
				v.dispose();
				System.out.println("Carga");
			}
		});
		
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
				int row = jotaTabla.getSelectedRow();
				Jugador j= new Jugador((Integer) jotaTabla.getValueAt(row, 0),jotaTabla.getValueAt(row, 1).toString(), (Integer) jotaTabla.getValueAt(row, 2), (Integer) jotaTabla.getValueAt(row, 3), (Double) jotaTabla.getValueAt(row, 4));
				borrarUsuario(v,j);
				todosJugadores = bds.seleccionarUsuario();
				actualizarTabla(todosJugadores);
				System.out.println("Borrar");
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//Evento volver a la ventana inicio
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					ventanaI vi= new ventanaI();
					vi.setVisible(true);
					v.setVisible(false);
					
					
					
				}
			}
		});
	}
	
	private void actualizarTabla(List<Jugador> jugadores) {
		jotaTabla.setModel(new LaunchTab(jugadores));
		v.setVisible(false);
		v.setVisible(true);
	}
	
	private void nuevoUsuario(JFrame  frame,List<Jugador> jugadores) throws SQLException {
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
		
		Jugador n =new Jugador(cont, s, 1, 0, 500);
		bds.insertarUsuario(n);
		JOptionPane.showMessageDialog(frame,"La operación se ha realizado exitosamente");
	}
	
	private void borrarUsuario(JFrame  frame, Jugador jug) throws SQLException {
		Object[] options = {"Sí","No"};
		int n = JOptionPane.showOptionDialog(frame,"La acción que vas a realizar implica eliminar datos que no se pueden recuperar. \n¿Seguro que quieres borrar el usuario "+jug.getNombre()+"?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		
		if (n == 0) {
			bds.borrarUsuario(jug);
			JOptionPane.showMessageDialog(frame,"La operación se ha realizado exitosamente");
		}
	}
	
	private void cargarUsuario(JFrame  frame,Jugador jug) {
		Object[] options = {"Sí","No"};
		int n = JOptionPane.showOptionDialog(frame,"¿Seguro que quieres cargar la partida de el usuario "+jug.getNombre()+"?","Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		
		if (n == 0) {
			bdd = new BDynamic();
			try {
				bdd.abrirBD();
				bdd.reiniciarBD();
				bdd.inicializarBD(jug);
				//FALTA DIRECCIONAR A LA NUEVA LOCALIZACIÓN
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

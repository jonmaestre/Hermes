package hermesServices;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import hermesServices.*;
import datos.Hermes.*;

public class ventanaTiendas {
	
	private static final long serialVersionUID = 1L;
	private JTable tablaVenta;
	private Jugador jugador;
	private List<Producto> almacenProd;
	private List<Producto> todoProd;
	private List<Producto> display;
	private JButton btnComprar= new JButton("Comprar producto");
	private BDynamic bd;
	
	public ventanaTiendas(int ancho, int altura, String nombre) {
		
		JFrame  v= new JFrame("Hermes: Tienda "+nombre);	
		
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
			jugador = bd.selectUsuario();
			almacenProd=bd.selectProducto();
			todoProd=almacenProd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		display=displayTienda(todoProd,jugador,nombre);
		actualizarTienda(display);
		
		btnComprar.addMouseListener(new MouseAdapter()	{	
			@Override
			public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				try {
				int row = tablaVenta.getSelectedRow();
				Producto p= new Producto((Integer) tablaVenta.getValueAt(row, 0), tipoMueble.valueOf(tablaVenta.getValueAt(row, 1).toString()), tematica.valueOf(tablaVenta.getValueAt(row, 2).toString()), color.valueOf(tablaVenta.getValueAt(row, 3).toString()), material.valueOf(tablaVenta.getValueAt(row, 4).toString()), (Integer) tablaVenta.getValueAt(row, 5), (Integer) tablaVenta.getValueAt(row, 6), (Integer) tablaVenta.getValueAt(row, 7), tablaVenta.getValueAt(row, 8).toString(), (Integer) tablaVenta.getValueAt(row, 9));
				double kromer= jugador.getCartera();
				if (kromer >= p.getPrecioCompra()) {
					compraProducto(p,jugador,nombre);
					todoProd.remove(p);
					display.remove(p);
					jugador = bd.selectUsuario();
					almacenProd=bd.selectProducto();
					actualizarTienda(display);
				} else {
					JOptionPane.showMessageDialog(v,"No tienes suficiente Kromer para realizar esta compra.");

				}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}

		});
		
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					
					
					
					
					
				}
			}

		});

		
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					v.dispose();
				}
			}

		});
	}
	
	private void actualizarTienda(List<Producto> productos) {
		tablaVenta.setModel(new LaunchTienda(productos));
	}
	
	
	private void compraProducto(Producto producto, Jugador jugador, String tienda) {
		double cartera= jugador.getCartera();
		double precio= producto.getPrecioCompra();
		jugador.setCartera(cartera-precio);
		bd.updateUsuario(jugador);
		bd.insertarProducto(producto, tienda, jugador);
	}
	
	private List<Producto> displayTienda(List<Producto> productos, Jugador jugador,String tienda) {
		List<Producto> display= new ArrayList<Producto>();
		
		if(tienda == "Muebles Carmele") { //PORCENTAJE DE INGRESO NO SUPERIOS AL 20%
			if(jugador.getDia()==1) {
				Producto p = new Producto(bd.generadorCodP(productos), tipoMueble.MESA, tematica.PRIMAVERA , color.ROJO, material.M_ROBLE, 36, 30, jugador.getDia(), tienda, jugador.getIdJugador());
				productos.add(p);
				display.add(p);
				p = new Producto(bd.generadorCodP(productos), tipoMueble.SILLA, tematica.RUSTICO, color.AMARILLO, material.M_PINO, 18, 15, jugador.getDia(), tienda, jugador.getIdJugador());
				productos.add(p);
				display.add(p);
				p = new Producto(bd.generadorCodP(productos), tipoMueble.ESTANTERIA, tematica.PRIMAVERA , color.BLANCO, material.M_ABEDUL, 11, 10, jugador.getDia(), tienda, jugador.getIdJugador());
				productos.add(p);
				display.add(p);
				return display;
			} else if(jugador.getDia()==2) {
				return display;
			} else if(jugador.getDia()==3) {
				return display;
			} else if(jugador.getDia()==4) {
				return display;
			} else if(jugador.getDia()==5) {
				return display;
			} else {
				return display;
			}
			
		} else if(tienda == "Colchones Iñaki") { //PORCENTAJE DE INGRESO SUPERIOR AL 50%
			if(jugador.getDia()==1) {
				Producto p = new Producto(bd.generadorCodP(productos), tipoMueble.CAMA, tematica.OTOÑO , color.NARANJA, material.M_ROBLE, 450, 200, jugador.getDia(), tienda, jugador.getIdJugador());
				productos.add(p);
				display.add(p);
				return display;
			} else if(jugador.getDia()==2) {
				return display;
			} else if(jugador.getDia()==3) {
				return display;
			} else if(jugador.getDia()==4) {
				return display;
			} else if(jugador.getDia()==5) {
				return display;
			} else {
				return display;
			}
		} else if(tienda == "Interiores Alex") { //PORCENTAJE DE INGRESO NO SUPERIOR AL 50%
			if(jugador.getDia()==1) {
				Producto p = new Producto(bd.generadorCodP(productos), tipoMueble.LAMPARA, tematica.HALLOWEEN , color.MORADO, material.PLASTICO, 90, 60, jugador.getDia(), tienda, jugador.getIdJugador());
				productos.add(p);
				display.add(p);
				p = new Producto(bd.generadorCodP(productos), tipoMueble.PUERTA, tematica.INDUSTRIAL , color.GRIS, material.ACERO, 55, 40, jugador.getDia(), tienda, jugador.getIdJugador());
				productos.add(p);
				display.add(p);
				p = new Producto(bd.generadorCodP(productos), tipoMueble.ARMARIO, tematica.ST_PATRICKS_DAY , color.VERDE, material.M_ABEDUL, 275, 200, jugador.getDia(), tienda, jugador.getIdJugador());
				productos.add(p);
				display.add(p);
				return display;
			} else if(jugador.getDia()==2) {
				return display;
			} else if(jugador.getDia()==3) {
				return display;
			} else if(jugador.getDia()==4) {
				return display;
			} else if(jugador.getDia()==5) {
				return display;
			} else {
				return display;
			}
		} else {
			if(jugador.getDia()==1) {
				Producto p = new Producto(bd.generadorCodP(productos), tipoMueble.DESCONOCIDO, tematica.ANYO_NUEVO_CHINO, color.DESCONOCIDO, material.DESCONOCIDO, 1000, 500, jugador.getDia(), tienda, jugador.getIdJugador());
				productos.add(p);
				display.add(p);
				return display;
			} else if(jugador.getDia()==2) {
				return display;
			} else if(jugador.getDia()==3) {
				return display;
			} else if(jugador.getDia()==4) {
				return display;
			} else if(jugador.getDia()==5) {
				return display;
			} else {
				return display;
			}
		}
	}
	
	
	
public class LaunchTienda  extends AbstractTableModel {
		
		
		private static final long serialVersionUID = 1L;
		private final List<String> headers = Arrays.asList("Codigo", "Tipo de Mueble", "Tematica", "Color", "Material","PV","Precio Compra","Dia","Tienda","Id. Jugador");
		private List<Producto> productos;
		
		public LaunchTienda(List<Producto> productos1) {
			this.productos = productos1;
		}
		
		@Override
		public String getColumnName(int column) {
			return headers.get(column);
		}

		@Override
		public int getRowCount() {
			return productos.size();
		}

		@Override
		public int getColumnCount() {
			return headers.size(); 
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Producto p = productos.get(rowIndex);
			switch (columnIndex) {
				case 0: return p.getCodigoObjeto();
				case 1: return p.getTipoMueble();
				case 2: return p.getTematica();
				case 3: return p.getColor();
				case 4: return p.getMaterial();
				case 5: return p.getPrecioVenta();
				case 6: return p.getPrecioCompra();
				case 7: return p.getDiaCompra();
				case 8: return p.getTienda();
				case 9: return p.getCodU();
				default: return null;
			}
		}

	}
	
}

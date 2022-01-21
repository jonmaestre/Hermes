package hermesServices;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
		actualizarTienda(todoProd);
		
		
		
	}
	
	private void actualizarTienda(List<Producto> productos) {
		tablaVenta.setModel(new LaunchTienda(productos));
	}
	
	private List<Producto> displayTienda(List<Producto> productos, Jugador jugador,String tienda) {
		List<Producto> display= new ArrayList<Producto>();
		//producto (codigoProducto, tipoMueble, tematica, color, material, precioVenta, precioCompra,diaCompra, codTienda, cod_u)
		if(tienda == "Muebles Carmele") {
			if(jugador.getDia()==1) {
				Producto p = new Producto(bd.generadorCodP(productos), tipoMueble.MESA, tematica.PRIMAVERA , color.ROJO, material.M_ROBLE, 30, 20, jugador.getDia(), tienda, jugador.getIdJugador());
				productos.add(p);
				display.add(p);
				p = new Producto(bd.generadorCodP(productos), tipoMueble.SILLA, tematica.PRIMAVERA , color.AMARILLO, material.M_PINO, 20, 15, jugador.getDia(), tienda, jugador.getIdJugador());
				productos.add(p);
				display.add(p);
				p = new Producto(bd.generadorCodP(productos), tipoMueble.ESTANTERIA, tematica.PRIMAVERA , color.BLANCO, material.M_ABEDUL, 8, 6, jugador.getDia(), tienda, jugador.getIdJugador());
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
			
		} else if(tienda == "Colchones Iñaki") {
			if(jugador.getDia()==1) {
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
		} else if(tienda == "Interiores Alex") {
			if(jugador.getDia()==1) {
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

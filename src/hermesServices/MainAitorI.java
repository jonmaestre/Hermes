package hermesServices;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.Hermes.Jugador;
import datos.Hermes.Producto;
import datos.Hermes.Venta;
import datos.Hermes.color;
import datos.Hermes.material;
import datos.Hermes.tematica;
import datos.Hermes.tipoMueble;

public class MainAitorI {
	private static BDStatic bdStatic;
	private static ArrayList<Producto> listaProds = new ArrayList<>();
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		bdStatic= new BDStatic();
		bdStatic.abrirConexion();
		
		
		Jugador u1= new Jugador(0, "Aitor", 14, 2000, 1001);
		Jugador u2= new Jugador(1, "Nico", 6, 4500, 3000);
		Jugador u3= new Jugador(1, "Nico", 20, 9000, 1000);
		bdStatic.insertarUsuario(u1);
		bdStatic.insertarUsuario(u2);
		
		Producto p1= new Producto(0, tipoMueble.SOFA, tematica.INVIERNO, color.GRIS, material.PLASTICO, 835.12, 500.00, 4, "Apolo´s", 1);
		Producto p2= new Producto(1, tipoMueble.SILLA, tematica.HALLOWEEN, color.BLANCO, material.M_ABEDUL, 100.13, 80.14, 1, "Polo´s", 0);
		Producto p3= new Producto(2, tipoMueble.SILLA, tematica.HALLOWEEN, color.BLANCO, material.M_ABEDUL, 100.13, 80.14, 1, "Polo´s", 0);
		listaProds.add(p1);
		listaProds.add(p2);
		listaProds.add(p3);
		bdStatic.insertarProductos(listaProds);
		bdStatic.actualizarUsuario(u3);
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		ArrayList<Producto> listaProdTest= bdStatic.seleccionarProducto(u1);
		
		bdStatic.cerrarConexion();
		
		System.out.println(listaProdTest);
		System.out.println(listaUsuTest);
		ventanaHermes vh= new ventanaHermes("Hola");
		ventanaI vi= new ventanaI();
		ventanaTiendas vt=new ventanaTiendas("Hola");
	}

}

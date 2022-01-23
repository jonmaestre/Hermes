package hermesServices;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datos.Hermes.Jugador;
import datos.Hermes.Producto;
import datos.Hermes.Venta;
import datos.Hermes.color;
import datos.Hermes.material;
import datos.Hermes.tematica;
import datos.Hermes.tipoMueble;


public class BDStaticTest {
	private BDStatic bdStatic;
	private ArrayList<Producto> listaProds;
	private ArrayList<Venta> listaVentas;
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		bdStatic= new BDStatic();
//		bdStatic.abrirConexion();
		
		
		Jugador u1= new Jugador(0, "Aitor", 14, 2000, 1001);
		Jugador u2= new Jugador(1, "Nico", 6, 4500, 3000);
//		bdStatic.insertarUsuario(u1);
//		bdStatic.insertarUsuario(u2);
		
		Producto p1= new Producto(0, tipoMueble.SOFA, tematica.INVIERNO, color.GRIS, material.PLASTICO, 835.12, 500.00, 4, "Apolo´s", 1);
		Producto p2= new Producto(1, tipoMueble.SILLA, tematica.HALLOWEEN, color.BLANCO, material.M_ABEDUL, 100.13, 80.14, 1, "Polo´s", 0);
		listaProds.add(p1);
		listaProds.add(p2);
//		bdStatic.insertarProductos(listaProds);
		
	}

	@After
	public void tearDown() throws Exception {
//		bdStatic.cerrarConexion();
	}
	@Test
	public void seleccionarProductoTest() {
//		ArrayList<Jugador> listaUsuTest=BDStatic.seleccionarUsuario();
//		ArrayList<Producto> listaProdTest= BDStatic.seleccionarProducto(listaUsuTest.get(0));
//		
//		assertEquals(2,listaUsuTest.size());
		
	}

}

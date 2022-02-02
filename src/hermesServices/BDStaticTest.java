package hermesServices;

import static org.junit.Assert.*;

import java.io.IOException;
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
	private ArrayList<Producto> listaProds=new ArrayList<>();
	private ArrayList<Venta> listaVentas =new ArrayList<>();
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException, IOException {
		bdStatic= new BDStatic();
		bdStatic.abrirConexion();
		bdStatic.reiniciarBDS();
		
		
		Jugador u1= new Jugador(0, "Aitor", 14, 2000, 1001);
		Jugador u2= new Jugador(1, "Nico", 6, 4500, 3000);
		bdStatic.insertarUsuario(u1);
		bdStatic.insertarUsuario(u2);
		
		Producto p1= new Producto(0, tipoMueble.SOFA, tematica.INVIERNO, color.GRIS, material.PLASTICO, 835.12, 500.00, 4, "Apolo큦", 1);
		Producto p2= new Producto(1, tipoMueble.SILLA, tematica.HALLOWEEN, color.BLANCO, material.M_ABEDUL, 100.13, 80.14, 1, "Polo큦", 0);
		listaProds.add(p1);
		listaProds.add(p2);
		bdStatic.insertarProductos(listaProds);
		
		Venta v1= new Venta(0, tipoMueble.SOFA, tematica.INVIERNO, color.GRIS, material.PLASTICO, 835.12, 500.00, 4,7, "Apolo큦", 1);
		Venta v2= new Venta(1, tipoMueble.SILLA, tematica.HALLOWEEN, color.BLANCO, material.M_ABEDUL, 100.13, 80.14, 1,2, "Polo큦", 0);
		listaVentas.add(v1);
		listaVentas.add(v2);
		bdStatic.insertarVentas(listaVentas);
	}

	@After
	public void tearDown() throws Exception {
		bdStatic.cerrarConexion();
	}
	@Test
	public void seleccionarUsuarioTest() throws SQLException {
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		assertEquals(2,listaUsuTest.size());
		int id=listaUsuTest.get(0).getIdJugador();
		for (int i = 1; i <listaUsuTest.size()-1; i++) {
			assertTrue(id<listaUsuTest.get(i).getIdJugador());
			id=listaUsuTest.get(i).getIdJugador();
		} 
			
		
	}
	@Test
	public void borrarUsuarioTest() throws SQLException {
		Jugador u=new Jugador(0, "Aitor", 14, 2000, 1001);
		bdStatic.borrarUsuario(u);
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		assertEquals(1,listaUsuTest.size());
		assertFalse(0==listaUsuTest.get(0).getIdJugador());
	}
	
	@Test
	public void insertarUsuarioTest() throws SQLException {
		Jugador u=new Jugador(2, "Andoni", 10, 1500, 235);
		bdStatic.insertarUsuario(u);
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		assertEquals(3,listaUsuTest.size());
		assertTrue(2==listaUsuTest.get(listaUsuTest.size()-1).getIdJugador());
	}

	@Test
	public void actualizarUsuarioTest() throws SQLException {
		Jugador u=new Jugador(0, "Aitor", 19, 1500, 1900);
		bdStatic.actualizarUsuario(u);
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		assertEquals(2,listaUsuTest.size());
		assertEquals(19,listaUsuTest.get(0).getDia());
		assertEquals(1500,listaUsuTest.get(0).getExp());
		
		
	}
	@Test
	public void seleccionarProductoTest() throws SQLException {
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		ArrayList<Producto> listaProds=bdStatic.seleccionarProducto(listaUsuTest.get(1));
		assertEquals(1,listaProds.size());
		assertEquals(0,listaProds.get(0).getCodigoObjeto());
	}
	@Test
	public void borrarProductoTest() throws SQLException {
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		bdStatic.borrarProductos(listaUsuTest.get(0));
		ArrayList<Producto> listaProds=bdStatic.seleccionarProducto(listaUsuTest.get(0));
		assertEquals(0,listaProds.size());
		
	}
	@Test
	public void insertarProductoTest() throws SQLException {
		ArrayList<Producto> listaProds1=new ArrayList<>();
		Producto p=new Producto(2, tipoMueble.SILLA, tematica.HALLOWEEN, color.BLANCO, material.M_ABEDUL, 100.13, 80.14, 1, "Polo큦", 1);
		listaProds1.add(p);
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		bdStatic.insertarProductos(listaProds1);
		ArrayList<Producto> listaProds2=bdStatic.seleccionarProducto(listaUsuTest.get(1));
		assertEquals(2,listaProds2.size());
		assertEquals(2,listaProds2.get(listaProds2.size()-1).getCodigoObjeto());
		
	}
	@Test
	public void seleccionarVentaTest() throws SQLException {
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		ArrayList<Venta> listaVentas=bdStatic.seleccionarVenta(listaUsuTest.get(1));
		assertEquals(1,listaVentas.size());
		assertEquals(0,listaVentas.get(0).getCodigoVenta());
	}
	@Test
	public void borrarVentaTest() throws SQLException {
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		bdStatic.borrarVentas(listaUsuTest.get(1));
		ArrayList<Venta> listaVentas=bdStatic.seleccionarVenta(listaUsuTest.get(1));
		assertEquals(0,listaVentas.size());
		
	}
	@Test
	public void insertarVentaTest() throws SQLException {
		ArrayList<Venta> listaVenta1=new ArrayList<>();
		Venta v= new Venta(2, tipoMueble.SOFA, tematica.INVIERNO, color.GRIS, material.PLASTICO, 835.12, 500.00, 4,7, "Apolo큦", 1);
		listaVenta1.add(v);
		ArrayList<Jugador> listaUsuTest=bdStatic.seleccionarUsuario();
		bdStatic.insertarVentas(listaVenta1);
		ArrayList<Venta> listaVentas2=bdStatic.seleccionarVenta(listaUsuTest.get(1));
		assertEquals(2,listaVentas2.size());
		assertEquals(2,listaVentas2.get(1).getCodigoVenta());
		
	}
}

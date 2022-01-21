package hermesServices;

import java.awt.Color;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jdi.connect.spi.Connection;

import datos.Hermes.*;

public class BDynamic {
	
	private Connection conn;
	private Logger logger = Logger.getLogger( "BaseDatos" );
	
	//nombre sirve para buscar el nombre de la base de datos deseada y reiniciar si lo pones a true borra lo que haya dentro
	//de la base de datos. 
	public void abrirBD()  throws IOException, SQLException {
		try {
			logger.log( Level.INFO, "Carga de libreria org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexion con basedatosdyn.bd");
			conn = (Connection) DriverManager.getConnection("jdbc:sqlite:basedatosdyn.bd");
		} catch(Exception e) {
			logger.log( Level.SEVERE, "Excepcion al abrir conexion", e );
		}
	}	
	public void cerrarConexion() throws IOException, SQLException {
		try {
			logger.log( Level.INFO, "Cerrando conexion" );
			conn.close();
		}catch(Exception e) {
			logger.log( Level.SEVERE, "Excepcion al cerrar conexion", e );
		}
	}
	
	public void reiniciarBD()  throws IOException, SQLException {
		try {
		Statement statement =  ((java.sql.Connection) conn).createStatement();
		String sent;
		
		sent = "DROP TABLE IF EXISTS producto";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		sent = "CREATE TABLE producto (codigoProducto INT(3) NOT NULL, tipoMueble varchar(20), tematica varchar(30), color varchar(30), material varchar(30), precioVenta dec, precioCompra dec, diaCompra int(2), codTienda int(3),cod_u INT(3) NOT NULL, PRIMARY KEY(codigoProducto),FOREIGN KEY(cod_u) REFERENCES usuario(cod_u));";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		
		
		sent = "DROP TABLE IF EXISTS ventas";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		sent = "CREATE TABLE ventas (codigoVenta INT(3) NOT NULL, tipoMueble varchar(20), tematica varchar(30), color varchar(30), material varchar(30), precioVenta dec, precioCompra dec, diaCompra int(2), codTienda int(3),cod_u INT(3) NOT NULL, PRIMARY KEY(codigoVenta),FOREIGN KEY(cod_u) REFERENCES usuario(cod_u) ;";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );		

		sent = "DROP TABLE IF EXISTS usuario";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		sent = "CREATE TABLE usuario (cod_u INT(3) NOT NULL, nombre_u varchar (20), dia INT(2), exp INT(9), kromer INT(9), PRIMARY KEY(cod_u));";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Excepcion en el reinicio de la BD",e);
		}
	}
	
	
	//Agregar un nuevo producto a la BD.    FALTAN METODOS DE SELECT VENTAS Y PRODUCTOS EN LA OTRA BD
		public void inicializarBD(Jugador jugador) {
			try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
				reiniciarBD();
				String sent = "insert into usuario (cod_u, nombre_u, dia, exp, kromer) values (" + jugador.getIdJugador() + ",'" + jugador.getNombre() + "'," + jugador.getDia() + "," + jugador.getExp() + "," + jugador.getCartera() +");";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				List<Producto> prods;
				//prods=  BD.SELECTPRODUCTOS(jugador);
				for (Producto producto : prods) {
					sent = "insert into producto (codigoProducto, tipoMueble, tematica, color, material, precioVenta, precioCompra,diaCompra, codTienda, cod_u) values (" + producto.getCodigoObjeto() + ",'" + producto.getTipoMueble() + "','" + producto.getTematica() + "','" + producto.getColor() + "','" + producto.getMaterial() + "'," + producto.getPrecioVenta() + "," + producto.getPrecioCompra() + "," + producto.getDiaCompra() + "," + producto.getTienda() + "," + producto.getCodU() +");";
					logger.log( Level.INFO, "Statement: " + sent );
					statement.executeUpdate( sent );
				}
				
				List<Venta> ventas;
				//prods=  BD.SELECTVENTAS(jugador);
				for (Venta venta : ventas) {
					sent = "insert into ventas (codigoVenta, tipoMueble, tematica, color, material, precioVenta, precioCompra,diaCompra, codTienda, cod_u) values (" + venta.getCodigoVenta() + ",'" + venta.getTipoMueble() + "','" + venta.getTematica() + "','" + venta.getColor() + "','" + venta.getMaterial() + "'," + venta.getPrecioVenta() + "," + venta.getPrecioCompra() + "," + venta.getDiaVenta() + "," + venta.getTienda() + "," + jugador.getIdJugador() +");";
					logger.log( Level.INFO, "Statement: " + sent );
					statement.executeUpdate( sent );
				}
				
				
			} catch (Exception e) {
				logger.log( Level.SEVERE, "Excepcion al inicializar la BD", e );
			}
		}
	
	//Agregar un nuevo producto a la BD.
	public void insertarProducto( Producto producto, Tiendas tienda, Jugador jugador) {
		try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
			String sent = "insert into producto (codigoProducto, tipoMueble, tematica, color, material, precioVenta, precioCompra,diaCompra, codTienda, cod_u) values (" + producto.getCodigoObjeto() + ",'" + producto.getTipoMueble() + "','" + producto.getTematica() + "','" + producto.getColor() + "','" + producto.getMaterial() + "'," + producto.getPrecioVenta() + "," + producto.getPrecioCompra() + "," + jugador.getDia() + "," + tienda.getCodTienda() + "," + jugador.getIdJugador() +");";
			logger.log( Level.INFO, "Statement: " + sent );
			statement.executeUpdate( sent );
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion al insertar un producto", e );
		}
	}
	//Eliminar un producto de la BD.
	public void eliminarProducto( Producto producto, Jugador jugador) {
		try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
			int c = producto.getCodigoObjeto();
			String sent = "delete from producto " +" where codigoProducto=" + c+";";
			logger.log( Level.INFO, "Statement: " + sent );
			statement.executeUpdate( sent );
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion al eliminar un producto", e );
		}
	}
	//Agregar una nueva venta a la BD.
	public void insertarVenta(Venta venta, Jugador jugador) {
		try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
			String sent = "insert into ventas (codigoVenta, tipoMueble, tematica, color, material, precioVenta, precioCompra,diaCompra, codTienda, cod_u) values (" + venta.getCodigoVenta() + ",'" + venta.getTipoMueble() + "','" + venta.getTematica() + "','" + venta.getColor() + "','" + venta.getMaterial() + "'," + venta.getPrecioVenta() + "," + venta.getPrecioCompra() + "," + venta.getDiaVenta() + "," + venta.getTienda() + "," + jugador.getIdJugador() +");";
			logger.log( Level.INFO, "Statement: " + sent );
			statement.executeUpdate( sent );
		} catch (Exception e) {
			logger.log( Level.SEVERE, "ExcepciÃ³n", e );
		}
	
	}
	
		
	
	
	
	
	
	/*
	
	 Busca los árboles de una zona en la tabla abierta de BD, usando la sentencia SELECT de SQL
	 (Atención: esta operación es síncrona, no devuelve el control hasta que se ejecuta completamente en base de datos)
	 @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente)
	 @param codZona	Código de zona a buscar
	 @return Lista de árboles en esa zona, null si hay cualquier error de BD
	
	public static ArrayList<Arbol> arbolSelect( Statement st, String codZona ) {
		String sentSQL = "";
		try {
			ArrayList<Arbol> ret = new ArrayList<>();
			sentSQL = "select * from arbol where zona='" + secu(codZona) + "'";
			log( Level.INFO, "BD\t" + sentSQL, null );
			ResultSet rs = st.executeQuery( sentSQL );
			while (rs.next()) {
				double lat = rs.getDouble( "latitud" );
				double lon = rs.getDouble( "longitud" );
				String nom = rs.getString( "nombre" );
				int edad = rs.getInt( "edad" );
				int color = rs.getInt( "color" );
				Arbol arbol = new Arbol( new PuntoGPS( lat,  lon ), nom, edad );
				arbol.setColor( new Color( color ));
				ret.add( arbol );
			}
			rs.close();
			return ret;
		} catch (Exception e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}
	*/
	
	
	/*
	 Borra un árbol de la tabla abierta de BD, usando la sentencia DELETE de SQL.
	 @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente)
	@param codZona	Código de zona del árbol
	 @param latitud	Latitud de posicionamiento del árbol
	 @param longitud	Longitud de posicionamiento del árbol

	public static void arbolDelete( final Statement st, final String codZona, final double latitud, final double longitud ) {
		tareasPendientes.add( new Runnable() { @Override public void run() {
			String sentSQL = "";
			try {
				sentSQL = "delete from arbol " +
						" where zona='" + codZona + "' AND latitud = " + latitud + " AND longitud = " + longitud;
				int val = st.executeUpdate( sentSQL );
				log( Level.INFO, "BD modificada " + val + " fila\t" + sentSQL, null );
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
				lastError = e;
				e.printStackTrace();
			}
		} } );
	}
	 */

	
}

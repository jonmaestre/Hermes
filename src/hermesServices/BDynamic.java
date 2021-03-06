package hermesServices;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



import datos.Hermes.*;

public class BDynamic {
	
	private static Connection conn;
	private BDStatic bd;
	private Logger logger = Logger.getLogger( "BaseDatos" );
	
	//nombre sirve para buscar el nombre de la base de datos deseada y reiniciar si lo pones a true borra lo que haya dentro
	//de la base de datos. 
	public void abrirBD()  throws IOException, SQLException {
		try {
			logger.log( Level.INFO, "Carga de libreria org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexion con basedatosdyn.bd");
			conn = DriverManager.getConnection("jdbc:sqlite:basedatosdyn.bd");
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
		
		try (Statement statement =  conn.createStatement();){
		String sent;
		
		sent = "DROP TABLE IF EXISTS usuario;";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		sent = "CREATE TABLE usuario (cod_u INT(3) NOT NULL, nombre_u varchar (20), dia INT(2), exp INT(9), kromer dec(9,2), PRIMARY KEY(cod_u));";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		
		sent = "DROP TABLE IF EXISTS producto;";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		sent = "CREATE TABLE producto (codigoProducto INT(3) NOT NULL, tipoMueble varchar(20), tematica varchar(30), color varchar(30), material varchar(30), precioVenta dec, precioCompra dec, diaCompra int(2), codTienda varchar(20) ,cod_u INT(3) NOT NULL, PRIMARY KEY(codigoProducto),FOREIGN KEY(cod_u) REFERENCES usuario(cod_u));";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		
		
		sent = "DROP TABLE IF EXISTS ventas;";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		sent = "CREATE TABLE ventas (codigoVenta INT(3) NOT NULL, tipoMueble varchar(20), tematica varchar(30), color varchar(30), material varchar(30), precioVenta dec, precioCompra dec, diaCompra int(2), diaVenta int(2), codTienda varchar(20),cod_u INT(3) NOT NULL, PRIMARY KEY(codigoVenta),FOREIGN KEY(cod_u) REFERENCES usuario(cod_u)) ;";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );		

		} catch (Exception e) {
			logger.log(Level.SEVERE,"Excepcion en el reinicio de la BD",e);
		}
	}
	
	
	//Abrir la BD, introducir los productos/ventas seg?n el usuario
		public void inicializarBD(Jugador jugador) {
			try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
				reiniciarBD();
				String sent = "insert into usuario (cod_u, nombre_u, dia, exp, kromer) values (" + jugador.getIdJugador() + ",'" + jugador.getNombre() + "'," + jugador.getDia() + "," + jugador.getExp() + "," + jugador.getCartera() +");";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				bd = new BDStatic();
				try {
					bd.abrirConexion();
				} catch (Exception e) {
					e.printStackTrace();
				}
				

				
				List<Producto> prods= bd.seleccionarProducto(jugador);
				for (Producto producto : prods) {
					sent = "insert into producto (codigoProducto, tipoMueble, tematica, color, material, precioVenta, precioCompra,diaCompra, codTienda, cod_u) values (" + producto.getCodigoObjeto() + ",'" + producto.getTipoMueble() + "','" + producto.getTematica() + "','" + producto.getColor() + "','" + producto.getMaterial() + "'," + producto.getPrecioVenta() + "," + producto.getPrecioCompra() + "," + producto.getDiaCompra() + ",'" + producto.getTienda() + "'," + producto.getCodU() +");";
					logger.log( Level.INFO, "Statement: " + sent );
					statement.executeUpdate( sent );
				}
				
				List<Venta> ventas= bd.seleccionarVenta(jugador);;
				for (Venta venta : ventas) {
					sent = "insert into ventas (codigoVenta, tipoMueble, tematica, color, material, precioVenta, precioCompra,diaCompra, codTienda, cod_u) values (" + venta.getCodigoVenta() + ",'" + venta.getTipoMueble() + "','" + venta.getTematica() + "','" + venta.getColor() + "','" + venta.getMaterial() + "'," + venta.getPrecioVenta() + "," + venta.getPrecioCompra() + "," + venta.getDiaVenta() + ",'" + venta.getTienda() + "'," + jugador.getIdJugador() +");";
					logger.log( Level.INFO, "Statement: " + sent );
					statement.executeUpdate( sent );
				}
				
				
			} catch (Exception e) {
				logger.log( Level.SEVERE, "Excepcion al inicializar la BD", e );
			}
		}
	
	//Agregar un nuevo producto a la BD.
	public void insertarProducto( Producto producto, String tienda, Jugador jugador) {
		try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
			String sent = "insert into producto (codigoProducto, tipoMueble, tematica, color, material, precioVenta, precioCompra,diaCompra, codTienda, cod_u) values (" + producto.getCodigoObjeto() + ",'" + producto.getTipoMueble() + "','" + producto.getTematica() + "','" + producto.getColor() + "','" + producto.getMaterial() + "'," + producto.getPrecioVenta() + "," + producto.getPrecioCompra() + "," + jugador.getDia() + ",'" + tienda + "'," + jugador.getIdJugador() +");";
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
			String sent = "insert into ventas (codigoVenta, tipoMueble, tematica, color, material, precioVenta, precioCompra,diaCompra, codTienda, cod_u) values (" + venta.getCodigoVenta() + ",'" + venta.getTipoMueble() + "','" + venta.getTematica() + "','" + venta.getColor() + "','" + venta.getMaterial() + "'," + venta.getPrecioVenta() + "," + venta.getPrecioCompra() + "," + venta.getDiaVenta() + ",'" + venta.getTienda() + "'," + jugador.getIdJugador() +");";
			logger.log( Level.INFO, "Statement: " + sent );
			statement.executeUpdate( sent );
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion en la insercion de ventas", e );
		}
	}
	
	public void updateUsuario (Jugador jugador) {
		try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
			String sent = "update usuario set dia="+ jugador.getDia()+",exp="+ jugador.getExp()+",kromer="+ jugador.getCartera()+" where cod_u="+ jugador.getIdJugador()+";";
			logger.log( Level.INFO, "Statement: " + sent );
			statement.executeUpdate( sent );
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion en la actualizaci?n de datos", e );
		}
	}
	
	//usuario (cod_u, nombre_u, dia, exp, kromer)
	
	public Jugador selectUsuario() throws SQLException{
		Jugador u = new Jugador(0, null, 0, 0, 0);
		try(Statement statement = ((java.sql.Connection) conn).createStatement()){
			ResultSet rs= statement.executeQuery("SELECT * FROM usuario;");
			while(rs.next()) {
				u=new Jugador(
					rs.getInt("cod_u"),
					rs.getString("nombre_u"),
					rs.getInt("dia"),
					rs.getInt("exp"),
					rs.getDouble("kromer")
					);
			}
		}
		return u;
	}
	
	public ArrayList<Producto> selectProducto() throws SQLException{
		ArrayList<Producto> listaProds= new ArrayList<>();
		try(Statement statement = ((java.sql.Connection) conn).createStatement()){
			ResultSet rs= statement.executeQuery("SELECT * FROM producto;");
			while(rs.next()) {
				Producto p=new Producto(
						rs.getInt("codigoProducto"),
						tipoMueble.valueOf(rs.getString("tipoMueble")),
						tematica.valueOf(rs.getString("tematica")),
						color.valueOf(rs.getString("color")),
						material.valueOf(rs.getString("material")),
						rs.getDouble("precioVenta"),
						rs.getDouble("precioCompra"),
						rs.getInt("diaCompra"),
						rs.getString("codTienda"),
						rs.getInt("cod_u")
						);
				listaProds.add(p);
			}
		}
		return listaProds;
	}
	
	public ArrayList<Venta> selectVenta() throws SQLException{
		ArrayList<Venta> listaVentas= new ArrayList<>();
		try(Statement statement = ((java.sql.Connection) conn).createStatement()){
			ResultSet rs= statement.executeQuery("SELECT * FROM ventas;");
			while(rs.next()) {
				Venta v=new Venta(
						rs.getInt("codigoVenta"),
						tipoMueble.valueOf(rs.getString("tipoMueble")),
						tematica.valueOf(rs.getString("tematica")),
						color.valueOf(rs.getString("color")),
						material.valueOf(rs.getString("material")),
						rs.getDouble("precioVenta"),
						rs.getDouble("precioCompra"),
						rs.getInt("diaCompra"),
						rs.getInt("diaVenta"),
						rs.getString("codTienda"),
						rs.getInt("cod_u")
						);
				listaVentas.add(v);
			}
		}
		return listaVentas;
	}
		
	public void guardarDatos() throws SQLException, Exception {
		try (Statement statement = ((java.sql.Connection) conn).createStatement()) {

			Jugador jugador= selectUsuario();
			ArrayList<Producto> productos=selectProducto();
			ArrayList<Venta> ventas=selectVenta();

			bd = new BDStatic();
			try {
				bd.abrirConexion();

				bd.insertarProductos(productos);
				bd.insertarVentas(ventas);
				bd.actualizarUsuario(jugador);

				reiniciarBD();
				inicializarBD(jugador);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}

	public int generadorCodP(List<Producto> productos) {
		int code=0;
		for (Producto p : productos) {
			if (code==p.getCodigoObjeto()) {
				code++;
			}
		}
		return code;
	}
	
	public int generadorCodV(List<Venta> ventas) {
		int code=0;
		for (Venta v : ventas) {
			if (code==v.getCodigoVenta()) {
				code++;
			}
		}
		return code;
	}
}

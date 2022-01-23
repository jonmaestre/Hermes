package hermesServices;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datos.Hermes.Jugador;
import datos.Hermes.Producto;
import datos.Hermes.Venta;
import datos.Hermes.color;
import datos.Hermes.material;
import datos.Hermes.tematica;
import datos.Hermes.tipoMueble;

public class BDStatic {
	private Connection conn;
	
	public void abrirConexion() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:basedatossta.bd");
		
	}
	public void cerrarConexion() throws SQLException {
		conn.close();
	}
	
	public void insertarUsuario(Jugador usuario) throws SQLException {
		try(Statement stmnt=conn.createStatement()){
			String sent="INSERT INTO usuario VALUES(" + usuario.getIdJugador() + ",'" + usuario.getNombre() + "'," + usuario.getDia() +
					"," + usuario.getExp() + "," + usuario.getCartera() + ");";
			stmnt.executeUpdate(sent);
		}
	}
	public void borrarVentas(Jugador usuario) throws SQLException {
		try(Statement stmnt=conn.createStatement()){
			String sent="DELETE FROM venta WHERE codU=" + usuario.getIdJugador() + ";";
			stmnt.executeUpdate(sent);
		}
	}
	public void borrarProductos(Jugador usuario) throws SQLException {
		try(Statement stmnt=conn.createStatement()){
			String sent="DELETE FROM producto WHERE codU=" + usuario.getIdJugador() + ";";
			stmnt.executeUpdate(sent);
		}
	}
	
	public void borrarUsuario(Jugador usuario) throws SQLException {
		borrarProductos(usuario);
		borrarVentas(usuario);
		try(Statement stmnt=conn.createStatement()){
			String sent="DELETE FROM usuario WHERE idJugador=" + usuario.getIdJugador() + ";";
			stmnt.executeUpdate(sent);
		}
	}
	
	public ArrayList<Jugador> seleccionarUsuario() throws SQLException{
		ArrayList<Jugador> listaUsu= new ArrayList<>();
		try(Statement stmnt=conn.createStatement()){
			ResultSet rs= stmnt.executeQuery("SELECT * FROM usuario;");
			while(rs.next()) {
			Jugador u=new Jugador(
					rs.getInt("idJugador"),
					rs.getString("nombre"),
					rs.getInt("dia"),
					rs.getInt("exp"),
					rs.getInt("cartera")
					);
			listaUsu.add(u);
			}
		}
		return listaUsu;
	}
	
	public ArrayList<Producto> seleccionarProducto(Jugador usuario) throws SQLException{
		ArrayList<Producto> listaProds= new ArrayList<>();
		try(Statement stmnt=conn.createStatement()){
			ResultSet rs= stmnt.executeQuery("SELECT * FROM producto WHERE codU= " + usuario.getIdJugador() + ";");
			while(rs.next()) {
				Producto p=new Producto(
						rs.getInt("codigoObjeto"),
						tipoMueble.valueOf(rs.getString("tipoMueble")),
						tematica.valueOf(rs.getString("tematica")),
						color.valueOf(rs.getString("color")),
						material.valueOf(rs.getString("material")),
						rs.getFloat("precioVenta"),
						rs.getFloat("precioCompra"),
						rs.getInt("diaCompra"),
						rs.getString("tienda"),
						rs.getInt("codU")
						);
				listaProds.add(p);
			}
		}
		return listaProds;
	}
	
	public ArrayList<Venta> seleccionarVenta(Jugador usuario) throws SQLException{
		ArrayList<Venta> listaVentas= new ArrayList<>();
		try(Statement stmnt=conn.createStatement()){
			ResultSet rs= stmnt.executeQuery("SELECT * FROM venta WHERE codU= " + usuario.getIdJugador() + ";");
			while(rs.next()) {
				Venta v=new Venta(
						rs.getInt("codigoVenta"),
						tipoMueble.valueOf(rs.getString("tipoMueble")),
						tematica.valueOf(rs.getString("tematica")),
						color.valueOf(rs.getString("color")),
						material.valueOf(rs.getString("material")),
						rs.getFloat("precioVenta"),
						rs.getFloat("precioCompra"),
						rs.getInt("diaCompra"),
						rs.getInt("diaVenta"),
						rs.getString("tienda"),
						rs.getInt("codU")
						);
				listaVentas.add(v);
			}
		}
		return listaVentas;
	}
	
	public void insertarProductos(ArrayList<Producto> listaProds) throws SQLException {
		try(Statement stmnt=conn.createStatement()){
			for (Producto producto : listaProds) {
				String sent= "INSERT INTO producto VALUES(" + producto.getCodigoObjeto() + ",'" + producto.getTipoMueble() + 
						"','" + producto.getTematica() + "','" + producto.getColor() + "','" + producto.getMaterial() + 
						"'," +  producto.getPrecioVenta() + "," + producto.getPrecioCompra() + "," + producto.getDiaCompra() +
						",'" + producto.getTienda() + "'," + producto.getCodU() + ";";
				stmnt.executeUpdate(sent);	
			}
		}
	}
	
	public void insertarVentas(ArrayList<Venta> listaVentas) throws SQLException {
		try(Statement stmnt=conn.createStatement()){
			for (Venta ventas : listaVentas) {
				String sent= "INSERT INTO venta VALUES(" + ventas.getCodigoVenta() + ",'" + ventas.getTipoMueble() + 
						"','" + ventas.getTematica() + "','" + ventas.getColor() + "','" + ventas.getMaterial() + 
						"'," +  ventas.getPrecioVenta() + "," + ventas.getPrecioCompra() + "," + ventas.getDiaCompra() +
						"," + ventas.getDiaVenta() + ",'" + ventas.getTienda() + "'," + ventas.getCodU() + ";";
				stmnt.executeUpdate(sent);	
			}
		}
	}
	
	public void actualizarUsuario(Jugador usuario) throws SQLException {
		try(Statement stmnt=conn.createStatement()){
			String sent="UPDATE usuario SET dia=" + usuario.getDia() + ",exp=" + usuario.getExp() + ",cartera=" + usuario.getCartera() +
					"WHERE idJugador=" + usuario.getIdJugador() + ";";
			stmnt.executeUpdate(sent);
		}
	}

}

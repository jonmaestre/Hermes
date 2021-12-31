package datos.Hermes;

import java.util.ArrayList;

public class Almacen extends Datos {
	protected int codAlm;
	protected tipoMueble nombre;
	protected int stock;
	protected int capacidad;
	protected ArrayList<Producto> listaProductos;
	
	public Almacen(int codAlm, tipoMueble nombre, int stock, int capacidad, ArrayList<Producto> listaProductos) {
		super();
		this.codAlm = codAlm;
		this.nombre = nombre;
		this.stock = stock;
		this.capacidad = capacidad;
		this.listaProductos = listaProductos;
	}

	public int getCodAlm() {
		return codAlm;
	}

	public void setCodAlm(int codAlm) {
		this.codAlm = codAlm;
	}

	public tipoMueble getNombre() {
		return nombre;
	}

	public void setNombre(tipoMueble nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		for(Producto i:listaProductos) { //Para meter todos los productos de cada tipo en el almacen correspondiente.
			if(nombre==i.getTipoMueble()) {
				this.listaProductos = listaProductos;
		}
		
		}
		
	}
	
	
	
	
	
}

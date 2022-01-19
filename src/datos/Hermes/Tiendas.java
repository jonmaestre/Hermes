package datos.Hermes;

public class Tiendas extends Datos {
	
	protected int codTienda;
	protected String nombreTienda;
	

	public int getCodTienda() {
		return codTienda;
	}

	public void setCodTienda(int codTienda) {
		this.codTienda = codTienda;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}	
	
	
	public Tiendas(int codTienda, String nombreTienda) {
		super();
		this.codTienda = codTienda;
		this.nombreTienda = nombreTienda;
		
	}
}

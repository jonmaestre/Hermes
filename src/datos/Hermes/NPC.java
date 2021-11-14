package datos.Hermes;

import java.util.ArrayList;

public class NPC extends Datos {
	private String nombre_NPC = "";
	private String tipoNPC = "" ;// estilo este te vende sillas otro te vende mesas...
	private String nombre_lugar = ""; // esto es para saber donde van ha estar puestos los NPCs pero nose si poner String o por coordenadas hay que mirarlo 
	//private ArrayList<String> tipo_objetos == hay que unirloo con un enum de los objetos que vende cada uno o ponerlo ha mano ns como preferis
	
	public NPC(String tipo, String nombre_NPC, String tipoNPC, String nombre_lugar) {
		super(tipo);
		this.nombre_NPC = nombre_NPC;
		this.tipoNPC = tipoNPC;
		this.nombre_lugar = nombre_lugar;
	}

	public String getNombre_NPC() {
		return nombre_NPC;
	}

	public void setNombre_NPC(String nombre_NPC) {
		this.nombre_NPC = nombre_NPC;
	}

	public String getTipoNPC() {
		return tipoNPC;
	}

	public void setTipoNPC(String tipoNPC) {
		this.tipoNPC = tipoNPC;
	}

	public String getNombre_lugar() {
		return nombre_lugar;
	}

	public void setNombre_lugar(String nombre_lugar) {
		this.nombre_lugar = nombre_lugar;
	}
	
	
}

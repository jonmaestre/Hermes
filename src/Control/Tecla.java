package Control;

public class Tecla {
	private boolean pulsada = false;
	private long ultimaPulsacion = System.nanoTime();
	
	 public void teclaPulsada()
	    {
	        pulsada = true;
	        ultimaPulsacion = System.nanoTime();
	    }
	    
	    public void teclaLiberada()
	    {
	        pulsada = false;
	    }
	    
	    public boolean isPulsada()
	    {
	        return pulsada;
	    }
	    
	    public long obtenerUltimaPulsacion()
	    {
	        return ultimaPulsacion;
	    }

}

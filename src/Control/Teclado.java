package Control;

import java.awt.event.KeyEvent;

public class Teclado {
	 public Tecla arriba = new Tecla();
	    public Tecla abajo = new Tecla();
	    public Tecla izquierda = new Tecla();
	    public Tecla derecha = new Tecla();
	    
	    public boolean recogiendo = false;
	    public boolean corriendo = false;
	    public boolean debug = false;
	    public boolean isInventario = false;
	    public boolean isInformacion = false;

	    public void keyTyped(KeyEvent e) {
	        
	    }

	    public void keyPressed(KeyEvent e) {
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_W:
	                arriba.teclaPulsada();
	                break;
	            case KeyEvent.VK_S:
	                abajo.teclaPulsada();
	                break;
	            case KeyEvent.VK_A:
	                izquierda.teclaPulsada();
	                break;
	            case KeyEvent.VK_D:
	                derecha.teclaPulsada();
	                break;
	            case KeyEvent.VK_ENTER:
	                recogiendo = true;
	                break;
	            case KeyEvent.VK_SHIFT:
	                corriendo = true;
	                break;
	            case KeyEvent.VK_ESCAPE:
	                System.exit(0);
	                    break;
	            case KeyEvent.VK_F1:
	                debug = !debug;
	                break;
	            case KeyEvent.VK_I:
	                isInventario = !isInventario;
	                break;
	            case KeyEvent.VK_U:
	                isInformacion = ! isInformacion;
	                break;
	        }
	    }

	    public void keyReleased(KeyEvent e) {
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_W:
	                arriba.teclaLiberada();
	                break;
	            case KeyEvent.VK_S:
	                abajo.teclaLiberada();
	                break;
	            case KeyEvent.VK_A:
	                izquierda.teclaLiberada();
	                break;
	            case KeyEvent.VK_D:
	                derecha.teclaLiberada();
	                break;
	                case KeyEvent.VK_ENTER:
	                recogiendo = false;
	                break;
	            case KeyEvent.VK_SHIFT:
	                corriendo = false;
	                    break;
	        }
	    }

}

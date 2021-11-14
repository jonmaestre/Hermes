package hermesServices;

import java.util.ArrayList;

public class Usuarios {
	
	private final String[] users = {"MARIA"}; // Prueba de nombres
	
	public ArrayList<String> getUsersByName(String name) {
		ArrayList<String> res = new ArrayList<String>();
		if(name != null) {
			for(String user : users) {
				if(user.toUpperCase().contains(name.toUpperCase())) {
					res.add(user);
				}
			}
		}
		return res;
		
	}
	public ArrayList<String> getUsersByNames(String name1, String name2) {
		ArrayList<String> res = new ArrayList<String>();
		
		if(name1 != null) {
			for (String user : users) {
				if(user.toUpperCase().contains(name1.toUpperCase())) {
					res.add(user);
					
				}
			}
		}
		if(name2 != null) {
			for(String user : users) {
				if(user.toUpperCase().contains(name2.toUpperCase())) {
					res.add(user);
					
				}
			}
		}
		return null;
		
	}
}

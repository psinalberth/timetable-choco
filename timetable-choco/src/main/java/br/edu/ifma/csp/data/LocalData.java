package br.edu.ifma.csp.data;

import java.util.ArrayList;
import java.util.List;

public class LocalData {
	
	private static final List<String> locais = new ArrayList<String>();
	
	static {
		
		locais.add("Lab. 24");
		locais.add("Lab. 25");
		locais.add("Lab. 26");
		locais.add("Lab. 27");
		locais.add("Lab. Mult.");
		
		locais.add("Sala 15");
		locais.add("Sala 16");
		locais.add("Sala 17");
		locais.add("Sala 18");
		locais.add("Sala 19");
		locais.add("Sala 20");
		
	}
	
	public static String [] getLocais() {
		return locais.toArray(new String[locais.size()]);
	}
}

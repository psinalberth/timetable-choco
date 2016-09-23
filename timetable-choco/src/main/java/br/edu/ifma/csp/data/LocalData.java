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
	
//		locais.add("Sala 7");
		
		/*locais.add("Sala 11");
		locais.add("Sala 12");
		locais.add("Sala 13");
		locais.add("Sala 14");*/
		
		locais.add("Sala 15");
		locais.add("Sala 16");
		locais.add("Sala 17");
		locais.add("Sala 18");
		locais.add("Sala 19");
		
		locais.add("Sala 20");
		locais.add("Sala 21");
		locais.add("Sala 22");
		locais.add("Sala 23");
		locais.add("Sala 24");
		
		locais.add("Sala 25");
		locais.add("Sala 26");
		locais.add("Sala 27");
		locais.add("Sala 28");
		locais.add("Sala 29");
		
		locais.add("Sala 30");
		locais.add("Sala 31");
		locais.add("Sala 32");
		
		locais.add("Sala 4");
		locais.add("Sala 5");
		locais.add("Sala 6");
	}
	
	public static String [] getLocais() {
		return locais.toArray(new String[locais.size()]);
	}
}

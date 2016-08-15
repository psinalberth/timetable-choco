package br.edu.ifma.csp.data;

import java.util.ArrayList;
import java.util.List;

public class ProfessorData {
	
	private static final List<String> professores = new ArrayList<String>();
	
	static {
		
		professores.add("Josenildo");
		professores.add("Carla");
		professores.add("Omar");
		professores.add("Karla");
		professores.add("Eva");
		professores.add("Raimundo");
		professores.add("João");
		professores.add("Rayane");
		professores.add("Mauro");
		professores.add("Márcio");
		professores.add("Eveline");
		professores.add("Valdir");
		professores.add("Gentil");
		professores.add("Luís Carlos");
		professores.add("Angela");
	}
	
	public static String [] getProfessores() {
		return professores.toArray(new String[professores.size()]);
	}
}

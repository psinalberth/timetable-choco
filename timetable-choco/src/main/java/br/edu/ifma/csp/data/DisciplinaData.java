package br.edu.ifma.csp.data;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaData {
	
	private static final List<String> disciplinas = new ArrayList<String>();
	
	static {
		
		disciplinas.add("Cálculo I");
		disciplinas.add("Cálculo Vetorial");
		disciplinas.add("Filosofia");
		disciplinas.add("ICC");
		disciplinas.add("Metodologia Científica");
		
		disciplinas.add("Fundamentos de S.I");
		disciplinas.add("Inglês");
		disciplinas.add("LP I");
		disciplinas.add("Matemática Discreta");
		disciplinas.add("Org. Arq. Computadores");
		disciplinas.add("Prob. Estatística");
		
		disciplinas.add("Álgebra Linear");
		disciplinas.add("AED I");
		disciplinas.add( "LP II");
		disciplinas.add("SO I");
		disciplinas.add("Sociologia");
		
		disciplinas.add("AED II");
		disciplinas.add("BD I");
		disciplinas.add("ES I");
		disciplinas.add("IHC");
		disciplinas.add("Redes I");
		
		disciplinas.add("Análise I");
		disciplinas.add("BD II");
		disciplinas.add("Gestão e organização");
		disciplinas.add("Lab. BD");
		disciplinas.add("LP III");
		disciplinas.add("Redes II");
		
		disciplinas.add("Análise II");
		disciplinas.add("Ger. Projetos");
		disciplinas.add("IA");
		disciplinas.add("LP Web");
		
		disciplinas.add("Comp. Ética e Sociedade");
		disciplinas.add("IPO");
		disciplinas.add("Monografia I");
		
		disciplinas.add("Empreendedorismo");
	}
	
	public static String[] getDisciplinas() {
		return disciplinas.toArray(new String[disciplinas.size()]);
	}
}

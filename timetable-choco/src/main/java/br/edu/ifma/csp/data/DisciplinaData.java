package br.edu.ifma.csp.data;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaData {
	
	private static final List<String> disciplinas = new ArrayList<String>();
	
	static {
		
		disciplinas.add("Cálculo I");
		disciplinas.add("Cálculo Vet.");
		disciplinas.add("Filosofia");
		disciplinas.add("ICC");
		disciplinas.add("Met. Científica");
		
		disciplinas.add("Fund. de S.I");
		disciplinas.add("Inglês");
		disciplinas.add("LP I");
		disciplinas.add("Mat. Discreta");
		disciplinas.add("Org. Arq. Comp.");
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
		disciplinas.add("Gestão e Org.");
		disciplinas.add("Lab. BD");
		disciplinas.add("LP III");
		disciplinas.add("Redes II");
		
		disciplinas.add("Análise II");
		disciplinas.add("Ger. Projetos");
		disciplinas.add("IA");
		disciplinas.add("LP Web");
		
		disciplinas.add("Ética e Soc.");
		disciplinas.add("IPO");
		disciplinas.add("Monografia I");
		
		disciplinas.add("Empreendedorismo");
	}
	
	public static String[] getDisciplinas() {
		return disciplinas.toArray(new String[disciplinas.size()]);
	}
}

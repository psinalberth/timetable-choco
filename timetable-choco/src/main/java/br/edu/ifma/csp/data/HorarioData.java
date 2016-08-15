package br.edu.ifma.csp.data;

import java.util.ArrayList;
import java.util.List;

public class HorarioData {
	
	private static final List<String> horarios = new ArrayList<String>();
	
	static {
		
		horarios.add("SEG_1650");
		horarios.add("SEG_1740");
		horarios.add("SEG_1830");
		horarios.add("SEG_1830");
		horarios.add("SEG_2010");
		horarios.add("SEG_2100");
		horarios.add("SEG_2150");
		
		horarios.add("TER_1650");
		horarios.add("TER_1740");
		horarios.add("TER_1830");
		horarios.add("TER_1830");
		horarios.add("TER_2010");
		horarios.add("TER_2100");
		horarios.add("TER_2150");
		
		horarios.add("QUA_1650");
		horarios.add("QUA_1740");
		horarios.add("QUA_1830");
		horarios.add("QUA_1830");
		horarios.add("QUA_2010");
		horarios.add("QUA_2100");
		horarios.add("QUA_2150");
		
		horarios.add("QUI_1650");
		horarios.add("QUI_1740");
		horarios.add("QUI_1830");
		horarios.add("QUI_1830");
		horarios.add("QUI_2010");
		horarios.add("QUI_2100");
		horarios.add("QUI_2150");
		
		horarios.add("SEX_1650");
		horarios.add("SEX_1740");
		horarios.add("SEX_1830");
		horarios.add("SEX_1830");
		horarios.add("SEX_2010");
		horarios.add("SEX_2100");
		horarios.add("SEX_2150");
	}
	
	public static String [] getHorarios() {
		return horarios.toArray(new String[horarios.size()]);
	}
}

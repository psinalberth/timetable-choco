package br.edu.ifma.csp.data;

import java.util.ArrayList;
import java.util.List;

public class HorarioData {
	
	private static final List<String> horarios = new ArrayList<String>();
	
	static {
		
		horarios.add("SEG_15:10");
		horarios.add("SEG_16:00");
		horarios.add("SEG_16:50");
		horarios.add("SEG_17:40");
		horarios.add("SEG_18:30");
		horarios.add("SEG_19:20");
		horarios.add("SEG_20:10");
		horarios.add("SEG_21:00");
		horarios.add("SEG_21:50");
		
		horarios.add("TER_15:10");
		horarios.add("TER_16:00");
		horarios.add("TER_16:50");
		horarios.add("TER_17:40");
		horarios.add("TER_18:30");
		horarios.add("TER_19:20");
		horarios.add("TER_20:10");
		horarios.add("TER_21:00");
		horarios.add("TER_21:50");
		
		horarios.add("QUA_15:10");
		horarios.add("QUA_16:00");
		horarios.add("QUA_16:50");
		horarios.add("QUA_17:40");
		horarios.add("QUA_18:30");
		horarios.add("QUA_19:20");
		horarios.add("QUA_20:10");
		horarios.add("QUA_21:00");
		horarios.add("QUA_21:50");
		
		horarios.add("QUI_15:10");
		horarios.add("QUI_16:00");
		horarios.add("QUI_16:50");
		horarios.add("QUI_17:40");
		horarios.add("QUI_18:30");
		horarios.add("QUI_19:20");
		horarios.add("QUI_20:10");
		horarios.add("QUI_21:00");
		horarios.add("QUI_21:50");
		
		horarios.add("SEX_15:10");
		horarios.add("SEX_16:00");
		horarios.add("SEX_16:50");
		horarios.add("SEX_17:40");
		horarios.add("SEX_18:30");
		horarios.add("SEX_19:20");
		horarios.add("SEX_20:10");
		horarios.add("SEX_21:00");
		horarios.add("SEX_21:50");
	}
	
	public static String [] getHorarios() {
		return horarios.toArray(new String[horarios.size()]);
	}
}

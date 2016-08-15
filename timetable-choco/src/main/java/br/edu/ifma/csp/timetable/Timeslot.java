package br.edu.ifma.csp.timetable;

import java.util.ArrayList;
import java.util.List;

import org.chocosolver.solver.variables.IntVar;

public class Timeslot {
	
	private IntVar professor;
	private IntVar disciplina;
	private List<IntVar> horarios;
	
	public Timeslot() {
		this.horarios = new ArrayList<IntVar>();
	}
	
	public IntVar getProfessor() {
		return professor;
	}
	
	public IntVar getDisciplina() {
		return disciplina;
	}
	
	public List<IntVar> getHorarios() {
		return horarios;
	}
	
	public void addHorario(IntVar horario) {
		this.horarios.add(horario);
	}
}

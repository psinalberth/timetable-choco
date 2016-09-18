package br.edu.ifma.csp.timetable;

import java.util.ArrayList;
import java.util.List;

import org.chocosolver.solver.variables.IntVar;

public class Timeslot {
	
	private IntVar professor;
	private IntVar disciplina;
	private List<IntVar> horarios;
	private List<IntVar> locais;
	
	public Timeslot() {
		this.horarios = new ArrayList<IntVar>();
		this.locais = new ArrayList<IntVar>();
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
	
	public List<IntVar> getLocais() {
		return locais;
	}
	
	public void addHorario(IntVar horario) {
		this.horarios.add(horario);
	}
	
	public void addProfessor(IntVar professor) {
		this.professor = professor;
	}
	
	public void addDisciplina(IntVar disciplina) {
		this.disciplina = disciplina;
	}
	
	public void addLocal(IntVar local) {
		this.locais.add(local);
	}
}

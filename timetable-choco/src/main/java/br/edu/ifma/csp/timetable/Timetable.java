package br.edu.ifma.csp.timetable;

import java.util.ArrayList;
import java.util.List;

import org.chocosolver.samples.AbstractProblem;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.constraints.nary.alldifferent.AllDifferent;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;
import org.chocosolver.util.ESat;

import br.edu.ifma.csp.data.DisciplinaData;
import br.edu.ifma.csp.data.HorarioData;
import br.edu.ifma.csp.data.LocalData;
import br.edu.ifma.csp.data.ProfessorData;

public class Timetable extends AbstractProblem {
	
	int preferencias [][] = {
			
		new int [] {12, 24, 29, 32},
		new int [] {17, 21, 27},
		new int [] {13, 16, 29, 32},
		new int [] {3, 7, 18, 21, 27},
		new int [] {3, 5, 7, 12},
		new int [] {3, 7, 8, 13, 32},
		new int [] {22, 24, 25, 30},
		new int [] {0, 1, 10, 11},
		new int [] {13, 25, 28, 30},
		new int [] {9, 14, 20, 26},
		new int [] {3, 7, 19, 21, 27},
		new int [] {2, 4, 15},
		new int [] {12, 15, 31},
		new int [] {6, 33},
		new int [] {23, 34}
	};
	
	int [] aulas = {
			
		6, 4, 4, 6, 4, 
	    4, 4, 4, 4, 4, 4,
	    4, 6, 6, 4, 4,
	    4, 4, 4, 4, 4,
	    4, 4, 4, 4, 4, 4,
	    6, 4, 4, 6,
	    4, 6, 4,
        4
    };
	
	int periodos [][] = {
		
		new int [] {0, 1, 2, 3, 4},
		new int [] {5, 6, 7, 8, 9, 10},
		new int [] {11, 12, 13, 14, 15},
		new int [] {16, 17, 18, 19, 20},
		new int [] {21, 22, 23, 24, 25, 26},
		new int [] {27, 28, 29, 30},
		new int [] {31, 32, 33},
		new int [] {34},
	};
	
	IntVar [] disciplinas;
	IntVar [] professores;
	IntVar [] horarios;
	IntVar [] locais;
	IntVar [][] horariosPeriodo;
	IntVar [][] horariosProfessor;
	IntVar [][] horariosDisciplina;
	IntVar [][] horariosLocal;
	List<Timeslot> timeslots;

	@Override
	public void buildModel() {
		
		timeslots = new ArrayList<Timeslot>();
		
		disciplinas = new IntVar[DisciplinaData.getDisciplinas().length];
		professores = new IntVar[DisciplinaData.getDisciplinas().length];
		horarios = new IntVar[HorarioData.getHorarios().length];
		locais = new IntVar[LocalData.getLocais().length];
		
		horariosDisciplina = new IntVar[disciplinas.length][];
		horariosProfessor = new IntVar[disciplinas.length][];
		
		for (int i = 0; i < disciplinas.length; i++) {
			
			Timeslot timeslot = new Timeslot();
			
			disciplinas[i] = VF.bounded("D" + (i+1), 0, disciplinas.length - 1, solver);
			timeslot.addDisciplina(disciplinas[i]);
			
			professores[i] = VF.bounded("P" + (i+1), 0, professores.length - 1, solver);
			timeslot.addProfessor(professores[i]);
			
			horariosDisciplina[i] = new IntVar[aulas[i]];
			 
			
			for (int j = 0; j < aulas[i]; j++) {
				
				IntVar horario = VF.bounded(disciplinas[i].getName() + "_" + "H" + (j+1), 0, horarios.length - 1, solver);
				timeslot.addHorario(horario);
				
				horariosDisciplina[i][j] = horario;
			}
			
			solver.post(new AllDifferent(timeslot.getHorarios().toArray(new IntVar[timeslot.getHorarios().size()]), "NEQS"));
			
			timeslots.add(timeslot);	
		}
		
		manterDisciplinasComOfertaUnicaConstraint();
		
		manterDisciplinasOrdenadasConstraint();
		
		manterHorariosOrdenadosConstraint();
		
		manterProfessorQuePossuiPreferenciaConstraint();
		
		manterDisciplinasComHorarioUnicoConstraint();
		
		manterDisciplinasComHorarioUnicoConstraint();
		
		for (int j = 0; j < horariosDisciplina.length; j++) {
			solver.post(new AllDifferent(horariosDisciplina[j], "NEQS"));
		}
	}

	private void manterDisciplinasComHorarioUnicoConstraint() {
		
		horariosPeriodo = new IntVar[periodos.length][];
		
		for (int i = 0, index = 0; i < periodos.length; i++) {
			
			List<IntVar> horarios = new ArrayList<IntVar>();
			
			for (int j = index; j < index + aulas[index] - 1; j++) {
				horarios.addAll(timeslots.get(j).getHorarios());
			}
			
			horariosPeriodo[i] = horarios.toArray(new IntVar[horarios.size()]);
			solver.post(new AllDifferent(horariosPeriodo[i], "NEQS"));
			index += aulas[index];
		}		
	}

	private void manterHorariosOrdenadosConstraint() {
		
		for (int i = 0; i < horariosDisciplina.length; i++) {
		
			for (int j = 0; j < horariosDisciplina[i].length; j++) {
				
				IntVar d1 = horariosDisciplina[i][j];
				IntVar d2 = null;
				
				if ((j+1) < horariosDisciplina[i].length) {
					
					d2 = horariosDisciplina[i][j+1];
					
					solver.post(IntConstraintFactory.arithm(d1, "<" , d2));
				}
			}
		}
	}

	private void manterProfessorQuePossuiPreferenciaConstraint() {
		
		for (int i = 0; i < professores.length; i++) {	
			solver.post(IntConstraintFactory.member(professores[i], getProfessoresByDisciplina(i)));
		}
	}

	private void manterDisciplinasComOfertaUnicaConstraint() {
		solver.post(new AllDifferent(disciplinas, "DEFAULT"));
	}

	private void manterDisciplinasOrdenadasConstraint() {
		
		for (int i = 0; i < disciplinas.length; i++) {
			
			IntVar d1 = disciplinas[i];
			IntVar d2 = null;
			
			if ((i+1) < disciplinas.length) {
				
				d2 = disciplinas[i+1];
				
				solver.post(IntConstraintFactory.arithm(d1, "<" , d2));
			}
		}
	}
	
	private int [] getDisciplinasPorPeriodo(int periodo) {
		return periodos[periodo];
	}
	
	@SuppressWarnings("unused")
	private int getPeriodoDisciplina(int disciplina) {
		
		for (int i = 0; i < periodos.length; i++) {
			
			for (int j = 0; j < periodos[i].length; j++) {
				
				if (periodos[i][j] == disciplina)
					return i;
			}
		}
		
		return -1;
	}
	
	@SuppressWarnings("unused")
	private IntVar [] getHorariosPeriodo(int periodo) {
		
		List<IntVar> slots = new ArrayList<IntVar>();
		
		int disciplinas [] = getDisciplinasPorPeriodo(periodo);
		
		for (int i = 0; i < disciplinas.length; i++) {
		
			for (Timeslot timeslot : timeslots) {
				
				if (timeslot.getDisciplina().getValue() == disciplinas[i]) {
					slots.addAll(timeslot.getHorarios());
				}
			}
		}
		
		return slots.toArray(new IntVar[slots.size()]);
	}
	
	@SuppressWarnings("unused")
	private List<IntVar> getTimeslotsProfessor(int professor) {
		
		List<IntVar> slots = new ArrayList<IntVar>();
		
		for (Timeslot timeslot : timeslots) {
			
			if (timeslot.getProfessor().getValue() == professor) {
				
				slots.addAll(timeslot.getHorarios());
			}
		}
		
		return slots;
	}

	@Override
	public void configureSearch() {
//		solver.set(IntStrategyFactory.lastConflict(solver));
	}

	@Override
	public void createSolver() {
		solver = new Solver("Timetable");
	}

	@Override
	public void prettyOut() {
		
		int ic = 0;
		
		if (solver.isFeasible() == ESat.TRUE) {
			
			do {
				
				ic += 1;
				
				for (int i = 0; i < disciplinas.length; i++) {
					
					System.out.print(disciplinas[i].getName() + " = " + DisciplinaData.getDisciplinas()[disciplinas[i].getValue()] + ", ");
					System.out.println(professores[i].getName() + " = " + ProfessorData.getProfessores()[professores[i].getValue()]);
					
					for (int j = 0; j < aulas[i]; j++) {
						
						System.out.println(horariosDisciplina[i][j].getName() + " = " + HorarioData.getHorarios()[horariosDisciplina[i][j].getValue()]);
					}
				}
				
			} while (solver.nextSolution() == Boolean.TRUE && ic < 3);
			
		} else {
			
			System.out.println("No solution.");
		}
	}

	@Override
	public void solve() {
		solver.findSolution();
	}
	
	public int [] getDisciplinasByProfessor(int professor) {
		return preferencias[professor];
	}
	
	public int [] getProfessoresByDisciplina(int disciplina) {
		
		List<Integer> lista = new ArrayList<Integer>();
		
		for (int i = 0; i < preferencias.length; i++) {
			
			for (int j = 0; j < preferencias[i].length; j++) {
				
				if (preferencias[i][j] == disciplina)
					lista.add(i);
			}
		}
				
		int [] array = new int[lista.size()];
		
		for (int i = 0; i < lista.size(); i++) {
			array[i] = lista.get(i);
		}
		
		return array;
	}
	
	public static void main(String[] args) {
		new Timetable().execute(args);
	}
}

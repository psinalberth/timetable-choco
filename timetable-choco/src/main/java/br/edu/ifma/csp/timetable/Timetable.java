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
	
	int [] aulas = {6, 4, 4, 6, 4, 
            4, 4, 4, 4, 4, 4,
            4, 6, 6, 4, 4,
            4, 4, 4, 4, 4,
            4, 4, 4, 4, 4, 4,
            6, 4, 4, 6,
            4, 6, 4,
            4};
	
	IntVar [] disciplinas;
	IntVar [] professores;
	IntVar [] horarios;
	IntVar [] locais;
	IntVar [][] horariosProfessor;
	IntVar [][] horariosDisciplina;
	IntVar [][] horatiosLocal;
	List<Timeslot> timeslots;

	@Override
	public void buildModel() {
		
		timeslots = new ArrayList<Timeslot>();
		
		disciplinas = new IntVar[DisciplinaData.getDisciplinas().length];
		professores = new IntVar[DisciplinaData.getDisciplinas().length];
		horarios = new IntVar[HorarioData.getHorarios().length];
		locais = new IntVar[LocalData.getLocais().length];
		
		horariosDisciplina = new IntVar[disciplinas.length][];
		
		for (int i = 0; i < disciplinas.length; i++) {
			
			disciplinas[i] = VF.bounded("D" + (i+1), 0, disciplinas.length - 1, solver);
			professores[i] = VF.bounded("P" + (i+1), 0, professores.length - 1, solver);
			
			horariosDisciplina[i] = new IntVar[aulas[i]];
			
			for (int j = 0; j < aulas[i]; j++) {
				horariosDisciplina[i][j] = VF.bounded("H" + (j+1) + "_" + disciplinas[i].getName(), 0, horarios.length, solver);
			}
			
			solver.post(new AllDifferent(horariosDisciplina[i], "DEFAULT"));
		}
		
		manterDisciplinasComOfertaUnicaConstraint();
		
		manterDisciplinasOrdenadasConstraint();
		
		manterProfessorQuePossuiPreferenciaConstraint();
		
		manterProfessoresComHorarioUnicoConstraint();
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
	
	private void manterProfessoresComHorarioUnicoConstraint() {
		
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
		
		if (solver.isFeasible() == ESat.TRUE) {
			
			do {
				
				/*for (int i = 0; i < disciplinas.length; i++) {
					System.out.println(disciplinas[i].getValue() + ", " + professores[i].getValue());
				}*/
				
			} while (solver.nextSolution() == Boolean.TRUE);
			
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

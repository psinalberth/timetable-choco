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
	
	IntVar [] disciplinas;
	IntVar [] professores;
	IntVar [] horarios;
	IntVar [] locais;
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
		
		for (int i = 0; i < disciplinas.length; i++) {
			
			Timeslot timeslot = new Timeslot();
			
			disciplinas[i] = VF.bounded("D" + (i+1), 0, disciplinas.length - 1, solver);
			timeslot.addDisciplina(disciplinas[i]);
			
			professores[i] = VF.bounded("P" + (i+1), 0, professores.length - 1, solver);
			timeslot.addProfessor(professores[i]);
			
			for (int j = 0; j < aulas[i]; j++) {
				
				IntVar horario = VF.bounded(disciplinas[i].getName() + "_" + "H" + (j+1), 0, horarios.length - 1, solver);
				timeslot.addHorario(horario);
			}
			
			timeslots.add(timeslot);
		}
		
		manterDisciplinasComOfertaUnicaConstraint();
		
		manterDisciplinasOrdenadasConstraint();
		
		manterProfessorQuePossuiPreferenciaConstraint();
		
		manterProfessoresComHorarioUnicoConstraint();
		
		for (int i = 0; i < professores.length; i++) {
			
			List<IntVar> horarios = getTimeslotsProfessor(professores[i]);
			
			if (horarios.size() > 0) {
				solver.post(new AllDifferent(horarios.toArray(new IntVar[horarios.size()]), "DEFAULT"));
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
	
	private void manterProfessoresComHorarioUnicoConstraint() {
		
	}
	
	private List<IntVar> getTimeslotsProfessor(IntVar professor) {
		
		List<IntVar> slots = new ArrayList<IntVar>();
		
		for (Timeslot timeslot : timeslots) {
			
			if (timeslot.getProfessor().equals(professor)) {
				
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
		
		if (solver.isFeasible() == ESat.TRUE) {
			
			do {
				
				for (int i = 0; i < disciplinas.length; i++) {
					
					System.out.print(disciplinas[i].getName() + " = " + DisciplinaData.getDisciplinas()[disciplinas[i].getValue()] + ", ");
					System.out.println(professores[i].getName() + " = " + ProfessorData.getProfessores()[professores[i].getValue()]);
					
					for (int j = 0; j < aulas[i]; j++) {
						
//						System.out.println(horarios[j].getValue());
					}
				}
				
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

package br.edu.ifma.csp.propagator;

import java.util.ArrayList;
import java.util.List;

import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.ESat;

import br.edu.ifma.csp.timetable.Timeslot;

public class HorarioPropagator extends Propagator<IntVar>{

	private static final long serialVersionUID = -4832783941146149194L;
	
	IntVar professor;
	List<Timeslot> timeslots;
	
	public HorarioPropagator(IntVar professor, List<Timeslot> timeslots) {
		
		super(professor);
		
		this.professor = professor;
		this.timeslots = timeslots;
	}

	@Override
	public void propagate(int evtmask) throws ContradictionException {
		//this.professor.updateUpperBound(this.professor.getUB(), this);
	}
	
	public List<IntVar> getHorarios() {
		
		List<IntVar> horarios = new ArrayList<IntVar>();
		
		for (Timeslot slot : timeslots) {
			
			if (this.professor.getValue() == slot.getProfessor().getValue()) {
				horarios.addAll(slot.getHorarios());
			}
		}
		
		return horarios;
	}

	@Override
	public ESat isEntailed() {
		
		List<IntVar> horarios = getHorarios();
		System.out.println(horarios);
		
		return ESat.TRUE;
	}

}

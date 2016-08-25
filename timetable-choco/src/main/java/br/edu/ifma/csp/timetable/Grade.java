package br.edu.ifma.csp.timetable;

public class Grade {
	
	private String[][] grade;
	
	public Grade(int dias, int horarios) {
		
		this.grade = new String[horarios][dias];
	}
	
	public String[][] getGrade() {
		return grade;
	}
}

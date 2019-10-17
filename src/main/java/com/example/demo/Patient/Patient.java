package com.example.demo.Patient;

public class Patient {
	
	private int patientID;
	private String firstName;
	private String lastName;
	private long averageTimeTaken;
	private int gaitSpeed;
	private String Note;
	
	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public long getAverageTimeTaken() {
		return averageTimeTaken;
	}
	
	public void setAverageTimeTaken(long averageTimeTaken) {
		this.averageTimeTaken = averageTimeTaken;
	}
	
	public int getGaitSpeed() {
		return gaitSpeed;
	}
	
	public void setGaitSpeed(int gaitSpeed) {
		this.gaitSpeed = gaitSpeed;
	}
	
	public String getNote() {
		return Note;
	}
	
	public void setNote(String note) {
		Note = note;
	}
	
	

}

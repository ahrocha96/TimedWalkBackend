package com.example.demo.Patient;

import java.util.List;

public interface IPatientSchema {

	public Patient save(Patient patient);
	
	public Patient update(Patient patient);
	
	public Patient findByPatientID(int patientID);
	
	List<Patient> findAllPatients();
	
	public Patient remove(int patientID);

	public int getTopID();

	public void deleteAll();


	
}

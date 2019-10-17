package com.example.demo.Patient;

import java.util.HashMap;
import java.util.List;

import com.example.demo.exceptions.FakeDbException;

public class FakePatientSchema implements IPatientSchema{
	
	
	HashMap<Integer, Patient> patientSchema;
	private int topID = 1;

	public FakePatientSchema() {
		patientSchema = new HashMap<Integer, Patient>();
	}
	
	@Override
	public Patient save(Patient patient){
		
		if(patientSchema.get(patient.getPatientID()) == null){
			patientSchema.put(patient.getPatientID(), patient);
		}
		else{
			throw new FakeDbException("User already exists in the database!");
		}
		return patient;
	}
	
	@Override
	public Patient update(Patient patient){
		
		Patient retrievedPatient = patientSchema.get(patient.getPatientID());
		
		retrievedPatient.setAverageTimeTaken(patient.getAverageTimeTaken());
		retrievedPatient.setGaitSpeed(patient.getGaitSpeed());
		retrievedPatient.setNote(patient.getNote());
		
		return retrievedPatient;
	}
	
	@Override
	public Patient findByPatientID(int patientID){
		
		Patient foundPatient = null;
		
		if(patientSchema.get(patientID) != null){
			foundPatient = patientSchema.get(patientID);
		}
		else{
			throw new FakeDbException("User doesn't exist");
		}
		
		return foundPatient;
	}
	
	@Override
	public int getTopID(){
		topID++;
		return topID;
	}

	@Override
	public void deleteAll() {
		return;
	}

	@Override
	public List<Patient> findAllPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient remove(int patientID) {
		// TODO Auto-generated method stub
		return null;
	}


}

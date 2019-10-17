package com.example.demo.Patient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.demo.Counter.Counter;
import com.example.demo.Patient.Patient;
import com.example.demo.exceptions.DbException;

public class PatientSchema implements IPatientSchema {
   
	private MongoOperations mongoOperations;

	public PatientSchema(MongoOperations mongoOperations) {
		super();
		this.mongoOperations = mongoOperations;
	}
	
	
	@Override
	public Patient save(Patient patient) {

		Patient savedPatient = null;
		
		Query query = new Query();
		Update update = new Update();
		FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
		
		patient.setPatientID(getTopID());
		
		query.addCriteria(Criteria.where("PatientID").is(patient.getPatientID()));

		update.setOnInsert("PatientID", patient.getPatientID());
		update.setOnInsert("FirstName", patient.getFirstName());
		update.setOnInsert("LastName", patient.getLastName());
		update.setOnInsert("AverageTimeTaken", patient.getAverageTimeTaken());
		update.setOnInsert("GaitSpeed", patient.getGaitSpeed());
		update.setOnInsert("Note", patient.getNote());
		
		findAndModifyOptions.upsert(true);
		findAndModifyOptions.returnNew(true);

		if(findByPatientID(patient.getPatientID()) == null){
			savedPatient = mongoOperations.findAndModify(query, update, findAndModifyOptions, Patient.class, "Patient");
		}
		
		return savedPatient;
	}

	@Override
	public Patient update(Patient patient) {
		
		Patient updatedPatient = null;
		
		Query query = new Query();
		Update update = new Update();
		FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
		
		query.addCriteria(Criteria.where("PatientID").is(patient.getPatientID()));
		
		update.set("AverageTimeTaken", patient.getAverageTimeTaken());
		update.set("GaitSpeed", patient.getGaitSpeed());
		update.set("Note", patient.getNote());
		
		findAndModifyOptions.upsert(true);
		findAndModifyOptions.returnNew(true);
		
		if(findByPatientID(patient.getPatientID()) != null){
			updatedPatient = mongoOperations.findAndModify(query, update, findAndModifyOptions, Patient.class, "Patient");
		}
		return updatedPatient;
	}
	
	public Patient remove(int patientID){
		
		Patient removedPatient = null;
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("PatientID").is(patientID));
		
		if(findByPatientID(patientID) != null){
			removedPatient = mongoOperations.findAndRemove(query, Patient.class, "Patient");
		}
		
		return removedPatient;
	}


	@Override
	public Patient findByPatientID(int patientID) {
		
		Patient foundPatient = null;
		
		Query query = new Query();
		query.addCriteria(Criteria.where("PatientID").is(patientID));

		foundPatient = mongoOperations.findOne(query, Patient.class, "Patient");

		return foundPatient;

	}
	
	@Override
	public List<Patient> findAllPatients(){
		
		List<Patient> patientList = new ArrayList<Patient>();
		
		
		Query query = new Query();
		query.addCriteria(Criteria.where("PatientID").exists(true));
		
		patientList = mongoOperations.find(query, Patient.class, "Patient");
		
		return patientList;
	}
	
	@Override
	public int getTopID() {
	
		Query query = new Query();
  	  	query.addCriteria(Criteria.where("_id").is("Patient"));
		
  	  	Update update = new Update();
 
  	  	FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
  	    findAndModifyOptions.upsert(true);
  	    findAndModifyOptions.returnNew(true);
  	  
  	  	Counter counter = mongoOperations.findAndModify(query, update.inc("seq", 1), findAndModifyOptions, Counter.class, "PatientCounter");
  	  	
        return counter.getSeq();
        
    }


	@Override
	public void deleteAll() {
		
    	mongoOperations.remove(new Query(), Patient.class, "Patient");
    	
	}

}

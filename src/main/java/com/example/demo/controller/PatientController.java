package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import javax.annotation.PostConstruct;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Patient.Patient;
import com.example.demo.Patient.PatientSchema;
import com.example.demo.config.CustomMongoDbConfig;
import com.example.demo.exceptions.DbException;

@RestController
@RequestMapping("/Patient")
public class PatientController {
	
	private MongoOperations mongoOperations;
	PatientSchema patientSchema;
	
	@PostConstruct
	public void init(){
		mongoOperations = CustomMongoDbConfig.mongoTemplate();
		patientSchema =  new PatientSchema(mongoOperations);
	}
	
    @GetMapping
    public @ResponseBody List<Patient> getAllPatients() {
        return patientSchema.findAllPatients();
    }
    
    @GetMapping("/{id}")
    public @ResponseBody Patient getPatient(@PathVariable(value = "id") int patientID){
    	return patientSchema.findByPatientID(patientID);
    }
    
    @PostMapping
    public @ResponseBody Patient postPatient(@RequestBody Patient patient) {
        return patientSchema.save(patient);
    }
    
    @PutMapping
    public @ResponseBody Patient updatePatient(@RequestBody Patient patient){
    	return patientSchema.update(patient);
    }
    
    @DeleteMapping("/{id}")
    public @ResponseBody Patient delete(@PathVariable(value = "id") int patientID) {
    	return patientSchema.remove(patientID);
    }
}

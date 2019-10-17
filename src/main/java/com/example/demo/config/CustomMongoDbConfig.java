package com.example.demo.config;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;

public class CustomMongoDbConfig {

	static public MongoTemplate mongoTemplate() {
		
		MongoClientURI uri = new MongoClientURI("mongodb://andrew:password1@ds231207.mlab.com:31207/patientwalktest");
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(uri);
		return new MongoTemplate(mongoDbFactory);
		
	}
	
}

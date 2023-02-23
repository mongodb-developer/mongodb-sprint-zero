package com.example.ms0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ms0.repository.ThingRepository;
import com.example.ms0.repository.CustomThingRepository;
import com.example.ms0.model.Thing;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.bson.Document;

import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication
public class Ms0Application implements CommandLineRunner {

	@Autowired
	ThingRepository thingRepo;

	@Autowired
    CustomThingRepository customRepo;

	@Autowired
    private MongoOperations mongoOperations;

	public static void main(String[] args) {
		SpringApplication.run(Ms0Application.class, args).close();
	}

	public void run(String... args) {

		/* ~~~~~   List of operations   ~~~~~ */

		if (Arrays.asList(args).contains("insert")) {
			System.out.println("Inserting a Thing...\n");
			thingRepo.save(new Thing(1, true, LocalDateTime.now()));
		}

		if (Arrays.asList(args).contains("findIt")) {
			System.out.println("Finding Things...\n");
			thingRepo.findAll().forEach(thing -> System.out.println(getThingDetails(thing)));
		}

		if (Arrays.asList(args).contains("updateMatched")) {
			int a = 1;
			System.out.println("Unsetting 'example' field where a = " + a);
			customRepo.unsetExample(a);
		}

		if (Arrays.asList(args).contains("replaceMatched")) {
			int a = 1;
			System.out.println("Replacing a Thing...\n");
			customRepo.replaceMatched(a);
		}

		if (Arrays.asList(args).contains("upsert")) {
			int a = 2;
			System.out.println("Upserting a Thing...\n");
			customRepo.upsert(a);
		}


		if (Arrays.asList(args).contains("deleteAll")) {
			System.out.println("Deleting all Things...\n");
			thingRepo.deleteAll();
		}

		if (Arrays.asList(args).contains("currentOp")) {
			Document command = new Document("currentOp", true);
			Document result = mongoOperations.executeCommand(command);
			System.out.println(result.toJson());
		}
	}

	// Print details in readable form
	public String getThingDetails(Thing thing) {
		System.out.println(
			"a: " + thing.getA() +
			", \nexample: " + thing.isExample() +
			", \ntime: " + thing.getTime());
		return "";
	}
}

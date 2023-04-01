package co.mch.projects.restapi.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * API Rest CRUD Spring Boot application.
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
@SpringBootApplication
public class TodolistApplication {
    /*
     * main() method is entry point of the program.
     * JVM searches for main() method
     */
	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}

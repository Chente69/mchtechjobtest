package co.mch.projects.restapi.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * API Rest CRUD Spring Boot application.
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */

//@SpringBootApplication(scanBasePackages ={"co.mch.projects.restapi.todolist.daljpa.controllers","co.mch.projects.restapi.todolist.dalbusiness.services","co.mch.projects.restapi.todolist.daljpa.model", "co.mch.projects.restapi.todolist.daljpa.repositories"})

@SpringBootApplication
@EnableJpaRepositories("co.mch.projects.restapi.todolist.daljpa.repositories")
public class TodolistApplication {
    /*
     * main() method is entry point of the program.
     * JVM searches for main() method
     */
	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}

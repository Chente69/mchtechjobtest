package co.mch.projects.restapi.todolist.daljpa.model;
import javax.persistence.*;

/**
 * API Rest CRUD Spring Boot application.
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */

@Entity
@Table(name = "todos")
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; // Todo unique identifier
    private String task; // Task description
    private boolean completed; // indicates if one task is completed
    
    // Constructor methods
	public Todo() {
		// TODO Auto-generated constructor stub
	}

	public Todo(String task, boolean completed) {
		super();
		this.task = task;
		this.completed = completed;
	}
	
   // Properties getters and setters methods
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	// Method to print the Todo properties
	@Override
	public String toString() {
		return "Todo [id=" + id + ", task=" + task  + ", completed=" + completed + "]";
	}	

}

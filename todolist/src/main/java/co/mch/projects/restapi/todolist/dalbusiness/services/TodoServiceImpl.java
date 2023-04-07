package co.mch.projects.restapi.todolist.dalbusiness.services;
import co.mch.projects.restapi.todolist.dalbusiness.exceptions.ResourceNotFoundException;
import co.mch.projects.restapi.todolist.daljpa.model.Todo;
import co.mch.projects.restapi.todolist.daljpa.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service that implements TodoService Interface uses for testing TodoRepository
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
@Service
public class TodoServiceImpl implements TodoService{
	
	private TodoRepository todoRepository;
	
	
    // Inject repo dependency 
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
    // Method to save one entity (todo) in table todos
	@Override
	public Todo saveTodo(Todo todo) {
		Optional<Todo> saveTodo = todoRepository.findByTask(todo.getTask());
        if(saveTodo.isPresent()){
            throw new ResourceNotFoundException("Todo already exist with given task:" + todo.getTask());
        }
        return todoRepository.save(todo);
	}

	 // Method to query all entities (Todo) from table todos
	@Override
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}
    
	// Method to query one entity (Todo) from table todos
	// given the identifier for the row table
	@Override
	public Optional<Todo> getTodoById(long id) {
		return todoRepository.findById(id);
	}

	// Method to update one entity (Todo) from table todos
	// given the todo with the fields to update
	@Override
	public Todo updateTodo(Todo updateTodo) {
        return todoRepository.save(updateTodo);
	}

	// Method to delete one entity (Todo) from table todos
	// given the identifier for the row table to be removed
	@Override
	public void deleteTodo(long id) {
		todoRepository.deleteById(id);
		
	}

}

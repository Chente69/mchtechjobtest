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
	

	@Override
	public Todo saveTodo(Todo todo) {
		Optional<Todo> saveTodo = todoRepository.findByTask(todo.getTask());
        if(saveTodo.isPresent()){
            throw new ResourceNotFoundException("Todo already exist with given task:" + todo.getTask());
        }
        return todoRepository.save(todo);
	}

	@Override
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}

	@Override
	public Optional<Todo> getTodoById(long id) {
		return todoRepository.findById(id);
	}

	@Override
	public Todo updateTodo(Todo updateTodo) {
        return todoRepository.save(updateTodo);
	}

	@Override
	public void deleteTodo(long id) {
		todoRepository.deleteById(id);
		
	}

}

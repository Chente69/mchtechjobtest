package co.mch.projects.restapi.todolist.dalbusiness.services;
import co.mch.projects.restapi.todolist.daljpa.model.Todo;
import java.util.List;
import java.util.Optional;
/**
 * Interface with CRUD methods uses for testing TodoRepository .
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
public interface TodoService {
	Todo saveTodo(Todo todo);
	List<Todo> getAllTodos();
	Optional<Todo> getTodoById(long id);
	Todo updateTodo(Todo updateTodo);
	void deleteTodo(long id);
}

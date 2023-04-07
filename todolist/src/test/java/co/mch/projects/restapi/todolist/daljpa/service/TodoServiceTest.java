package co.mch.projects.restapi.todolist.daljpa.service;
import co.mch.projects.restapi.todolist.dalbusiness.services.TodoServiceImpl;
import co.mch.projects.restapi.todolist.daljpa.model.Todo;
import co.mch.projects.restapi.todolist.daljpa.repositories.TodoRepository;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
	@InjectMocks
	TodoServiceImpl todoService;
	
	@Mock
	TodoRepository repository;
	
    @Mock
    private ModelMapper mapper;
    
    @Test
    void testCreatingSuccess() {
        String task = "Task one";
        boolean isCompleted = false;
        
        Todo todo = new Todo("Task one",false);
        Mockito.when(repository.save(Mockito.any(Todo.class))).thenReturn(todo);

        todoService.saveTodo(todo);

        ArgumentCaptor<Todo> newTodo = ArgumentCaptor.forClass(Todo.class);
        Mockito.verify(repository).save(newTodo.capture());

        assertEquals(task, newTodo.getValue().getTask());
        assertEquals(isCompleted, newTodo.getValue().isCompleted());

    }
    
    @Test
    void testGetAll() {
        String title1 = "Task 2";
        boolean isCompleted1 = false;
        String title2 = "Task 2";
        boolean isCompleted2 = false;
        

        Todo todo1= new Todo(title1,isCompleted1);
        Todo todo2=  new Todo(title2,isCompleted2); 
        
        List<Todo> todoList = Lists.newArrayList(todo1, todo2);
        Mockito.when(repository.findAll()).thenReturn(todoList);

        todoService.getAllTodos();

        Mockito.verify(repository).findAll();

    }
    
    @Test
    void testFindByIdSuccess() {
        long id = 1L;
        String title1 = "Task 1";
        boolean isCompleted1 = false;
        
        Todo todo= new Todo(title1,isCompleted1);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(todo));
        todoService.getTodoById(id);
        Mockito.verify(repository).findById(id);

    }
    
    @Test
    void testUpdateTodoByIdSuccess() {
        long id = 1L;
        String task = "Task 1";
        boolean isNotCompleted = false;
        boolean isCompleted = true;
        
        Todo todo= new Todo(task,isNotCompleted);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(todo));
        todoService.getTodoById(id);
        Mockito.verify(repository).findById(id);   
        
        todo.setCompleted(isCompleted);
        Mockito.when(repository.save(Mockito.any(Todo.class))).thenReturn(todo);
        todoService.updateTodo(todo);
        
        ArgumentCaptor<Todo> updateTodo = ArgumentCaptor.forClass(Todo.class);
        Mockito.verify(repository).save(updateTodo.capture());

        assertEquals(task, updateTodo.getValue().getTask());
        assertEquals(isCompleted, updateTodo.getValue().isCompleted());
    }
    
    void testdeleteTodoByIdSuccess() {
        long id = 1L;
        String title1 = "Task 1";
        boolean isCompleted1 = false;
        
        Todo todo= new Todo(title1,isCompleted1);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(todo));
        todoService.deleteTodo(id);
         
    }
    
}

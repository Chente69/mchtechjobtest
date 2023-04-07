package co.mch.projects.restapi.todolist.daljpa.controller;

import co.mch.projects.restapi.todolist.ApplicationConfig;
import co.mch.projects.restapi.todolist.dalbusiness.services.TodoServiceImpl;
import co.mch.projects.restapi.todolist.daljpa.model.Todo;
import co.mch.projects.restapi.todolist.daljpa.repositories.TodoRepository;
import co.mch.projects.restapi.todolist.daljpa.service.TodoServiceTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class TodoContollerTest {
    private static final String TODO_PATH = "/api/todos";

    @Autowired
    private MockMvc mockMvc;
    
	@InjectMocks
	TodoServiceImpl todoService;
	
	@Mock
	TodoRepository repository;
    
    @Autowired
    private ObjectMapper objectMapper;	
   
    
    @Test
    void shouldFetchAllTodos() throws Exception {
        this.mockMvc.perform(get(TODO_PATH)).andExpect(status().is(200));
    }
    
    @Test
    void shouldFetchOneTodoById() throws Exception {
        long id = 1L;
    	Todo resource = new Todo("Task one",false);

        this.mockMvc.perform(post(TODO_PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.task", Is.is(resource.getTask())));
        
        this.mockMvc.perform(get(TODO_PATH + "/{id}", id))
                .andExpect(status().isOk()); 
    }    
    
    @Test
    void shouldCreateNewTodo() throws Exception {
    	Todo resource = new Todo("Task one",false);

        this.mockMvc.perform(post(TODO_PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.task", Is.is(resource.getTask())));
        this.mockMvc.perform(get(TODO_PATH)).andExpect(status().isOk());
    }
    
    
    // JUnit test for update todo REST API - positive scenario
    @Test
    public void givenUpdatedTodo_whenUpdateTodo_thenReturnUpdateTodoObject() throws Exception{
        // given - precondition or setup
        long todoId = 1L;
        String task = "Task one";
        boolean isCompleted = false;
        Todo todo= new Todo(task,isCompleted);
        
        String updateTask = "Task 1";
        boolean updaeteIsCompleted = true;
        Todo updateTodo= new Todo(updateTask,updaeteIsCompleted);
        
        this.mockMvc.perform(post(TODO_PATH).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todo)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.task", Is.is(todo.getTask())));
        this.mockMvc.perform(get(TODO_PATH)).andExpect(status().isOk());

        
        ResultActions response = this.mockMvc.perform(put(TODO_PATH + "/{id}",todoId).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateTodo)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
       
        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.task", Is.is(updateTodo.getTask())))
                .andExpect(jsonPath("$.completed", Is.is(updateTodo.isCompleted())));       
    }


}

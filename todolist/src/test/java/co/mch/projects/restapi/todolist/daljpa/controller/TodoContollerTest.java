package co.mch.projects.restapi.todolist.daljpa.controller;

import co.mch.projects.restapi.todolist.ApplicationConfig;
import co.mch.projects.restapi.todolist.daljpa.model.Todo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class TodoContollerTest {
    private static final String TODO_PATH = "/api/todos";

    @Autowired
    private MockMvc mockMvc;
        
    @Autowired
    private ObjectMapper objectMapper;	
   
 // JUnit test for Get All Todos REST API
    @Test
    void shouldFetchAllTodos() throws Exception {
        this.mockMvc.perform(get(TODO_PATH)).andExpect(status().is(200));
    }
    
 // JUnit test for Get  REST API ( Queries one todo by Id)
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
    
 // JUnit test for Post REST API ( Creates a new todo)
    @Test
    void shouldCreateNewTodo() throws Exception {
    	// given - precondition or setup
    	Todo resource = new Todo("Task one",false);
    	// when - action or behaviour that we are going test
        this.mockMvc.perform(post(TODO_PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.task", Is.is(resource.getTask())));
        this.mockMvc.perform(get(TODO_PATH)).andExpect(status().isOk());
    }
    
    
    // JUnit test for PUT REST API - (update one todo) positive scenario
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

 
 // JUnit test for delete todo REST API
    @Test
    public void givenTodoId_whenDeleteTodo_thenReturn200() throws Exception{
        // given - precondition or setup
        long todoId = 1L;
        String task = "Task one";
        boolean isCompleted = false;
        Todo todo= new Todo(task,isCompleted);
         
        this.mockMvc.perform(post(TODO_PATH).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todo)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.task", Is.is(todo.getTask())));
        this.mockMvc.perform(get(TODO_PATH)).andExpect(status().isOk());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete(TODO_PATH +"/{id}", todoId));

        // then - verify the output
        response.andExpect(status().is(204))
                .andDo(print());
    }
}

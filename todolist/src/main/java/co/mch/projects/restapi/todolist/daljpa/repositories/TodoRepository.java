package co.mch.projects.restapi.todolist.daljpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.mch.projects.restapi.todolist.daljpa.model.Todo;
/**
 * Custom interface that implements JpaRepository <T, ID> 
 * and allows us to use automatically JpaRepository’s methods: 
 * save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

}

package co.mch.projects.restapi.todolist.daljpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.mch.projects.restapi.todolist.daljpa.model.Todo;
/**
 * Custom interface that implements JpaRepository <T, ID> 
 * and allows us to use automatically JpaRepository’s methods: 
 * save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	Optional<Todo> findByTask(String task);

}

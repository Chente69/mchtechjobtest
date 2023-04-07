package co.mch.projects.restapi.todolist.dalbusiness.exceptions;

/**
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}

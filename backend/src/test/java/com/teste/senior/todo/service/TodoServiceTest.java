package com.teste.senior.todo.service;

import com.teste.senior.todo.model.Todo;
import com.teste.senior.todo.model.enums.Status;
import com.teste.senior.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private Todo todo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        todo = Todo.builder()
                .id(1)
                .title("Test Title")
                .description("Test Description")
                .status(Status.PENDING)
                .build();
    }

    @Test
    public void testFindAll() {
        when(todoRepository.findAll()).thenReturn(Collections.singletonList(todo));

        List<Todo> todos = todoService.findAll();

        assertEquals(1, todos.size());
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        when(todoRepository.findById(1)).thenReturn(Optional.of(todo));

        Optional<Todo> foundTodo = todoService.findById(1);

        assertTrue(foundTodo.isPresent());
        assertEquals("Test Title", foundTodo.get().getTitle());
    }

    @Test
    public void testCreate() {
        when(todoRepository.save(todo)).thenReturn(todo);

        Todo createdTodo = todoService.create(todo);

        assertNotNull(createdTodo);
        assertEquals("Test Title", createdTodo.getTitle());
    }

    @Test
    public void testUpdate() {
        when(todoRepository.findById(1)).thenReturn(Optional.of(todo));
        when(todoRepository.save(todo)).thenReturn(todo);

        todo.setTitle("Updated Title");
        Todo updatedTodo = todoService.update(todo);

        assertNotNull(updatedTodo);
        assertEquals("Updated Title", updatedTodo.getTitle());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(todoRepository).deleteById(1);

        todoService.deleteById(1);

        verify(todoRepository, times(1)).deleteById(1);
    }

    @Test
    public void testVerifyTitleThrowsException() {
        Todo todoWithoutTitle = Todo.builder().id(2).description("No Title").status(Status.PENDING).build();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            todoService.create(todoWithoutTitle);
        });

        assertEquals("Titulo é obrigatório", exception.getMessage());
    }
}
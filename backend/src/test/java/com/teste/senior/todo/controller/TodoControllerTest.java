package com.teste.senior.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.senior.todo.model.Todo;
import com.teste.senior.todo.model.enums.Status;
import com.teste.senior.todo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoController todoController;

    @MockBean
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
    public void testGetAll() throws Exception {
        when(todoController.getAll()).thenReturn(Collections.singletonList(todo));

        mockMvc.perform(get("/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Title"));
    }

    @Test
    public void testGetById() throws Exception {
        when(todoController.getById(1)).thenReturn(ResponseEntity.of(Optional.of(todo)));

        mockMvc.perform(get("/todo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"));
    }

    @Test
    public void testCreateTodo() throws Exception {
        when(todoController.createTodo(any(Todo.class))).thenReturn(todo);

        mockMvc.perform(post("/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(todo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Title"));
    }

    @Test
    public void testUpdateTodo() throws Exception {
        when(todoController.updateTodo(any(Todo.class))).thenReturn(todo);

        mockMvc.perform(put("/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(todo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"));
    }

    @Test
    public void testDeleteTodo() throws Exception {
        doNothing().when(todoService).deleteById(1);

        mockMvc.perform(delete("/todo/1"))
                .andExpect(status().isNoContent());
    }
}
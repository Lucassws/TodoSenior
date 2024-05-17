package com.teste.senior.todo.controller;

import com.teste.senior.todo.model.Todo;
import com.teste.senior.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getAll() {
        return todoService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(todoService.findById(id).orElseThrow(() -> new Exception(
                "Tarefa com o ID: "+ id + " não Encontrada")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity delete(@PathVariable Integer id) {
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

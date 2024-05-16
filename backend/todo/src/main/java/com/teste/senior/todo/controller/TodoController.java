package com.teste.senior.todo.controller;

import com.teste.senior.todo.model.Todo;
import com.teste.senior.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping(path = "/todo")
public class TodoController {

    private TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<List<Todo>> getAll() {
        return ResponseEntity.ok(todoRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Todo> getById(Integer id) {
        return ResponseEntity.ok(todoRepository.findById(id).orElse(null));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Todo> save(Todo todo) {
        return ResponseEntity.ok(todoRepository.save(todo));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Todo> update(Todo todo) {
        return ResponseEntity.ok(todoRepository.save(todo));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(Integer id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

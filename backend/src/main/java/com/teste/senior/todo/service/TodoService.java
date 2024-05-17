package com.teste.senior.todo.service;

import com.teste.senior.todo.model.Todo;
import com.teste.senior.todo.model.enums.Status;
import com.teste.senior.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(Integer id) {
        return todoRepository.findById(id);
    }

    public Todo create(Todo todo) {
        verifyTitle(todo);
        return todoRepository.save(todo);
    }

    public Todo update(Todo todo) {
        verifyTitle(todo);
        todoRepository.findById(todo.getId()).orElseThrow(() -> new RuntimeException("Todo not found"));
        if (todo.getStatus() == Status.COMPLETED) {
            todo.setCompletionDate(LocalDateTime.now());
        }
        return todoRepository.save(todo);
    }

    public void deleteById(Integer id) {
        try {
            todoRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Erro ao deletar a tarefa!", e);
        }
    }

    private static void verifyTitle(Todo todo) {
        if (todo.getTitle().isEmpty()) {
            throw new RuntimeException("Titulo é obrigatório");
        }
    }
}

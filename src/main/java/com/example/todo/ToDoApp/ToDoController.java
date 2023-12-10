package com.example.todo.ToDoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping("/todos")
public class ToDoController {
    @Autowired
    private final ToDoRepository todoRepository;

    public ToDoController(ToDoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<ToDo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ToDo> getTodoById(@PathVariable Long id) {
        return todoRepository.findById(id);
    }

    @PostMapping
    public ToDo createTodo(@RequestBody ToDo todo) {
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public ToDo updateTodo(@PathVariable Long id, @RequestBody ToDo updatedTodo) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(updatedTodo.getTitle());
                    todo.setDescription(updatedTodo.getDescription());
                    return todoRepository.save(todo);
                })
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}

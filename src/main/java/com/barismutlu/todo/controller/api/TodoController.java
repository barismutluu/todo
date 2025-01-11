package com.barismutlu.todo.controller.api;
import com.barismutlu.todo.business.dto.TodoDTO;
import com.barismutlu.todo.business.services.TodoService;
import com.barismutlu.todo.data.entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getAlltodos();
        if (todos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTodoById(@PathVariable Long id) {
        try {
            TodoDTO todo = todoService.getTodoById(id);
            return ResponseEntity.ok(todo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Belirtilen ID'ye sahip bir Not bulunamadı: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody TodoDTO todoDTO) {
        try {
            todoService.saveTodo(todoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Not başarıyla oluşturuldu.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not oluşturulurken bir hata oluştu.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodoItem(@PathVariable Long id, @RequestBody TodoItem updatedItem) {
        try {
            TodoItem updated = todoService.updateTodoItem(id, updatedItem);
            return ResponseEntity.ok("Not başarıyla güncellendi: " + updated.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Belirtilen ID'ye sahip bir Not bulunamadı: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable Long id) {
        try {
            todoService.deleteTodoById(id);
            return ResponseEntity.ok("Not başarıyla silindi.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Belirtilen ID'ye sahip bir Not bulunamadı: " + id);
        }
    }
}

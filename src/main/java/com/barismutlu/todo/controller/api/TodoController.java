package com.barismutlu.todo.controller.api;

import com.barismutlu.todo.business.dto.TodoDTO;
import com.barismutlu.todo.business.services.TodoService;
import com.barismutlu.todo.data.entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService=todoService;
    }

    @GetMapping
    public List<TodoDTO>getAllTodos(){
        return todoService.getAlltodos();
    }
    @PutMapping("/{id}")
    public ResponseEntity<TodoItem>updateTodoItem(@PathVariable Long id, @RequestBody TodoItem updatedItem) {
        TodoItem updated=todoService.updateTodoItem(id,updatedItem);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO>getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }
    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO) {
        return ResponseEntity.ok(todoService.saveTodo(todoDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteTodoById(@PathVariable Long id){
        todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }
}

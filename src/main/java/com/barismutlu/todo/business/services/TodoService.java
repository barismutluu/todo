package com.barismutlu.todo.business.services;

import com.barismutlu.todo.business.dto.TodoDTO;
import com.barismutlu.todo.exception.TodoNotFoundException;
import com.barismutlu.todo.data.entity.TodoItem;
import com.barismutlu.todo.data.mapper.TodoMapper;
import com.barismutlu.todo.data.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public List<TodoDTO> getAlltodos(){
        return todoRepository.findAll().stream()
                .map(TodoMapper::toDto)
                .collect(Collectors.toList());
    }

    public TodoDTO getTodoById(long id){
        TodoItem todoItem = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Bu ID'ye sahip bir Todo bulunamadı: " + id));
        return TodoMapper.toDto(todoItem);
    }

    public TodoItem updateTodoItem(Long id, TodoItem updatedItem) {
        return todoRepository.findById(id).map(todo -> {
            todo.setDescription(updatedItem.getDescription());
            todo.setCompleted(updatedItem.isCompleted());
            return todoRepository.save(todo);
        }).orElseThrow(() -> new TodoNotFoundException("Güncellenmek istenen TodoItem bulunamadı: " + id));
    }

    public TodoDTO saveTodo(TodoDTO todoDTO){
        TodoItem todoItem = TodoMapper.toEntity(todoDTO);
        return TodoMapper.toDto(todoRepository.save(todoItem));
    }

    public void deleteTodoById(long id){
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException("Silinmek istenen Todo bulunamadı: " + id);
        }
        todoRepository.deleteById(id);
    }
}

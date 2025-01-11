package com.barismutlu.todo.business.services;

import com.barismutlu.todo.business.dto.TodoDTO;
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
        this.todoRepository=todoRepository;
    }

    public List<TodoDTO> getAlltodos(){
        return todoRepository.findAll().stream()
                .map(TodoMapper::toDto)
                .collect(Collectors.toList());
    }
    public TodoDTO getTodoById(long id){
        TodoItem todoItem=todoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Todo not found"));
                        return TodoMapper.toDto(todoItem);
    }
    public TodoItem updateTodoItem(Long id, TodoItem updatedItem) {
        return todoRepository.findById(id).map(todo -> {
            todo.setDescription(updatedItem.getDescription());
            todo.setCompleted(updatedItem.isCompleted());
            return todoRepository.save(todo);
        }).orElseThrow(() -> new RuntimeException("TodoItem not found"));
    }

    public TodoDTO saveTodo(TodoDTO todoDTO){
        TodoItem todoItem=TodoMapper.toEntity(todoDTO);
        return TodoMapper.toDto(todoRepository.save(todoItem));
    }

    public void deleteTodoById(long id){
        todoRepository.deleteById(id);
    }

}

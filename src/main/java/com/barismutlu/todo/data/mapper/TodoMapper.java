package com.barismutlu.todo.data.mapper;

import com.barismutlu.todo.business.dto.TodoDTO;
import com.barismutlu.todo.data.entity.TodoItem;

public class TodoMapper {
    public static TodoDTO toDto(TodoItem todoItem){
        TodoDTO dto=new TodoDTO();
        dto.setId(todoItem.getId());
        dto.setDescription(todoItem.getDescription());
        dto.setCompleted(todoItem.isCompleted());
        return dto;
    }
    public static TodoItem toEntity(TodoDTO dto){
        TodoItem entity=new TodoItem();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setCompleted(dto.isCompleted());
        return entity;
    }
}

package com.barismutlu.todo.business.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoDTO {

    private Long id;
    private String description;
    private boolean completed;

}

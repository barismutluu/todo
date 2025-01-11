package com.barismutlu.todo.business.dto;

import lombok.Getter;
import lombok.Setter;

@Setter//metotlar için tanımlanmış setter
@Getter//metotlar için tanımlanmış getter
public class TodoDTO {
// proje için gerekli kurucu metotları
    private Long id;
    private String description;
    private boolean completed;

}

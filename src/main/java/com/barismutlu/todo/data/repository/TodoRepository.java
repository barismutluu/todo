package com.barismutlu.todo.data.repository;

import com.barismutlu.todo.data.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem,Long> {
// JpaRepository'den standart CRUD işlemlerini devralır.
}

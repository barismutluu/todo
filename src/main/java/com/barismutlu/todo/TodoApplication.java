package com.barismutlu.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodoApplication {
// Spring Boot projesinin çalıştırılması
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }
}

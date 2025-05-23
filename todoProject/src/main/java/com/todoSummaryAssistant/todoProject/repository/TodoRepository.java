package com.todoSummaryAssistant.todoProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoSummaryAssistant.todoProject.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{

}

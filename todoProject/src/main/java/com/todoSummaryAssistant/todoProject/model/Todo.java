package com.todoSummaryAssistant.todoProject.model;

import com.todoSummaryAssistant.todoProject.enums.TodoStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="todo")
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TodoStatus status = TodoStatus.Pending;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TodoStatus getStatus() {
		return status;
	}

	public void setStatus(TodoStatus status) {
		this.status = status;
	}

	
	
	
	

}

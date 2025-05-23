package com.todoSummaryAssistant.todoProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoSummaryAssistant.todoProject.model.Todo;
import com.todoSummaryAssistant.todoProject.service.TodoService;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@PostMapping("/add")
	public Todo addTodo(@RequestBody Todo todo) {
		return todoService.addTodo(todo);
	}
	
	@GetMapping("/view")
	public List<Todo> viewTodo() {
		return todoService.viewTodo();
	}
	
	@PutMapping("/update/{id}")
	public Todo updateTodo(@PathVariable int id,@RequestBody Todo todo) {
		Todo tododb = todoService.findId(id);
		tododb.setText(todo.getText());
		tododb.setStatus(todo.getStatus());
		return todoService.updateTodo(tododb);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable int id) {
		 todoService.deleteTodo(id);
		 return ResponseEntity.ok("Successfully Deleted for the ID: "+id);
	}
	
	@PostMapping("/summarize")
	public ResponseEntity<String> summarizeTodos() {
	    String summary = todoService.summarizeTodosAndPostToSlack();
	    return ResponseEntity.ok("Summary posted to Slack:\n" + summary);
	}


}

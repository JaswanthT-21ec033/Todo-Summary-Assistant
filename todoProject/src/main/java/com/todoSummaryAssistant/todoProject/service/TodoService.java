package com.todoSummaryAssistant.todoProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoSummaryAssistant.todoProject.model.Todo;
import com.todoSummaryAssistant.todoProject.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired 
	private OpenAIService openAIService;
	
	@Autowired 
	private SlackService slackService;
	

	public Todo addTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	public List<Todo> viewTodo() {
		return todoRepository.findAll();
	}

	public Todo findId(int id) {
		Optional<Todo> optional = todoRepository.findById(id);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid Id : "+id);
		return optional.get();
	}

	public Todo updateTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	public void deleteTodo(int id) {
		todoRepository.deleteById(id);
	}
	
	public String summarizeTodosAndPostToSlack() {
	    List<Todo> todos = todoRepository.findAll(); 
	    List<String> texts = todos.stream().map(Todo::getText).toList(); 
	    String summary = openAIService.summarizeTodos(texts); 
	    slackService.postToSlack(summary); 
	    return summary;
	}


}

package com.example.resQ.controller;


import com.example.resQ.entity.Todo;
import com.example.resQ.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private WebSocketController webSocketController;

    @PostMapping("/todo")
    public ResponseEntity<?> toggleCompletedTodo(@RequestParam("id") Long id){
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Id is not exists."));
        todo.setCompleted(!todo.isCompleted());
        this.todoRepository.save(todo);
        webSocketController.toggleCompleted(todo);
        return ResponseEntity.created(null).build();
    }
}

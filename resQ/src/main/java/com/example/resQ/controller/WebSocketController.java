package com.example.resQ.controller;


import com.example.resQ.entity.Booking;
import com.example.resQ.model.BookingMessage;
import com.example.resQ.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Slf4j
public class WebSocketController {

    private int queue = 0;

    private final SimpMessagingTemplate template;

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    @Autowired
    private BookingRepository bookingRepository;

    @MessageMapping("/send/message")
    public void sendMessage(String msg){
        log.info("sendMessage: {}", msg);
        this.template.convertAndSend("/message",  msg);
    }

    @GetMapping("/get/currentQueue")
    public ResponseEntity<?> currentQueue(){
        log.info("getMessage");
        return ResponseEntity.ok().body(this.queue);
    }

    @GetMapping("increaseQueue")
    public void increaseQueue(){
        queue+=1;
        this.template.convertAndSend("/message",  queue);
    }

    @GetMapping("decreaseQueue")
    public void decreaseQueue(){
        queue-=1;
        this.template.convertAndSend("/message",  queue);

    }


}
//package com.example.resQ.controller;
//
//
//import com.example.resQ.entity.Booking;
//
//import com.example.resQ.repository.BookingRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@CrossOrigin(origins = "http://localhost:4200")
//@RestController
//public class TodoController {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Autowired
//    private WebSocketController webSocketController;
//
//    @PostMapping("/booking")
//    public ResponseEntity<?> toggleCompletedBoooking(@RequestParam("id") Long id){
//        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Id is not exists."));
//        booking.setCompleted(!booking.isCompleted());
//        this.bookingRepository.save(booking);
//        webSocketController.toggleCompleted(booking);
//        return ResponseEntity.created(null).build();
//    }
//}

package com.example.resQ.controller;

import com.example.resQ.entity.Booking;
import com.example.resQ.model.BookingMessage;
import com.example.resQ.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private WebSocketController webSocketController;


    @PostMapping("/saveBooking")
    public Booking saveBooking(@RequestBody BookingMessage bookingMessage){
        Booking booking = new Booking();
        booking.setName(bookingMessage.getName());
        booking.setNumber(bookingMessage.getNumber());
        this.bookingRepository.saveAndFlush(booking);
        return booking;
    }


}

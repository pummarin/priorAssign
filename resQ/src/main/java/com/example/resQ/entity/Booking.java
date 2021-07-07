//package com.example.resQ.entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Data
//@Entity
//@Table(name = "Booking")
//public class Booking {
//
//    @Id
//    @SequenceGenerator(name = "User_seq", sequenceName = "User_seq")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTE_seq")
//    @Column(name = "User_ID", unique = true, nullable = true)
//    private Long id;
//
//    @OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
//    @JoinColumn(name = "User_ID", insertable = true)
//    private User user  ;
//
//    @Column(name = "CREATED_DATE")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime bookingTime;
//}

package com.example.library.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "operation_time")
    private Timestamp operationTime;
    @Column(name = "reader_phone_number")
    private String readerPhoneNumber;
    @Column(name = "book_id")
    private Long bookId;

    public Transaction(String type, Timestamp operationTime, String readerPhoneNumber, Long bookId) {
        this.type = type;
        this.operationTime = operationTime;
        this.readerPhoneNumber = readerPhoneNumber;
        this.bookId = bookId;
    }
}

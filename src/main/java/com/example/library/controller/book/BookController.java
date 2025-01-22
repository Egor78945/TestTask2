package com.example.library.controller.book;

import com.example.library.controller.book.advice.BookControllerExceptionHandler;
import com.example.library.model.dto.BookDTO;
import com.example.library.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/book")
@RequiredArgsConstructor
@BookControllerExceptionHandler
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getFreeBooks() {
        return ResponseEntity.ok(bookService.getFreeBooks());
    }

    @PutMapping("/take")
    public ResponseEntity<String> takeBook(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("bookId") Long id) {
        bookService.takeBook(phoneNumber, id);
        return ResponseEntity.ok("The book is yours, dont forget to return it.");
    }
}

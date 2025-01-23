package com.example.library.controller.book;

import com.example.library.controller.book.advice.BookControllerExceptionHandler;
import com.example.library.model.dto.BookDTO;
import com.example.library.service.book.BookService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "Возвращает незанятые книги.")
    public ResponseEntity<List<BookDTO>> getFreeBooks() {
        return ResponseEntity.ok(bookService.getFreeBooks());
    }

    @PutMapping("/take")
    @Operation(description = "Закрепляет книги за читателем по номеру телефона.")
    public ResponseEntity<String> takeBook(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("bookId") Long id) {
        bookService.takeBook(phoneNumber, id);
        return ResponseEntity.ok("The book is yours, dont forget to return it.");
    }

    @PutMapping("/return")
    @Operation(description = "Освобождает книгу от читателя по номеру телефона.")
    public ResponseEntity<String> returnBook(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("bookId") Long bookId){
        bookService.returnBook(phoneNumber, bookId);
        return ResponseEntity.ok("You returned the book.");
    }
}

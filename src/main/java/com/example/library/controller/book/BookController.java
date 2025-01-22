package com.example.library.controller.book;

import com.example.library.model.dto.BookDTO;
import com.example.library.model.entity.Book;
import com.example.library.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getFreeBooks() {
        return ResponseEntity.ok(bookService.getFreeBooks());
    }
}

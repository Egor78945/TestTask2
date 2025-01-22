package com.example.library.controller.author;

import com.example.library.controller.author.advice.AuthorControllerExceptionHandler;
import com.example.library.model.entity.Author;
import com.example.library.service.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library/author")
@RequiredArgsConstructor
@AuthorControllerExceptionHandler
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/popular")
    public ResponseEntity<List<Author>> getMostPopularAuthorByDateDiapason(@RequestParam("from") String from, @RequestParam("to") String to) {
        List<Author> authors = authorService.getMostPopularAuthorByDateDiapason(from, to);
        return ResponseEntity.ok(authors);
    }
}

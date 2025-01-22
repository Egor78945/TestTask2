package com.example.library.controller.reader;

import com.example.library.controller.reader.advice.ReaderControllerExceptionHandler;
import com.example.library.model.dto.ReaderDTO;
import com.example.library.model.entity.Reader;
import com.example.library.service.reader.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/reader")
@RequiredArgsConstructor
@ReaderControllerExceptionHandler
public class ReaderController {
    private final ReaderService readerService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody ReaderDTO readerDTO) {
        readerService.registration(readerDTO);
        return ResponseEntity.ok("You have been registered.");
    }

    @GetMapping("/most")
    public ResponseEntity<List<Reader>> getMostReadingReader(){
        return ResponseEntity.ok(readerService.getMostReadingReaders());
    }
}

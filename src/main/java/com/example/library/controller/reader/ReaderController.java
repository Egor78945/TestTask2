package com.example.library.controller.reader;

import com.example.library.controller.reader.advice.ReaderControllerExceptionHandler;
import com.example.library.model.dto.ReaderDTO;
import com.example.library.model.dto.ReaderDeptDTO;
import com.example.library.model.entity.Reader;
import com.example.library.service.reader.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "Регистрирует нового читателя.")
    public ResponseEntity<String> registration(@RequestBody ReaderDTO readerDTO) {
        readerService.registration(readerDTO);
        return ResponseEntity.ok("You have been registered.");
    }

    @GetMapping("/most")
    @Operation(description = "Возвращает читателей, которые взяли больше всего книг.")
    public ResponseEntity<List<Reader>> getMostReadingReader(){
        return ResponseEntity.ok(readerService.getMostReadingReaders());
    }

    @GetMapping("/dept")
    @Operation(description = "Возвращает читателей и количество невозвращённых ими книг.")
    public ResponseEntity<List<ReaderDeptDTO>> getReadersDept(){
        return ResponseEntity.ok(readerService.getReadersDept());
    }
}

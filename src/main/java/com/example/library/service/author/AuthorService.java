package com.example.library.service.author;

import com.example.library.enumeration.TransactionOperationType;
import com.example.library.model.entity.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.reader.mapper.ReaderMapper;
import com.example.library.service.reader.validation.ReaderValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getMostPopularAuthorByDateDiapason(String fromString, String toString) {
        ReaderValidationService.isValidDate(fromString);
        ReaderValidationService.isValidDate(toString);
        Timestamp from = new Timestamp(ReaderMapper.mapStringDateToMillis(fromString));
        Timestamp to = new Timestamp(ReaderMapper.mapStringDateToMillis(toString));
        return authorRepository.findMostPopularAuthorByTimeDiapason(from, to, TransactionOperationType.TAKE.name()).stream().map(arr -> new Author((Long) arr[0], (String) arr[1], (String) arr[2], (Date) arr[3])).collect(Collectors.toList());
    }
}

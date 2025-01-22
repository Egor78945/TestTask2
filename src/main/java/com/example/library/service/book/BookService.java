package com.example.library.service.book;

import com.example.library.model.dto.BookDTO;
import com.example.library.model.entity.Book;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public List<BookDTO> getFreeBooks(){
        return bookRepository.findFreeBooks().stream().map(arr -> new BookDTO((Long)arr[0], (String)arr[1], (Integer)arr[2], (arr[3] +  " " + arr[4]))).collect(Collectors.toList());
    }
}

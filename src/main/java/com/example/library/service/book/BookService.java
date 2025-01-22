package com.example.library.service.book;

import com.example.library.enumeration.TransactionOperationType;
import com.example.library.model.dto.BookDTO;
import com.example.library.model.entity.Transaction;
import com.example.library.repository.BookRepository;
import com.example.library.service.reader.ReaderService;
import com.example.library.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ReaderService readerService;
    private final TransactionService transactionService;

    public List<BookDTO> getFreeBooks(){
        return bookRepository.findFreeBooks()
                .stream()
                .map(arr -> new BookDTO((Long)arr[0], (String)arr[1], (Integer)arr[2], (arr[3] +  " " + arr[4])))
                .collect(Collectors.toList());
    }

    public void takeBook(String phoneNumber, Long bookId){
        if(readerService.existsReaderByPhoneNumber(phoneNumber)){
            if(bookIsFree(bookId)){
                transactionService.saveTransaction(new Transaction(TransactionOperationType.TAKE.name(), new Timestamp(System.currentTimeMillis()), phoneNumber, bookId));
                return;
            }
            throw new RuntimeException("The book is busy by other reader.");
        }
        throw new RuntimeException("There in no reader with this phone number.");
    }

    public void returnBook(String phoneNumber, Long bookId){
        if(readerService.existsReaderByPhoneNumber(phoneNumber)){
            if(bookIsBusyBy(phoneNumber, bookId)){
                transactionService.saveTransaction(new Transaction(TransactionOperationType.RETURN.name(), new Timestamp(System.currentTimeMillis()), phoneNumber, bookId));
                return;
            }
            throw new RuntimeException("You did not take the book.");
        }
        throw new RuntimeException("There in no reader with this phone number.");
    }

    private boolean bookIsFree(Long bookId){
        return bookRepository.bookIsFreeById(bookId);
    }

    private boolean bookIsBusyBy(String phoneNumber, Long bookId){
        return bookRepository.existsTransactionByPhoneNumberAndBookIdAndType(phoneNumber, bookId, TransactionOperationType.TAKE.name());
    }
}

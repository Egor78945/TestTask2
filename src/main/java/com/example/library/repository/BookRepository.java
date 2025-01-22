package com.example.library.repository;

import com.example.library.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "WITH pairs AS (SELECT book_id FROM transactions GROUP BY book_id HAVING COUNT(book_id) % 2 = 0 OR COUNT(book_id) = 0) SELECT DISTINCT b.id, b.name, b.publish_date, a.name, a.surname FROM books b JOIN authors a ON b.author_id=a.id JOIN pairs ON pairs.book_id=b.id", nativeQuery = true)
    List<Object[]> findFreeBooks();
    @Query(value = "WITH pairs AS (SELECT book_id FROM transactions GROUP BY book_id HAVING COUNT(book_id) % 2 = 0 OR COUNT(book_id) = 0) SELECT CASE WHEN :id IN (SELECT * FROM pairs) THEN TRUE ELSE FALSE END", nativeQuery = true)
    Boolean bookIsFreeById(Long id);
    @Query(value = "WITH pairs AS (SELECT book_id FROM transactions GROUP BY book_id HAVING COUNT(book_id) % 2 != 0) SELECT CASE WHEN EXISTS (SELECT * FROM transactions t JOIN pairs p ON t.book_id=p.book_id WHERE t.reader_phone_number=:phoneNumber AND t.book_id=:bookId AND t.type=:type) THEN TRUE ELSE FALSE END", nativeQuery = true)
    Boolean existsTransactionByPhoneNumberAndBookIdAndType(String phoneNumber, Long bookId, String type);
}

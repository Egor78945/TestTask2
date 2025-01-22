package com.example.library.repository;

import com.example.library.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "WITH timeAuthors AS (SELECT a.id, COUNT(*) AS count FROM transactions t JOIN books b on t.book_id=b.id JOIN authors a ON b.author_id=a.id WHERE t.operation_time BETWEEN :from AND :to AND t.type=:transType GROUP BY a.id), maxAuthor AS (SELECT MAX(timeAuthors.count) AS max FROM timeAuthors) SELECT a.id, a.name, a.surname, a.birthday FROM authors a JOIN timeAuthors t ON a.id=t.id JOIN maxAuthor m ON t.count=m.max", nativeQuery = true)
    List<Object[]> findMostPopularAuthorByTimeDiapason(Timestamp from, Timestamp to, String transType);
}

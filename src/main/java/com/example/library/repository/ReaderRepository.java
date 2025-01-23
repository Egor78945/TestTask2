package com.example.library.repository;

import com.example.library.model.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, String> {
    Boolean existsReaderByPhoneNumber(String phoneNumber);

    @Query(value = "WITH maxes AS (SELECT reader_phone_number, COUNT(*) AS count FROM transactions  WHERE type='TAKE' GROUP BY reader_phone_number), maxCount AS (SELECT MAX(maxes.count) as max from maxes) SELECT r.phone_number, r.name, r.surname, r.sex, r.birthday FROM readers r JOIN maxes ON r.phone_number = maxes.reader_phone_number JOIN maxCount ON maxes.count=maxCount.max", nativeQuery = true)
    List<Object[]> findMostReadingReaders();

    @Query(value = "WITH takes AS (SELECT reader_phone_number, COUNT(*) AS count FROM transactions WHERE type='TAKE' GROUP BY reader_phone_number), returns AS (SELECT reader_phone_number, COUNT(*) AS count FROM transactions WHERE type='RETURN' GROUP BY reader_phone_number) SELECT t.reader_phone_number, CASE WHEN t.reader_phone_number IN (SELECT reader_phone_number FROM returns) THEN (t.count-r.count) ELSE t.count END AS diff FROM takes t LEFT JOIN returns r ON t.reader_phone_number=r.reader_phone_number ORDER BY diff DESC", nativeQuery = true)
    List<Object[]> findReadersDept();
}

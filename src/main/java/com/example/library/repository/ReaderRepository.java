package com.example.library.repository;

import com.example.library.model.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, String> {
    @Query("SELECT CASE WHEN EXISTS(SELECT phoneNumber FROM Reader WHERE phoneNumber=:phoneNumber) THEN TRUE ELSE FALSE END")
    Boolean existsReaderByPhoneNumber(String phoneNumber);
    @Query(value = "WITH maxes AS (SELECT reader_phone_number, COUNT(*) AS count FROM transactions  WHERE type='TAKE' GROUP BY reader_phone_number), maxCount AS (SELECT MAX(maxes.count) as max from maxes) SELECT r.phone_number, r.name, r.surname, r.sex, r.birthday FROM readers r JOIN maxes ON r.phone_number = maxes.reader_phone_number JOIN maxCount ON maxes.count=maxCount.max", nativeQuery = true)
    List<Object[]> findMostReadingReaders();
}

package com.example.library.service.reader.mapper;

import com.example.library.model.dto.ReaderDTO;
import com.example.library.model.entity.Reader;

import java.sql.Date;
import java.util.Calendar;

public class ReaderMapper {
    public static Reader mapTo(ReaderDTO readerDTO) {
        return new Reader(readerDTO.phoneNumber(), readerDTO.name(), readerDTO.surname(), readerDTO.sex().toUpperCase(), new Date(mapBirthdayStringToDate(readerDTO.birthday())));
    }

    private static long mapBirthdayStringToDate(String birthday) {
        String[] dmy = birthday.split("/");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(dmy[2]), Integer.parseInt(dmy[1]) - 1, Integer.parseInt(dmy[0]));
        return calendar.getTimeInMillis();
    }
}

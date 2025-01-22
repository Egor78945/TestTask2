package com.example.library.service.reader.validation;

import com.example.library.enumeration.ReaderSex;
import com.example.library.model.dto.ReaderDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ReaderValidationService {
    public static void isValid(ReaderDTO readerDTO){
        isValidPhoneNumber(readerDTO.phoneNumber());
        isValidNameOrSurname(readerDTO.name());
        isValidNameOrSurname(readerDTO.surname());
        isValidSexType(readerDTO.sex());
        isValidBirthday(readerDTO.birthday());
    }

    private static void isValidPhoneNumber(String phoneNumber){
        if(phoneNumber.startsWith("+") && phoneNumber.length() == 12){
            for(char n: phoneNumber.substring(1).toCharArray()){
                if(n < 48 || n > 57){
                    throw new RuntimeException("Phone number contains illegal symbols. It must to contain only digits from 0 to 9.");
                }
            }
            return;
        }
        throw new RuntimeException("Invalid phone number format. Required - +7 *** *** ** **");
    }

    private static void isValidNameOrSurname(String nameOrSurname){
        if(nameOrSurname.isEmpty() || nameOrSurname.isBlank() || nameOrSurname.length() > 50){
            throw new RuntimeException("Name is too short or to long. Max length - 50, Min length - 1.");
        }
    }

    private static void isValidSexType(String sex){
        if(!ReaderSex.MALE.name().equals(sex.toUpperCase()) && !ReaderSex.FEMALE.name().equals(sex.toUpperCase())){
            throw new RuntimeException("Invalid sex type. Required - male or female.");
        }
    }

    private static void isValidBirthday(String birthday){
        String[] dmy = birthday.split("/");
        if(Integer.parseInt(dmy[0]) < 1 || Integer.parseInt(dmy[0]) > 31){
            throw new RuntimeException("Invalid day.");
        }
        if(Integer.parseInt(dmy[1]) < 1 || Integer.parseInt(dmy[1]) > 12){
            throw new RuntimeException("Invalid month.");
        }
        if(Integer.parseInt(dmy[2]) < 1900 || Integer.parseInt(dmy[2]) > LocalDate.now().getYear()){
            throw new RuntimeException("Invalid year.");
        }
    }
}

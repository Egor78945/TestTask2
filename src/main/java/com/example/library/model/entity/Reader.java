package com.example.library.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "readers")
@Data
@NoArgsConstructor
public class Reader {
    @Id
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "sex")
    private String sex;
    @Column(name = "birthday")
    private Date birthday;

    public Reader(String phoneNumber, String name, String surname, String sex, Date birthday) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
    }
}

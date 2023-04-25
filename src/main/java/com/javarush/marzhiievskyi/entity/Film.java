package com.javarush.marzhiievskyi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "movie", name = "film")
public class Film {
    @Id
    private Integer id;
    //TODO continue!
}

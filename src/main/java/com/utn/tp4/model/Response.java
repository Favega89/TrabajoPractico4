package com.utn.tp4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Response implements Serializable{

    public Response(String soName, String browserName) {
        this.soName = soName;
        this.browserName = browserName;
        this.combination = this.soName+ " , " + this.browserName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String soName;

    private String browserName;

    private String combination;
}

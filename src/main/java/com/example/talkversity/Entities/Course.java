package com.example.talkversity.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    @ GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String instructor;
    private Integer creditNumbers;
    private Integer costPerCredit;
    private String days;

    public Course() {
    }

    public Course(String name, String instructor, Integer creditNumbers, Integer costPerCredit, String days) {
        this.name = name;
        this.instructor = instructor;
        this.creditNumbers = creditNumbers;
        this.costPerCredit = costPerCredit;
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public Integer getCreditNumbers() {
        return creditNumbers;
    }

    public Integer getCostPerCredit() {
        return costPerCredit;
    }

    public String getDays() {
        return days;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setCreditNumbers(Integer creditNumbers) {
        this.creditNumbers = creditNumbers;
    }

    public void setCostPerCredit(Integer costPerCredit) {
        this.costPerCredit = costPerCredit;
    }

    public void setDays(String days) {
        this.days = days;
    }
}

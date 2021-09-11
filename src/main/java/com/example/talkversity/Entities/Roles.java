package com.example.talkversity.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Roles() {
    }

    public Roles(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @ManyToMany(mappedBy = "roles", fetch=FetchType.EAGER)
//    private Set<Users> userSet = new HashSet<Users>();

}

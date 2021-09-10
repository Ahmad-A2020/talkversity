package com.example.talkversity.Entities;
import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname;
    private String midname;
    private String lastname;
    private Date dateOfBirth;
    private String gender;

    @Column(unique = true)
    private String userName;

    private String password;

    public Users() {
    }

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    Set<Roles> roles= new HashSet<>();

    /**
     * This function to add role to the user who may have more than one
     * @param role
     */
    public void addRole(Roles role){
        roles.add(role);
    }

    public Users(String firstname, String midname, String lastname, Date dateOfBirth, String gender,  String userName, String password) {
        this.firstname = firstname;
        this.midname = midname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMidname() {
        return midname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

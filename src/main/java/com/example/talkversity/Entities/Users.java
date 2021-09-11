package com.example.talkversity.Entities;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name="users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String midname;
    private String lastname;
    private Date dateOfBirth;
    private String gender;
    private String faculty;

    @Column(unique = true)
    private String username;
    private String password;


    public Users() {
    }
    public void setUsername(String username) {
        this.username = username;
    }



    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            })
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
   private  Set<Roles> roles= new HashSet<>();

    /**
     * This function to add role to the user who may have more than one
     * @param role
     */
    public void addRole(Roles role){
        roles.add(role);
    }
    // admin constructor
    public Users(String firstname, String midname, String lastname, Date dateOfBirth, String gender,  String username, String password) {
        this.firstname = firstname;
        this.midname = midname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }
    // student constructor
    public Users(String firstname, String midname, String lastname, Date dateOfBirth, String gender, String faculty, String username, String password) {
        this.firstname = firstname;
        this.midname = midname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.faculty = faculty;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Roles> roles = this.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }


    public String getPassword() {
        return password;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getFaculty() {
        return faculty;
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

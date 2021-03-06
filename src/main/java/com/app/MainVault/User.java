package com.app.MainVault;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        System.out.println("USERNAME Supposedly worked");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println("LAST NAME Supposedly worked");
    }

    @Override
    public String toString() {
        return username+password;
    }
}

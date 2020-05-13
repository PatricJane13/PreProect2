package model;

import javax.persistence.*;

@Entity
@Table(name = "register_table")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "role")
    private String role;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private long age;

    public User(long id, String name, String password, long age, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public User(long id, String name, String password, long age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public User(String name, String password, long age, String role) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

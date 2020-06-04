package it.university.testapp.entity;

import javax.persistence.*;

@Entity(name = "Students")
public class Student {

    private int id;
    private String name;
    private long passport;

    public Student() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PASSPORT", unique = true, nullable = false, length = 10)
    public long getPassport() {
        return passport;
    }

    public void setPassport(long passport) {
        this.passport = passport;
    }
}

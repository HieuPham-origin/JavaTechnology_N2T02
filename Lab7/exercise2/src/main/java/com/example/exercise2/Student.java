package com.example.exercise2;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter @ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String email;
    @Column(name="ietlsscore")
    private double ieltsScore;

    public Student(Student st){
        this.id = st.getId();
        this.name = st.getName();
        this.age = st.getAge();
        this.email = st.getEmail();
        this.ieltsScore = st.getIeltsScore();
    }
}


package fr.epita.quiz.datamodel;

import jakarta.persistence.*;

@Entity
@Table(name = "CHOICES")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    int id;

    @Column
    String option;

    @ManyToOne
    Question question;
}

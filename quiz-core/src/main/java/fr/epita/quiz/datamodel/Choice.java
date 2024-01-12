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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne
    Question question;
}

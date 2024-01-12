package fr.epita.quiz.datamodel;

import jakarta.persistence.*;

@Entity
@Table(name = "QUESTIONS")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "QUEST_ID")
    private int id;
    @Column(name = "QUEST_TITLE")
    private String title;

    public String getTitle() {
        return title;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

package fr.epita.quiz.datamodel;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "QUESTIONS")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "QUEST_ID")
    private int id;
    @Column(name = "QUEST_TITLE")
    private String title;
    @OneToMany(targetEntity = Choice.class, mappedBy = "question", fetch = FetchType.LAZY)
    private List<Choice> choices;

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

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

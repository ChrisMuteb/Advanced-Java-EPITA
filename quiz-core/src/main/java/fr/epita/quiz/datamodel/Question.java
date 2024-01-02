package fr.epita.quiz.datamodel;

public class Question {
    private int id;
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
}

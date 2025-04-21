package model;

import java.util.List;

public class flashcard {
    private String question;
    private String answer;
    private List<String> choices; // null oder leer für offene Fragen; optional bei Multiple Choice
    private boolean multipleChoice;
    private int id;

    // Konstruktoren, Getter & Setter

    public flashcard(int id, String question, String answer, List<String> choices, boolean isMultipleChoice) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.choices = choices;
        this.multipleChoice = isMultipleChoice;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getChoices() {
        return choices;
    }

    public boolean isMultipleChoice() {
        return multipleChoice;
    }

}

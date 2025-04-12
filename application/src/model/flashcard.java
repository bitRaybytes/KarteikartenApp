package model;

import java.util.List;

public class flashcard {
    private String question;
    private String answer;
    private List<String> choices; // null oder leer für offene Fragen; optional bei Multiple Choice
    private boolean multipleChoice;

    // Konstruktoren, Getter & Setter

    public flashcard(String question, String answer, List<String> choices, boolean multipleChoice) {
        this.question = question;
        this.answer = answer;
        this.choices = choices;
        this.multipleChoice = multipleChoice;
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

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import model.flashcard;
import model.flashcardRepository;

public class FlashcardPanel extends JPanel {

    private JLabel questionLabel;
    private JPanel answerPanel;
    private JButton showAnswerButton;
    private JPanel choicesPanel;

    public FlashcardPanel(){
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        choicesPanel = new JPanel();
        choicesPanel.setLayout(new GridLayout(0, 1, 10, 10));
        add(choicesPanel, BorderLayout.CENTER);

        answerPanel = new JPanel();
        JLabel answerLabel = new JLabel();
        answerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        answerPanel.add(answerLabel);
        answerPanel.setVisible(false); // Anfangs versteckt
        add(answerPanel, BorderLayout.SOUTH);

        showAnswerButton = new JButton("Antwort anzeigen");
        showAnswerButton.addActionListener((ActionEvent e) -> answerPanel.setVisible(true));
        add(showAnswerButton, BorderLayout.SOUTH);

        flashcardRepository repo = new flashcardRepository();
        List<flashcard> cards = repo.getAllCards();

        if (!cards.isEmpty()) {
            flashcard card = cards.get(0); // Erstmal nur die erste Karte anzeigen
            loadFlashcard(
                    card.getQuestion(),
                    card.getChoices(),
                    card.getAnswer(),
                    card.isMultipleChoice()
            );
        }

    }

    public void loadFlashcard(String question, List<String> choices, String correctAnswer, boolean isMultipleChoice) {
        questionLabel.setText("<html><div style='text-align: center;'>" + question + "</div></html>");

        choicesPanel.removeAll();
        answerPanel.setVisible(false);
        showAnswerButton.setVisible(false);

        if (isMultipleChoice && choices != null && !choices.isEmpty()) {
            for (String choice : choices) {
                JButton choiceButton = new JButton(choice);
                choiceButton.addActionListener(e -> {
                    boolean isCorrect = choice.equals(correctAnswer);
                    JOptionPane.showMessageDialog(this,
                            isCorrect ? "Richtig! 🎉" : "Leider falsch. ❌",
                            "Antwort", JOptionPane.INFORMATION_MESSAGE);
                });
                choicesPanel.add(choiceButton);
            }
        } else {
            showAnswerButton.setVisible(true);
            JLabel answerLabel = (JLabel) answerPanel.getComponent(0);
            answerLabel.setText("Antwort: " + correctAnswer);
        }
        revalidate();
        repaint();
    }

}

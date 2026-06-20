package view;

import model.Flashcard;
import model.FlashcardRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LearnPanel extends JPanel {
    private List<Flashcard> cards;
    private int currentIndex = 0;
    private JLabel questionLabel;
    private JButton revealButton;
    private JLabel answerLabel;
    private JButton nextButton;

    public LearnPanel() {
        setLayout(new BorderLayout());

        FlashcardRepository repository = new FlashcardRepository();
        cards = repository.getAllCards();

        questionLabel = new JLabel("Frage wird geladen...", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        answerLabel = new JLabel("", SwingConstants.CENTER);
        answerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        answerLabel.setForeground(Color.BLUE);

        revealButton = new JButton("Antwort anzeigen");
        nextButton = new JButton("Nächste Karte");

        revealButton.addActionListener((ActionEvent e) -> showAnswer());
        nextButton.addActionListener((ActionEvent e) -> showNextCard());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(revealButton);
        buttonPanel.add(nextButton);

        add(questionLabel, BorderLayout.NORTH);
        add(answerLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        if (!cards.isEmpty()) {
            displayCard(cards.get(currentIndex));
        } else {
            questionLabel.setText("Keine Karten vorhanden.");
            revealButton.setEnabled(false);
            nextButton.setEnabled(false);
        }
    }

    private void displayCard(Flashcard card) {
        questionLabel.setText("Frage: " + card.getQuestion());
        answerLabel.setText("");  // Antwort wird erst auf Klick gezeigt
    }

    private void showAnswer() {
        Flashcard card = cards.get(currentIndex);
        answerLabel.setText("Antwort: " + card.getAnswer());
    }

    private void showNextCard() {
        currentIndex++;
        if (currentIndex >= cards.size()) {
            currentIndex = 0;
            JOptionPane.showMessageDialog(this, "Du hast alle Karten durchlaufen! 🔁");
        }
        displayCard(cards.get(currentIndex));
    }
}

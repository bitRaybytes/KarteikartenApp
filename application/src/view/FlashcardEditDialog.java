package view;

import model.Flashcard;
import model.FlashcardRepository;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class FlashcardEditDialog extends JDialog {

    private FlashcardRepository repository = new FlashcardRepository();
    private JTextField questionField;
    private JTextField answerField;
    private JCheckBox multipleChoiceCheck;
    private JTextField choicesField;
    private boolean saved = false;
    private Flashcard updatedCard;
    private GuiView.CardSaveListener listener;

    public FlashcardEditDialog(JFrame parent, Flashcard card) {
        super(parent, "Karte bearbeiten", true);
        setSize(500, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 1, 10, 10));

        questionField = new JTextField(card.getQuestion());
        answerField = new JTextField(card.getAnswer());

        multipleChoiceCheck = new JCheckBox("Multiple Choice", card.isMultipleChoice());
        choicesField = new JTextField();
        if (card.getChoices() != null) {
            choicesField.setText(String.join(",", card.getChoices()));
        }

        add(labeled("Frage:", questionField));
        add(labeled("Antwort:", answerField));
        add(multipleChoiceCheck);
        add(labeled("Antwortmöglichkeiten (kommagetrennt):", choicesField));

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Speichern");
        JButton cancelButton = new JButton("Abbrechen");

        saveButton.addActionListener(e -> {
            saved = true;
            updatedCard = createCardFromFields();

            if (listener != null) {
                listener.onCardSaved(updatedCard); // Callback an Aufrufer
            }

            dispose();
        });



        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel);
    }

    public void setCardSaveListener(GuiView.CardSaveListener listener) {
        this.listener = listener;
    }

    private JPanel labeled(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private Flashcard createCardFromFields() {
        String question = questionField.getText().trim();
        String answer = answerField.getText().trim();
        boolean isMC = multipleChoiceCheck.isSelected();
        List<String> choices = null;

        if (isMC && !choicesField.getText().trim().isEmpty()) {
            choices = Arrays.asList(choicesField.getText().trim().split("\\s*,\\s*"));
        }
        int id = (updatedCard != null) ? updatedCard.getId() : 0; // <- id erhalten, wenn bearbeitet
        return new Flashcard(id, question, answer, choices, isMC);
    }

    public boolean isSaved() {
        return saved;
    }

    public Flashcard getUpdatedCard() {
        return updatedCard;
    }
}

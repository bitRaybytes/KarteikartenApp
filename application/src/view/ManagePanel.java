package view;

import model.flashcard;
import model.flashcardRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ManagePanel extends JPanel {

    private JFrame frame;
    private JPanel listPanel;
    private flashcardRepository repository;
    private DefaultListModel<String> cardListModel;
    private JList<String> cardList;
    private JPanel cardPanel = new JPanel(new BorderLayout());
    private flashcard selectedCard = null;



    public ManagePanel() {
        setLayout(new BorderLayout());
        repository = new flashcardRepository();
        List<flashcard> cards = repository.getAllCards();

        JLabel header = new JLabel("Karten verwalten", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        add(header, BorderLayout.NORTH);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (flashcard card : cards) {
            listPanel.add(createCardEntry(card));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        // Set up the list model and JList
        cardListModel = new DefaultListModel<>();
        cardList = new JList<>(cardListModel);
        add(scrollPane, BorderLayout.CENTER);

        // Button to add a new card
        JButton addButton = new JButton("Neue Karte");
        addButton.addActionListener(e -> {
            flashcard emptyCard = new flashcard(0, "", "", null, false);
            FlashcardEditDialog dialog = new FlashcardEditDialog(frame, emptyCard);
            dialog.setCardSaveListener(card -> {
                repository.saveCard(card);
                refreshList();
            });
            dialog.setVisible(true);
        });
        add(addButton, BorderLayout.SOUTH); // füge den Button ins Layout ein

    }

    public void onCardSaved(flashcard newCard) {
        repository.saveCard(newCard);       // speichert in MySQL
        addCardToList(newCard);             // zeigt im GUI an
    }




    public void addCardToList(flashcard newCard) {
        JLabel cardLabel = new JLabel("Frage: " + newCard.getQuestion());  // Anzeige der Frage
        cardLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        cardPanel.add(cardLabel);  // Füge das Label zum Panel hinzu

        // Optional: GUI sofort aktualisieren
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private JPanel createCardEntry(flashcard card) {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cardPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel questionLabel = new JLabel(card.getQuestion());
        questionLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        cardPanel.add(questionLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton editButton = new JButton("Bearbeiten");
        editButton.addActionListener((ActionEvent e) -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            FlashcardEditDialog dialog = new FlashcardEditDialog(parentFrame, card);
            dialog.setCardSaveListener(updatedCard -> {
                repository.saveCard(updatedCard);
                refreshList(); // Liste neu laden
            });
            dialog.setVisible(true);
        });

        JButton deleteButton = new JButton("Löschen");
        deleteButton.addActionListener((ActionEvent e) -> {
            int confirmed = JOptionPane.showConfirmDialog(
                    this,
                    "Möchtest du diese Karte wirklich löschen?",
                    "Löschen bestätigen",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmed == JOptionPane.YES_OPTION) {
                repository.deleteCard(card.getId());  // falls du deleteCard(int id) hast
                refreshList();  // Liste aktualisieren
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        cardPanel.add(buttonPanel, BorderLayout.EAST);

        return cardPanel;
    }


    public void refreshList() {
        listPanel.removeAll();  // Leere die Liste
        List<flashcard> cards = repository.getAllCards();  // Lade neue Karten

        for (flashcard card : cards) {
            listPanel.add(createCardEntry(card));
        }

        listPanel.revalidate();
        listPanel.repaint();
    }
}
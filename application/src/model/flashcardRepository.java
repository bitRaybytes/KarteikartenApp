package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class flashcardRepository {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/karteikartenApp?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";     // <- anpassen
    private static final String DB_PASSWORD = "";     // <- anpassen

    public flashcardRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Treiber laden
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<flashcard> getAllCards() {
        List<flashcard> cards = new ArrayList<>();
        String sql = "SELECT * FROM flashcards";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                String choicesString = rs.getString("choices");
                boolean isMC = rs.getBoolean("multipleChoice");

                List<String> choices = null;
                if (choicesString != null && !choicesString.isEmpty()) {
                    choices = Arrays.asList(choicesString.split("\\s*,\\s*"));
                }

                flashcard card = new flashcard(id, question, answer, choices, isMC);
                cards.add(card);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }

    public void saveCard(flashcard card) {

        if (card.getId() == 0) {
            // Neue Karte → INSERT
            String sql = "INSERT INTO flashcards (question, answer, choices, multiple_choice) VALUES (?, ?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, card.getQuestion());
                stmt.setString(2, card.getAnswer());
                stmt.setString(3, String.join(",", card.getChoices() != null ? card.getChoices() : new ArrayList<>()));
                stmt.setBoolean(4, card.isMultipleChoice());
                stmt.executeUpdate();

                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    card.setId(keys.getInt(1)); // Automatisch vergebene ID zurück in das Objekt
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Bestehende Karte → UPDATE
            updateCard(card);
        }
    }

    public void updateCard(flashcard card) {
        String sql = "UPDATE flashcards SET question = ?, answer = ?, choices = ?, multipleChoice = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, card.getQuestion());
            stmt.setString(2, card.getAnswer());
            stmt.setString(3, card.getChoices() != null ? String.join(";", card.getChoices()) : null);
            stmt.setBoolean(4, card.isMultipleChoice());
            stmt.setInt(5, card.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCard(int id) {
        String sql = "DELETE FROM flashcards WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
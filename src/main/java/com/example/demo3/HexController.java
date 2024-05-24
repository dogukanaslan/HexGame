package com.example.demo3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HexController {
    private GameModel model;
    private BoardView view;
    private Label turnLabel;
    private Label redMovesLabel;
    private Label blueMovesLabel;

    public HexController(GameModel model, BoardView view,Label turnLabel , Label redMovesLabel, Label blueMovesLabel) {
        this.model = model;
        this.view = view;
        this.turnLabel = turnLabel;
        this.redMovesLabel = redMovesLabel;
        this.blueMovesLabel = blueMovesLabel;
        attachEventHandlers(); // Olay işleyicilerini ekle
    }

    private void attachEventHandlers() {
        Hexagon[][] hexagons = view.getHexagons();
        for (int row = 0; row < model.getBoardSize(); row++) {
            for (int col = 0; col < model.getBoardSize(); col++) {
                Hexagon hexagon = view.getHexagons()[row][col];
                hexagon.setOnMouseClicked(event -> handleHexagonClick(hexagon));
            }
        }
    }

    private void handleHexagonClick(Hexagon hexagon) {
        int row = hexagon.getRow();
        int col = hexagon.getCol();

        if (model.isValidMove(row, col)) {
            model.makeMove(hexagon.getRow(), hexagon.getCol());
            view.updateView(); // Görünümü güncelle

            if (model.checkWinCondition(model.getCurrentPlayer())) {
                // Oyun bitti, kazananı ilan et
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Oyun Bitti!");
                alert.setHeaderText(null);
                alert.setContentText(model.getCurrentPlayer() + " kazandı!");
                alert.showAndWait();
                model.resetGame();
                view.updateView();
                updateTurnInfo();
            } else {
                // Oyun devam ediyor, oyuncu bilgilerini güncelle
                updateTurnInfo();
            }
        }
    }

    private void updateTurnInfo() {
        turnLabel.setText("Turn: " + model.getCurrentPlayer());
        redMovesLabel.setText("RED: " + model.getRedMoves());
        blueMovesLabel.setText("BLUE: " + model.getBlueMoves());
    }
    public void handleStartButtonClick() {
        // Oyunu sıfırla
        model.resetGame();
        view.updateView();
        updateTurnInfo();

        // (İsteğe bağlı) Oyuncu seçimi veya oyun ayarlarını yapılandırma kodları
    }
}
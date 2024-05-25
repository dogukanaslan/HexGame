package com.example.demo3;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class GameController {
    private GameModel model;
    private BoardView view;
    private Label turnLabel;
    private Label redMovesLabel;
    private Label blueMovesLabel;

    public GameController(GameModel model, BoardView view,Label turnLabel , Label redMovesLabel, Label blueMovesLabel) {
        this.model = model;
        this.view = view;
        this.turnLabel = turnLabel;
        this.redMovesLabel = redMovesLabel;
        this.blueMovesLabel = blueMovesLabel;
        attachEventHandlers(); // Olay işleyicilerini ekle
    }

    private void attachEventHandlers() {
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
            model.makeMove(row, col);
            view.updateView(); // Görünümü güncelle

            if (model.checkWinCondition(Player.RED)) {
                // Oyun bitti, kazananı ilan et
                WinAlert(Player.RED);
            }else if(model.checkWinCondition(Player.BLUE)){
                // Oyun bitti, kazananı ilan et
                WinAlert(Player.BLUE);
            }else{
                // Oyun devam ediyor, oyuncu bilgilerini güncelle
                updateTurnInfo();
            }
        }
    }
    public void WinAlert(Player player){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oyun Bitti!");
        alert.setHeaderText(null);
        alert.setContentText(player + " kazandı!");
        alert.showAndWait();
        model.resetGame();
        view.updateView();
        updateTurnInfo();
    }
    private void updateTurnInfo() {
        turnLabel.setText("Turn: " + model.getCurrentPlayer());
        redMovesLabel.setText("RED: " + model.getRedMoves());
        blueMovesLabel.setText("BLUE: " + model.getBlueMoves());
    }
    public void handleResetButtonClick() {
        // Oyunu sıfırla
        model.resetGame();
        view.updateView();
        updateTurnInfo();

    }
}
package com.example.demo3;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;

public class BoardView extends Pane {
    private GameModel model;
    private Hexagon[][] hexagons;

    public BoardView(GameModel model) {
        this.model = model;
        this.hexagons = new Hexagon[model.getBoardSize()][model.getBoardSize()];
        createBoard();
    }

    private void createBoard() {
        double hexagonRadius = 30.0; // Altıgenlerin yarıçapını belirle
        double h = Math.sqrt(3) * hexagonRadius / 2; // Altıgenin yüksekliğini hesapla

        for (int row = 0; row < model.getBoardSize(); row++) {
            for (int col = 0; col < model.getBoardSize(); col++) {
                Hexagon hexagon = new Hexagon(row, col, hexagonRadius);
                hexagons[row][col] = hexagon;

                // Altıgenin merkez noktasını hesapla
                double centerX = col * 2 * h +row * h;
                double centerY = row * 1.5 * hexagonRadius;

                // Altıgeni Pane'e ekle ve konumunu ayarla
                getChildren().add(hexagon);
                hexagon.setLayoutX(centerX);
                hexagon.setLayoutY(centerY);
            }
        }

        // Kenar boşluklarını ayarla (ihtiyaca göre)
        setPadding(new Insets(hexagonRadius * 2));
    }

    public void updateView() {
        for (int row = 0; row < model.getBoardSize(); row++) {
            for (int col = 0; col < model.getBoardSize(); col++) {
                hexagons[row][col].setOwner(model.getBoard()[row][col].getOwner());
            }
        }
    }

    public Hexagon[][] getHexagons() {
        return hexagons;
    }

    public void setHexagons(Hexagon[][] hexagons) {
        this.hexagons = hexagons;

    }
}
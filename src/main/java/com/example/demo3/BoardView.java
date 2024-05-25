package com.example.demo3;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
                Hexagon hexagon = new Hexagon(row, col, hexagonRadius,model);
                if(row==0|| row==model.getBoardSize()-1){
                    hexagon.paintEdge(1, Color.BLUE);
                    hexagon.paintEdge(0, Color.BLUE);
                } else if (col==0|| col==model.getBoardSize()-1) {
                    hexagon.paintEdge(2, Color.RED);
                    hexagon.paintEdge(4, Color.RED);
                }
                hexagons[row][col] = hexagon;

                // Altıgenin merkez noktasını hesapla
                double centerX = col * 2 * h +row * h;
                double centerY = row * 1.5 * hexagonRadius;

                FadeTransition ft = new FadeTransition(Duration.millis(500), hexagon);
                ft.setFromValue(0); // Başlangıçta görünmez
                ft.setToValue(1); // Sonunda tamamen görünür
                ft.setDelay(Duration.millis(30 * (row * model.getBoardSize() + col))); // Kademeli gecikme
                ft.play();
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
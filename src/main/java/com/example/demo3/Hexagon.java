package com.example.demo3;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Hexagon extends StackPane {
    private static final double SIZE = 30; // Altıgen boyutu (ihtiyaca göre ayarlayın)
    private int row;
    private int col;
    private Player owner;
    private Polygon hexagonShape;

    public Hexagon(int row, int col, double hexagonRadius) {
        this.row = row;
        this.col = col;
        this.owner = null; // Başlangıçta sahipsiz
        createHexagonShape(); // Altıgen şeklini oluştur
        getChildren().add(hexagonShape); // Şekli StackPane'e ekle
        setOnMouseClicked(this::handleMouseClick); // Tıklama olay işleyicisi ekle
    }

    private void createHexagonShape() {
        double[] points = new double[12]; // 6 köşe, her biri için 2 koordinat (x, y)
        for (int i = 0; i < 6; i++) {
            double angle = 2 * Math.PI / 6 * (i + 0.5);
            points[2 * i] = SIZE * Math.cos(angle);
            points[2 * i + 1] = SIZE * Math.sin(angle);
        }
        hexagonShape = new Polygon(points);
        hexagonShape.setFill(Color.LIGHTGRAY); // Başlangıç dolgu rengi
        hexagonShape.setStroke(Color.BLACK); // Kenarlık rengi
    }

    private void handleMouseClick(MouseEvent event) {
        // GameController'a tıklama olayı hakkında bilgi ver
        // Örnek:
        // GameController.getInstance().handleHexagonClick(this);
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        updateAppearance(); // Görünümü sahibine göre güncelle
    }

    private void updateAppearance() {
        if (owner == Player.RED) {
            hexagonShape.setFill(Color.RED);
        } else if (owner == Player.BLUE) {
            hexagonShape.setFill(Color.BLUE);
        } else {
            hexagonShape.setFill(Color.LIGHTGRAY); // Sahipsiz ise gri yap
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    // İhtiyaç duyulursa row, col ve owner için getter metotları eklenebilir
}
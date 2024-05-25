package com.example.demo3;
import javafx.scene.shape.Line;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class Hexagon extends StackPane {
    private static final double SIZE = 30; // Altıgen boyutu (ihtiyaca göre ayarlayın)
    private int row;
    private int col;
    private Player owner;
    private Polygon hexagonShape;
    private GameModel model;
    private double[] points;

    public Hexagon(int row, int col, double hexagonRadius, GameModel model) {
        this.model = model;
        this.row = row;
        this.col = col;
        this.owner = null; // Başlangıçta sahipsiz
        createHexagonShape(); // Altıgen şeklini oluştur
        getChildren().add(hexagonShape); // Şekli StackPane'e ekle

    }

    private void createHexagonShape() {
        points = new double[12]; // 6 köşe, her biri için 2 koordinat (x, y)
        for (int i = 0; i < 6; i++) {
            double angle = 2 * Math.PI / 6 * (i + 0.5);
            points[2 * i] = SIZE * Math.cos(angle);
            points[2 * i + 1] = SIZE * Math.sin(angle);
        }
        hexagonShape = new Polygon(points);
        hexagonShape.setFill(Color.LIGHTGRAY); // Başlangıç dolgu rengi
        hexagonShape.setStroke(Color.BLACK);
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

    public void paintEdge(int edgeIndex, Color color) {
        if (edgeIndex < 0 || edgeIndex >= 6) {
            throw new IllegalArgumentException("Edge index must be between 0 and 5");
        }
        double startX = points[2 * edgeIndex];
        double startY = points[2 * edgeIndex + 1];
        double endX = points[2 * ((edgeIndex + 1) % 6)];
        double endY = points[2 * ((edgeIndex + 1) % 6) + 1];
        Line edge = new Line(startX, startY, endX, endY);
        edge.setStroke(color);
        edge.setStrokeWidth(3); // Set the width of the edge line
        getChildren().add(edge); // Add the edge line to the StackPane

        if(row==0){
            if(edgeIndex==1){
                edge.setTranslateX(hexagonShape.getLayoutBounds().getWidth() / 5);
                edge.setTranslateY(hexagonShape.getLayoutBounds().getHeight() / -2.6);

            } else if (edgeIndex==0) {
                edge.setTranslateX(hexagonShape.getLayoutBounds().getWidth() / -5);
                edge.setTranslateY(hexagonShape.getLayoutBounds().getHeight() / -2.6);
            }

        }
        else if(row==model.getBoardSize()-1){
            if(edgeIndex==1){
                edge.setTranslateX(hexagonShape.getLayoutBounds().getWidth() / -5);
                edge.setTranslateY(hexagonShape.getLayoutBounds().getHeight() / 2.6);
            } else if (edgeIndex==0) {
                edge.setTranslateX(hexagonShape.getLayoutBounds().getWidth() / 5);
                edge.setTranslateY(hexagonShape.getLayoutBounds().getHeight() / 2.6);
            }
        }
        if(col==0){
            if(edgeIndex==2){
                edge.setTranslateX(hexagonShape.getLayoutBounds().getWidth() / -2);
                edge.setTranslateY(hexagonShape.getLayoutBounds().getHeight() / 20);

            } else if (edgeIndex==4) {
                edge.setTranslateX(hexagonShape.getLayoutBounds().getWidth() / -5);
                edge.setTranslateY(hexagonShape.getLayoutBounds().getHeight() / 2.6);
            }

        }
        else if(col==model.getBoardSize()-1){
            if(edgeIndex==2){
                edge.setTranslateX(hexagonShape.getLayoutBounds().getWidth() / 2);
                edge.setTranslateY(hexagonShape.getLayoutBounds().getHeight() / 20);
            } else if (edgeIndex==4) {
                edge.setTranslateX(hexagonShape.getLayoutBounds().getWidth() / 5);
                edge.setTranslateY(hexagonShape.getLayoutBounds().getHeight() / -2.6);
            }
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
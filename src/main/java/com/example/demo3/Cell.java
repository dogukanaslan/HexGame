package com.example.demo3;

public class Cell {
    private int row;
    private int col;
    private Player owner;
    private boolean visited; // Yeni değişken

    public Cell(int row, int col, Player owner) {
        this.row = row;
        this.col = col;
        this.owner = owner;
        this.visited = false; // Başlangıçta ziyaret edilmemiş olarak ayarla
    }

    // ... diğer getter ve setter metotları

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
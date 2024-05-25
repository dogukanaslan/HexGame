package com.example.demo3;

public class GameModel {
    private int boardSize;
    private Player currentPlayer;
    private Cell[][] board;
    private int redMoves = 0;
    private int blueMoves = 0;

    public GameModel(int boardSize) {
        this.boardSize = boardSize;
        this.currentPlayer = Player.RED; // Kırmızı oyuncu ile başla
        this.board = new Cell[boardSize][boardSize];
        initializeBoard(); // Tahtayı başlat
    }

    private void initializeBoard() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = new Cell(row, col, null); // Başlangıçta tüm hücreler boş
            }
        }
    }

    public boolean isValidMove(int row, int col) {

        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
            return false; // Tahta sınırları dışında
        }
        return board[row][col].getOwner() == null; // Hücre boş olmalı
    }

    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col].setOwner(currentPlayer);
            if (currentPlayer == Player.RED) {
                redMoves++;
            } else {
                blueMoves++;
            }
            currentPlayer = (currentPlayer == Player.RED) ? Player.BLUE: Player.RED; // Oyuncu değiştir
        }
    }

    public boolean checkWinCondition(Player player) {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                cell.setVisited(false); // Tüm hücreleri ziyaret edilmemiş olarak ayarla
            }
        }
        // Oyuncunun başlangıç kenarındaki tüm hücreleri kontrol et
        for (int i = 0; i < boardSize; i++) {
            if (player == Player.RED && board[i][0].getOwner() == player && dfs(i, 0, player)) {
                return true; // Kırmızı oyuncu sol kenardan sağ kenara ulaştı
            } else if (player == Player.BLUE && board[0][i].getOwner() == player && dfs(0, i, player)) {
                return true; // Mavi oyuncu üst kenardan alt kenara ulaştı
            }
        }
        return false; // Kazanan bulunamadı
    }

    private boolean dfs(int row, int col, Player player) {
        if ((player == Player.RED && col == boardSize - 1) || (player == Player.BLUE && row == boardSize - 1)) {
            return true; // Oyuncu karşı kenara ulaştı
        }

        // Ziyaret edildiğini işaretle
        board[row][col].setVisited(true);

        // Komşu hücreleri kontrol et (6 yön)
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0 || // Aynı hücreyi kontrol etme
                        row + i < 0 || row + i >= boardSize || col + j < 0 || col + j >= boardSize || // Sınır kontrolü
                        board[row + i][col + j].getOwner() != player || // Farklı oyuncuya ait hücre
                        board[row + i][col + j].isVisited()) { // Zaten ziyaret edilmiş hücre
                    continue;
                }

                if (dfs(row + i, col + j, player)) {
                    return true; // Karşı kenara ulaşıldı
                }
            }
        }

        return false; // Karşı kenara ulaşılamadı
    }
    public void resetGame() {
        currentPlayer = Player.RED; // Başlangıç oyuncuyu ayarla
        for (Cell[] row : board) {
            for (Cell cell : row) {
                cell.setOwner(null); // Hücreleri temizle
                cell.setVisited(false); // Ziyaret durumunu sıfırla
            }
        }
        redMoves = 0;
        blueMoves = 0;
    }

    // Getter metotları (ihtiyaca göre)
    public int getBoardSize() {
        return boardSize;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public int getRedMoves() {
        return redMoves;
    }

    public int getBlueMoves() {
        return blueMoves;
    }


}

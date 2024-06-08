/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author furkanbalaban
 */

import java.util.*;

public class Character {
    private int row;
    private int column;
    private int[][] matrix;
    private ArrayList<SabitEngel> engeller;

    public Character(int[][] matrix, ArrayList<SabitEngel> engeller) {
        this.matrix = matrix;
        this.engeller = engeller;
    }

    public void findTreasure() {
        // Hazine bulunacak hücrenin koordinatları
        int treasureRow = matrix.length - 1;
        int treasureColumn = matrix[0].length - 1;

        // En kısa yolun hesaplanması
        ArrayList<int[]> shortestPath = calculateShortestPath(treasureRow, treasureColumn);

        // Karakterin hareket etmesi
        for (int[] cell : shortestPath) {
            // Engelleri kontrol et ve hareket et
            if (!isCollision(cell[0], cell[1])) {
                move(cell[0], cell[1]);
            }
        }
    }

    private ArrayList<int[]> calculateShortestPath(int targetRow, int targetColumn) {
        // BFS algoritması kullanarak en kısa yolun hesaplanması
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        queue.offer(new int[]{row, column});
        visited[row][column] = true;

        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();
            int currentRow = currentCell[0];
            int currentColumn = currentCell[1];

            if (currentRow == targetRow && currentColumn == targetColumn) {
                // Hedef hücreye ulaşıldı, en kısa yol bulundu
                ArrayList<int[]> path = new ArrayList<>();
                path.add(new int[]{currentRow, currentColumn});
                while (currentRow != row || currentColumn != column) {
                    for (int[] neighbor : getNeighbors(currentRow, currentColumn)) {
                        int neighborRow = neighbor[0];
                        int neighborColumn = neighbor[1];
                        if (visited[neighborRow][neighborColumn] && isAdjacent(currentRow, currentColumn, neighborRow, neighborColumn)) {
                            path.add(0, new int[]{neighborRow, neighborColumn});
                            currentRow = neighborRow;
                            currentColumn = neighborColumn;
                            break;
                        }
                    }
                }
                return path;
            }

            for (int[] neighbor : getNeighbors(currentRow, currentColumn)) {
                int neighborRow = neighbor[0];
                int neighborColumn = neighbor[1];
                if (!visited[neighborRow][neighborColumn] && !isCollision(neighborRow, neighborColumn)) {
                    queue.offer(neighbor);
                    visited[neighborRow][neighborColumn] = true;
                }
            }
        }

        // Hedefe ulaşılamadı
        return new ArrayList<>();
    }

    private ArrayList<int[]> getNeighbors(int currentRow, int currentColumn) {
        // Komşu hücrelerin koordinatlarını döndürür
        ArrayList<int[]> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Yukarı, aşağı, sol, sağ

        for (int[] dir : directions) {
            int newRow = currentRow + dir[0];
            int newColumn = currentColumn + dir[1];
            if (isValidCell(newRow, newColumn)) {
                neighbors.add(new int[]{newRow, newColumn});
            }
        }
        return neighbors;
    }

    private boolean isValidCell(int row, int column) {
        // Geçerli bir hücre mi kontrol eder
        return row >= 0 && row < matrix.length && column >= 0 && column < matrix[0].length;
    }

    private boolean isAdjacent(int row1, int column1, int row2, int column2) {
        // İki hücre birbirine bitişik mi kontrol eder
        return Math.abs(row1 - row2) + Math.abs(column1 - column2) == 1;
    }

    private boolean isCollision(int row, int column) {
        // Verilen koordinatlarda engel var mı kontrol eder
        for (SabitEngel engel : engeller) {
            if (engel.checkCollision(row, column)) {
                return true;
            }
        }
        return false;
    }

    private void move(int newRow, int newColumn) {
        // Karakterin yeni konumunu ayarlar
        row = newRow;
        column = newColumn;
    }
}
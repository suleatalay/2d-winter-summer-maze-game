/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author furkanbalaban
 */
import java.util.*;
public class ShortestPathFinder {
    private int[][] grid;
    private int numRows;
    private int numColumns;
    private Treasure treasure;

    public ShortestPathFinder(int[][] grid, Treasure treasure) {
        this.grid = grid;
        this.numRows = grid.length;
        this.numColumns = grid[0].length;
        this.treasure = treasure;
    }

    // A* algoritmasını uygulayan metot
    public List<Node> findShortestPath(Node startNode) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getFCost));
        Set<Node> closedSet = new HashSet<>();
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();

            if (currentNode.row == treasure.getRow() && currentNode.column == treasure.getColumn()) {
                // Hedef düğüme ulaşıldı, en kısa yol bulundu
                return reconstructPath(currentNode);
            }

            closedSet.add(currentNode);

            // Komşu düğümleri işle
            for (Node neighbor : getNeighbors(currentNode)) {
                if (closedSet.contains(neighbor)) {
                    continue; // Bu komşu zaten işlendi
                }

                int newGCost = currentNode.gCost + 1; // Komşuya olan maliyet
                if (newGCost < neighbor.gCost || !openSet.contains(neighbor)) {
                    neighbor.gCost = newGCost;
                    neighbor.hCost = calculateHCost(neighbor);
                    neighbor.parent = currentNode;

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null; // Hedefe ulaşılamadı
    }

    // Komşu düğümleri al
    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Aşağı, yukarı, sağ, sol

        for (int[] dir : directions) {
            int newRow = node.row + dir[0];
            int newColumn = node.column + dir[1];

            // Geçerli bir konum mu kontrol et
            if (isValidLocation(newRow, newColumn) && grid[newRow][newColumn] != 1) {
                neighbors.add(new Node(newRow, newColumn, 0, 0, null));
            }
        }

        return neighbors;
    }

    // Hedef düğüme olan tahmini maliyeti hesapla (Manhattan mesafesi kullanılabilir)
    private int calculateHCost(Node node) {
        return Math.abs(node.row - treasure.getRow()) + Math.abs(node.column - treasure.getColumn());
    }

    // Yolu yeniden oluştur
    private List<Node> reconstructPath(Node endNode) {
        List<Node> path = new ArrayList<>();
        Node currentNode = endNode;

        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }

        Collections.reverse(path);
        return path;
    }

    // Geçerli bir konum mu kontrol et
    private boolean isValidLocation(int row, int column) {
        return row >= 0 && row < numRows && column >= 0 && column < numColumns;
    }
}

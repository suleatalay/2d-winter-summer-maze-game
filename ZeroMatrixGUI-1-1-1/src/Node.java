/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author furkanbalaban
 */
public class Node {
    int row;
    int column;
    int gCost; // Başlangıç düğümünden bu düğüme olan toplam maliyet
    int hCost; // Bu düğümden hedef düğüme olan tahmini maliyet
    Node parent; // Yolu bulmak için bir önceki düğüm

    public Node(int row, int column, int gCost, int hCost, Node parent) {
        this.row = row;
        this.column = column;
        this.gCost = gCost;
        this.hCost = hCost;
        this.parent = parent;
    }

    public int getFCost() {
        return gCost + hCost;
    }
}

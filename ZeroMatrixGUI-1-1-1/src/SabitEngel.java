/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author furkanbalaban
 */
import java.awt.*;

public abstract class SabitEngel {
    protected int row;
    protected int column;
    protected int size;

    public SabitEngel(int row, int column, int size) {
        this.row = row;
        this.column = column;
        this.size = size;
    }

    public void draw(Graphics g, int cellWidth, int cellHeight) {
        // Sabit engeli çizmek için gerekli kodlar buraya gelecek
    }

    protected void drawLabel(Graphics g, int cellWidth, int cellHeight, String label) {
        g.setColor(Color.BLACK);
        g.drawString(label, column * cellWidth + cellWidth / 2 - 5, row * cellHeight + cellHeight / 2 + 5);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    boolean checkCollision(int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.*;

public class KayaEngeli extends SabitEngel {
    public KayaEngeli(int row, int column, int size) {
        super(row, column, size);
    }

   @Override
    public void draw(Graphics g, int cellWidth, int cellHeight) {
        g.setColor(Color.DARK_GRAY);
      g.fillRect(column * cellWidth, row * cellHeight, size * cellWidth, size * cellHeight);
    
    drawLabel(g, cellWidth, cellHeight, "K");
    }
    
}

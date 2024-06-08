
import java.awt.Color;
import java.awt.Graphics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author furkanbalaban
 */
public class Ari extends SabitEngel{
    
    public Ari(int row,int column,int size)
    {
      super(row,column,size);
    
    }
     @Override
    public void draw(Graphics g, int cellWidth, int cellHeight) {
        g.setColor(Color.PINK);
          g.fillRect(column * cellWidth, row * cellHeight, size * cellWidth, cellHeight);
      drawLabel(g, cellWidth, cellHeight, "G");
    }
}

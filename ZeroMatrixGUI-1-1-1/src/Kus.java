
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
public class Kus extends SabitEngel{
    public Kus(int row,int column,int size)
    {
    super(row,column,size);
       }
    
    @Override
    public void draw(Graphics g, int cellWidth, int cellHeight) {
       g.setColor(Color.cyan);
        g.fillRect(column * cellWidth, row * cellHeight, cellWidth * 2, cellHeight * 2);
        drawLabel(g, cellWidth, cellHeight, "B");
    }
}

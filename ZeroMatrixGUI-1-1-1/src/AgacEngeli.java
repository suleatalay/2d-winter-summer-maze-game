import java.awt.*;

public class AgacEngeli extends SabitEngel {
    public AgacEngeli(int row, int column, int size) {
        super(row, column, size);
    }

    @Override
    public void draw(Graphics g, int cellWidth, int cellHeight) {
        g.setColor(Color.GREEN);
        g.fillRect(column * cellWidth, row * cellHeight, size * cellWidth, size * cellHeight);
    drawLabel(g, cellWidth, cellHeight, "A");

    }
}

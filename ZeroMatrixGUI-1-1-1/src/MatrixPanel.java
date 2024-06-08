import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class MatrixPanel extends JPanel {
    private int[][] matrix;
    private int treasureHunterRow;
    private int treasureHunterColumn;
    private ArrayList<SabitEngel> engeller;
    private BufferedImage duvarImage; 
    private BufferedImage agacImage;
    private BufferedImage dagImage;
    private BufferedImage kayaImage;
    private BufferedImage ariImage;
    private BufferedImage kusImage;
   
    public MatrixPanel(int[][] matrix) {
        this.matrix = matrix;
        if (matrix.length == 0) {
            this.matrix = new int[1][1];
        }
        System.out.println("KeyListener added to MatrixPanel");
         try {
            agacImage = ImageIO.read(new File("agac.png")); // Ağaç görseli dosyası
            duvarImage = ImageIO.read(new File("duvar.png")); // Duvar görseli dosyası
           kayaImage = ImageIO.read(new File("kaya.png"));
            dagImage = ImageIO.read(new File("dag.png"));
            ariImage = ImageIO.read(new File("ari.png"));
            kusImage = ImageIO.read(new File("kus.png"));
// Diğer engel görselleri de burada yüklenebilir
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
        repaint();
    }

    public void setEngeller(ArrayList<SabitEngel> engeller) {
        this.engeller = engeller;
        repaint();
    }

    public void setTreasureHunterPosition(int row, int column) {
        this.treasureHunterRow = row;
        this.treasureHunterColumn = column;
        repaint();
    }

    public int getTreasureHunterRow() {
        return treasureHunterRow;
    }

    public int getTreasureHunterColumn() {
        return treasureHunterColumn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellWidth = getWidth() / matrix[0].length;
        int cellHeight = getHeight() / matrix.length;

        // Matrisi çiz
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int cellValue = matrix[i][j];
                Color cellColor = getColorForCellPosition(i, j);
                g.setColor(cellColor);
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }

       if (engeller != null) {
    for (SabitEngel engel : engeller) {
        int engelRow = engel.getRow();
        int engelColumn = engel.getColumn();
        int engelSize = engel.getSize();

        // Engelin tipine göre görseli çiz
        if (engel instanceof AgacEngeli) {
            g.drawImage(agacImage, engelColumn * cellWidth, engelRow * cellHeight, engelSize * cellWidth, engelSize * cellHeight, null);
        } else if (engel instanceof DuvarEngeli) {
            g.drawImage(duvarImage, engelColumn * cellWidth, engelRow * cellHeight, engelSize * cellWidth, cellHeight, null);
        
        } else if (engel instanceof KayaEngeli) {
    g.drawImage(kayaImage, engelColumn * cellWidth, engelRow * cellHeight, engelSize * cellWidth, engelSize * cellHeight, null);
            // KayaEngeli görseli çizimi
    // Örneğin: g.drawImage(kayaImage, engelColumn * cellWidth, engelRow * cellHeight, engelSize * cellWidth, engelSize * cellHeight, null);
} else if (engel instanceof DagEngeli) {
   g.drawImage(dagImage, engelColumn * cellWidth, engelRow * cellHeight, engelSize * cellWidth, engelSize * cellHeight, null);
    // DagEngeli görseli çizimi
    // Örneğin: g.drawImage(dagImage, engelColumn * cellWidth, engelRow * cellHeight, engelSize * cellWidth, cellHeight, null);
} else if (engel instanceof Ari) {
    g.drawImage(ariImage, engelColumn * cellWidth, engelRow * cellHeight, engelSize * cellWidth, engelSize * cellHeight, null);
    // AriEngeli görseli çizimi
    // Örneğin: g.drawImage(ariImage, engelColumn * cellWidth, engelRow * cellHeight, engelSize * cellWidth, engelSize * cellHeight, null);
} else if (engel instanceof Kus) {
    g.drawImage(kusImage, engelColumn * cellWidth, engelRow * cellHeight, engelSize * cellWidth, engelSize * cellHeight, null);
    // KusEngeli görseli çizimi
    // Örneğin: g.drawImage(kusImage, engelColumn * cellWidth, engelRow * cellHeight, engelSize * cellWidth, engelSize * cellHeight, null);
}
        // Diğer engel türleri için de benzer şekilde görsel çizimi yapılabilir
    }
}

        // Hazine avcısını çiz
        g.setColor(Color.RED);
        g.fillOval(treasureHunterColumn * cellWidth, treasureHunterRow * cellHeight, cellWidth, cellHeight);
    }

    private Color getColorForCellPosition(int row, int col) {
        int midCol = matrix[0].length / 2;
        if (col < midCol) {
            return getWinterColor(matrix[row][col]);
        } else {
            return getSummerColor(matrix[row][col]);
        }
    }

    private Color getWinterColor(int value) {
        switch (value) {
            case 0:
                return new Color(192, 192, 192);
            case 1:
                return Color.BLUE;
            case 2:
                return Color.CYAN;
            case 3:
                return Color.WHITE;
            default:
                return Color.BLACK;
        }
    }

    private Color getSummerColor(int value) {
        switch (value) {
            case 0:
                return new Color(255, 255, 153);
            case 1:
                return new Color(34, 139, 34);
            case 2:
                return new Color(0, 128, 0);
            case 3:
                return new Color(255, 215, 0);
            default:
                return Color.BLACK;
        }
    }
}

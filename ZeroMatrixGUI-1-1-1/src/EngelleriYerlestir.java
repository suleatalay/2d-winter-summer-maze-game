import java.util.ArrayList;
import java.util.Random;

public class EngelleriYerlestir {
    private int[][] engelMatrisi;
    private int rows;
    private int columns;

    public EngelleriYerlestir(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.engelMatrisi = new int[rows][columns];
    }

    public ArrayList<SabitEngel> engelleriYerlestir() {
        ArrayList<SabitEngel> engeller = new ArrayList<>();
        Random rand = new Random();

        // Ağaç engelleri
        for (int i = 0; i < 5; i++) {
            int size = rand.nextInt(3) + 2; // 2*2, 3*3, 4*4
            int row = rand.nextInt(rows - size + 1);
            int column = rand.nextInt(columns - size + 1);

            if (!checkCollision(row, column, size)) {
                AgacEngeli agacEngeli = new AgacEngeli(row, column, size);
                engeller.add(agacEngeli);
                markOccupiedCells(row, column, size);
            }
        }

        // Duvar engelleri
        for (int i = 0; i < 5; i++) {
            int size = 10;
            int row = rand.nextInt(rows);
            int column = rand.nextInt(columns - size + 1);

            if (!checkCollision(row, column, size)) {
                DuvarEngeli duvarEngeli = new DuvarEngeli(row, column, size);
                engeller.add(duvarEngeli);
                markOccupiedCells(row, column, 1, size);
            }
        }

        // Kaya engelleri
        for (int i = 0; i < 5; i++) {
            int size = rand.nextInt(2) + 2; // 2*2, 3*3
            int row = rand.nextInt(rows - size + 1);
            int column = rand.nextInt(columns - size + 1);

            if (!checkCollision(row, column, size)) {
                KayaEngeli kayaEngeli = new KayaEngeli(row, column, size);
                engeller.add(kayaEngeli);
                markOccupiedCells(row, column, size);
            }
        }

        // Dağ engeli
        int row = rand.nextInt(rows);
        int column = rand.nextInt(columns - 15 + 1);
        if (!checkCollision(row, column, 15)) {
            DagEngeli dagEngeli = new DagEngeli(row, column, 15);
            engeller.add(dagEngeli);
            markOccupiedCells(row, column, 1, 15);
        }

        // Arı engelleri
        for (int i = 0; i < 5; i++) {
            int size = 2; // Boyut sabit olarak 2*2
            int arıRow = rand.nextInt(rows - size + 1);
            int arıColumn = rand.nextInt(columns - size + 1);

            if (!checkCollision(arıRow, arıColumn, size)) {
                Ari ariEngeli = new Ari(arıRow, arıColumn, size);
                engeller.add(ariEngeli);
                markOccupiedCells(arıRow, arıColumn, size);
            }
        }

        // Kuş engelleri
        for (int i = 0; i < 5; i++) {
            int size = 2; // Boyut sabit olarak 2*2
            int kuşRow = rand.nextInt(rows - size + 1);
            int kuşColumn = rand.nextInt(columns - size + 1);

            if (!checkCollision(kuşRow, kuşColumn, size)) {
                Kus kusEngeli = new Kus(kuşRow, kuşColumn, size);
                engeller.add(kusEngeli);
                markOccupiedCells(kuşRow, kuşColumn, size);
            }
        }

        return engeller;
    }

    private boolean checkCollision(int row, int column, int size) {
        for (int i = row; i < row + size; i++) {
            for (int j = column; j < column + size; j++) {
                if (i < 0 || i >= rows || j < 0 || j >= columns || engelMatrisi[i][j] == 1) {
                    return true; // Çakışma var
                }
            }
        }
        return false; // Çakışma yok
    }

    private void markOccupiedCells(int row, int column, int size) {
        for (int i = row; i < row + size; i++) {
            for (int j = column; j < column + size; j++) {
                engelMatrisi[i][j] = 1; // Hücreyi işgal et
            }
        }
    }

    private void markOccupiedCells(int row, int column, int height, int width) {
        for (int i = row; i < row + height; i++) {
            for (int j = column; j < column + width; j++) {
                engelMatrisi[i][j] = 1; // Hücreyi işgal et
            }
        }
    }
}

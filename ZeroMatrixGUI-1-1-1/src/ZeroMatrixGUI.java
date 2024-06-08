import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ZeroMatrixGUI extends JFrame {
    private JTextField rowsField;
    private JTextField columnsField;
    private JButton createMatrixButton;
    private JTextArea matrixArea;
    private MatrixPanel matrixPanel;
    private int[][] matrix;
    private TreasureHunter treasureHunter;

    public ZeroMatrixGUI() {
        setTitle("Zero Matrix Generator");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel rowsLabel = new JLabel("Rows:");
        rowsField = new JTextField(5);
        JLabel columnsLabel = new JLabel("Columns:");
        columnsField = new JTextField(5);
        createMatrixButton = new JButton("Create Matrix");

        matrixArea = new JTextArea();
        matrixPanel = new MatrixPanel(new int[0][0]);

        JPanel inputPanel = new JPanel();
        inputPanel.add(rowsLabel);
        inputPanel.add(rowsField);
        inputPanel.add(columnsLabel);
        inputPanel.add(columnsField);
        inputPanel.add(createMatrixButton);

        createMatrixButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createZeroMatrix();
                fillRandomWithNumbers();
            }
        });

        rowsField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createZeroMatrix();
                fillRandomWithNumbers();
            }
        });

        columnsField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createZeroMatrix();
                fillRandomWithNumbers();
            }
        });

        treasureHunter = new TreasureHunter();

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(matrixPanel, BorderLayout.CENTER);
        add(treasureHunter, BorderLayout.SOUTH);

        matrixPanel.setFocusable(true);
        matrixPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                moveTreasureHunter(e);
            }
        });
    }

    private void createZeroMatrix() {
        try {
            int rows = Integer.parseInt(rowsField.getText());
            int columns = Integer.parseInt(columnsField.getText());

            matrix = new int[rows][columns];

            StringBuilder matrixBuilder = new StringBuilder();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = 0;
                    matrixBuilder.append("0 ");
                }
                matrixBuilder.append("\n");
            }

            matrixArea.setText(matrixBuilder.toString());
            matrixPanel.setMatrix(matrix);

            // Engelleri yerleştir
            EngelleriYerlestir engelleriYerlestir = new EngelleriYerlestir(rows, columns);
            ArrayList<SabitEngel> engeller = engelleriYerlestir.engelleriYerlestir();
            matrixPanel.setEngeller(engeller);

            // Hazine avcısını başlangıç konumuna yerleştir
            matrixPanel.setTreasureHunterPosition(0, 0);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers for rows and columns.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillRandomWithNumbers() {
        if (matrix == null) {
            JOptionPane.showMessageDialog(this, "Please create a matrix first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Rastgele sayılarla matrisi doldur
        // Bu kısımda gerekli kodlar yer alacak
    }

    private void moveTreasureHunter(KeyEvent e) {
        System.out.println("moveTreasureHunter method called.");
        System.out.println("Key pressed: " + e.getKeyCode());
        int row = matrixPanel.getTreasureHunterRow();
        int column = matrixPanel.getTreasureHunterColumn();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                row = Math.max(row - 1, 0);
                break;
            case KeyEvent.VK_DOWN:
                row = Math.min(row + 1, matrix.length - 1);
                break;
            case KeyEvent.VK_LEFT:
                column = Math.max(column - 1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                column = Math.min(column + 1, matrix[0].length - 1);
                break;
        }

        // Hazine avcısının konumunu güncelle
        matrixPanel.setTreasureHunterPosition(row, column);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ZeroMatrixGUI().setVisible(true);
            }
        });
    }
}

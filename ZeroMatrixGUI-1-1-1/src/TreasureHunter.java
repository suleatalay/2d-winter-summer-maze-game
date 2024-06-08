
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TreasureHunter extends JPanel {
    private JLabel treasureLabel;

    public TreasureHunter() {
        setPreferredSize(new Dimension(600, 50)); // Bileşen boyutunu ayarla
        setBackground(Color.ORANGE); // Arka plan rengini ayarla

        treasureLabel = new JLabel("Treasure Hunter: Searching for treasures..."); // Başlangıç metni
        add(treasureLabel); // Etiketi bileşene ekle
    }
}
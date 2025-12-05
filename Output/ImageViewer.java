package Output;

import javax.swing.*;
import java.awt.*;


public class ImageViewer {
    public static void showSideBySide(String img1, String img2) {

        JFrame frame = new JFrame("AFN e AFD");

        // layout horizontal
        frame.setLayout(new GridLayout(1, 2));

        // cria os labels das imagens
        JLabel left = new JLabel(new ImageIcon(img1));
        JLabel right = new JLabel(new ImageIcon(img2));

        // centraliza
        left.setHorizontalAlignment(JLabel.CENTER);
        right.setHorizontalAlignment(JLabel.CENTER);

        // adiciona no frame
        frame.add(left);
        frame.add(right);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

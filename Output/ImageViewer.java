package Output;

import javax.swing.*;
import java.awt.*;

public class ImageViewer {

    public static void showStacked(String imgTop, String imgBottom) {

        JFrame frame = new JFrame("AFN e AFD");
        
        // agora 2 linhas e 1 coluna 
        frame.setLayout(new GridLayout(2, 1));

        // carregar imagens
        ImageIcon iconTop = new ImageIcon(imgTop);
        ImageIcon iconBottom = new ImageIcon(imgBottom);

        // labels
        JLabel topLabel = new JLabel(iconTop);
        JLabel bottomLabel = new JLabel(iconBottom);

        topLabel.setHorizontalAlignment(JLabel.CENTER);
        bottomLabel.setHorizontalAlignment(JLabel.CENTER);

        // cada uma dentro de um JScrollPane
        JScrollPane scrollTop = new JScrollPane(topLabel);
        JScrollPane scrollBottom = new JScrollPane(bottomLabel);

        scrollTop.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTop.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollBottom.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBottom.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // colocar no frame
        frame.add(scrollTop);
        frame.add(scrollBottom);

        // tamanho inicial da janela
        frame.setSize(1200, 1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

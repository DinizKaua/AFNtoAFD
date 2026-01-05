package Output;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageViewer {
    public static void showStacked(String imgTop, String imgBottom) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Visualização de Autômatos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel container = new JPanel(new GridLayout(2, 1, 0, 8));
            container.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

            // criar painéis de imagem (carregam as imagens internamente)
            JScrollPane topPane = criarScrollPaneComTitulo(loadImagePanel(imgTop), "AFN (Não Determinístico)");
            JScrollPane bottomPane = criarScrollPaneComTitulo(loadImagePanel(imgBottom), "AFD (Determinístico)");

            container.add(topPane);
            container.add(bottomPane);

            frame.add(container, BorderLayout.CENTER);

            // tamanho inicial razoável; pack para ajustar se imagens pequenas
            frame.setSize(1200, 1000);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JScrollPane criarScrollPaneComTitulo(JPanel imagePanel, String titulo) {
        JScrollPane scroll = new JScrollPane(imagePanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(new TitledBorder(titulo));
        return scroll;
    }

    private static JPanel loadImagePanel(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            // se falhar no carregamento, cria um painel com mensagem de erro
            return createErrorPanel("Erro ao carregar: " + path + " (" + e.getMessage() + ")");
        }

        return new ImagePanel(img);
    }

    private static JPanel createErrorPanel(String message) {
        JPanel p = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel("<html><body style='padding:8px;'>" + message + "</body></html>");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(lbl, BorderLayout.CENTER);
        p.setPreferredSize(new Dimension(400, 200));
        return p;
    }
    private static class ImagePanel extends JPanel {
        private final BufferedImage image;

        ImagePanel(BufferedImage image) {
            this.image = image;
            setBackground(Color.WHITE);
            // prefered size é o tamanho real da imagem; o JScrollPane usará isso para barras de rolagem
            setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // desenha a imagem no canto superior esquerdo (mantém resolução original)
            g.drawImage(image, 0, 0, this);
        }
    }
}

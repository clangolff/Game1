import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class PanelNoeud2 extends JPanel {

    private int w;
    private int h;

    ImagePanel perso;
    JPanel mainPanel;
    ImagePanel objet;
    JLabel titreVille;
    JPanel jNorth;
    JPanel jCenter;
    JPanel jSouth;

    public PanelNoeud2(int width, int height) {
        this.setLayout(null); // Utilisez setLayout(null) pour définir les positions manuellement
        this.setOpaque(false);

        this.w = width;
        this.h = height;

        perso = new ImagePanel("J1.png");
        perso.setOpaque(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false);

        objet = new ImagePanel("bouclier.png");
        objet.setOpaque(false);

        titreVille = new JLabel("blablablal");
        titreVille.setOpaque(false);

        jNorth = new JPanel();
        jNorth.setOpaque(false);

        jCenter = new JPanel();
        jCenter.setOpaque(false);
        jCenter.setLayout(new BorderLayout());

        jSouth = new JPanel();
        jSouth.setOpaque(false);

        configureLayout();
    }

    private void configureLayout() {
        this.w = this.getWidth();
        this.h = this.getHeight();

        System.out.println(this.w + " " + this.h);

        perso.setBounds(0, 0, w / 3, h);
        this.add(perso);

        mainPanel.setBounds(w / 3, 0, 2 * w / 3, h);
        this.add(mainPanel);

        titreVille.setBounds(w / 3, 0, 2 * w / 3, h / 8);
        mainPanel.add(titreVille, BorderLayout.NORTH);

        jSouth.setBounds(w / 3, getCenterPoint().y - h / 8, 2 * w / 3, h / 8);
        mainPanel.add(jSouth, BorderLayout.SOUTH);

        objet.setBounds(0, 0, w / 3, h / 2);
        jCenter.add(objet, BorderLayout.CENTER);

        jCenter.setBounds(w / 3, h / 8, 2 * w / 3, 7 * h / 8);
        mainPanel.add(jCenter, BorderLayout.CENTER);
    }

    public Point getCenterPoint() {
        int x = this.getX() + this.getWidth() / 2;
        int y = this.getY() + this.getHeight() / 2;
        return new Point(x, y);
    }

    public class Point {
        public int x;
        public int y;

        public Point(int X, int Y) {
            this.x = X;
            this.y = Y;
        }
    }

    public static void main(String args[]) {
        final int WIDTH = 800;
        final int HEIGHT = 600;

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WIDTH, HEIGHT);

        f.setLayout(null);
        Container c = f.getContentPane();

        for (int i = 0; i < 9; i++) {
            PanelNoeud pn = new PanelNoeud(WIDTH / 3, HEIGHT / 3); // Passer les dimensions correctes
            pn.setBounds((i % 3) * (WIDTH / 3), (i / 3) * (HEIGHT / 3), WIDTH / 3, HEIGHT / 3);
            c.add(pn);
        }
        f.setVisible(true);
	}
}

package gui;

import javax.swing.*;
import java.awt.*;

public class PageAcc extends JFrame {

	private static final long serialVersionUID = 1L;

	public PageAcc() {

        setTitle("Xiangqi Game");
        setSize(350,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titre = new JLabel("XIANGQI GAME");
        titre.setFont(new Font("Arial", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel version = new JLabel("(Version modifiée)");
        version.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton vsOrdinateur = new JButton("VS ORDINATEUR");
        vsOrdinateur.setAlignmentX(Component.CENTER_ALIGNMENT);
        vsOrdinateur.setMaximumSize(new Dimension(200,40));

        JButton vsAmis = new JButton("VS AMIS");
        vsAmis.setAlignmentX(Component.CENTER_ALIGNMENT);
        vsAmis.setMaximumSize(new Dimension(200,40));

        JLabel infoTitre = new JLabel("Informations :");
        infoTitre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel info1 = new JLabel("Plateau 11 x 11 avec zones spéciales");
        info1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel info2 = new JLabel("Règles personnalisées");
        info2.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(30));
        panel.add(titre);
        panel.add(version);
        panel.add(Box.createVerticalStrut(40));
        panel.add(vsOrdinateur);
        panel.add(Box.createVerticalStrut(20));
        panel.add(vsAmis);
        panel.add(Box.createVerticalStrut(40));
        panel.add(infoTitre);
        panel.add(info1);
        panel.add(info2);

        add(panel);
    }

    public static void main(String[] args) {
        new PageAcc().setVisible(true);
    }
}
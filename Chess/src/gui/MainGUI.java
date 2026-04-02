package gui;

import javax.swing.*;

import engine.process.GameManager;

public class MainGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    GameManager gameManager;
    GameDisplay display;
    public MainGUI(String title) {
        super(title);
        init();
    }
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setContentPane(new PageAcc(this));
        setVisible(true);
    } 

    public void lancerPartie() {
        gameManager = new GameManager(); 
        display = new GameDisplay(gameManager, null);
        setContentPane(display);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        revalidate();
        repaint();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGUI("Xiangqi Game");
        });
    }
    public void lancerPartieBot(engine.process.Niveau niveau) {

        engine.process.GameManager gameManager = new engine.process.GameManager();

        engine.process.Bot bot = new engine.process.Bot(niveau);

        GameDisplay display = new GameDisplay(gameManager, bot);

        setContentPane(display);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        revalidate();
        repaint();
    }
}

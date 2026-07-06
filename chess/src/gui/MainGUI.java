package gui;

import org.apache.log4j.Logger;

import javax.swing.*;

import engine.process.Bot;
import engine.process.GameManager;
import engine.process.Niveau;
import log.LoggerUtility;

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private GameManager gameManager;
    private GameDisplay display;

    public MainGUI(String title) {
        super(title);
        init();
    } 

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        setSize(600, 500);
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

    
    public void lancerPartieBot(Niveau niveau) {

        gameManager = new GameManager();
        Bot bot = new Bot(niveau);

        display = new GameDisplay(gameManager, bot);

        setContentPane(display);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new MainGUI("CHESS GAME"));

        Logger logger = LoggerUtility.getLogger(MainGUI.class, "html");
        logger.info("Interface lancée");
    }
}
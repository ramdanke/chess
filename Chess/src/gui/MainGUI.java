package gui;

import javax.swing.*;
import engine.map.Plateau;

public class MainGUI extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;

    private Plateau plateau;
    private GameDisplay display;

    public MainGUI(String title) {
        super(title);
        init();
    }

    private void init() {

        plateau = new Plateau();
        display = new GameDisplay(plateau);

        add(display);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Plein écran
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            display.repaint();
        }
    }

    public static void main(String[] args) {
        MainGUI gui = new MainGUI("Chess Game");
        Thread gameThread = new Thread(gui);
        gameThread.start();
    }
}
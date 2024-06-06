package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Bug Game");

        Panel panel = new Panel();
        window.add(panel);
        window.pack(); // to be sized to fit the preferred size(Panel)

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.startGameThread(); // Iniciar o loop do jog
    }
}

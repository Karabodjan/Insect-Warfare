package fr.celso.insectwarfare.main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Insect Warfare");

        Panel panel = new Panel();
        window.add(panel);
        window.pack(); // to be sized to fit the preferred size(Panel)

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.setupGame(); // called antes de iniciar o jogo
        panel.startGameThread();
    }

}

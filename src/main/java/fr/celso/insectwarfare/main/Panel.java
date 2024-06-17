package fr.celso.insectwarfare.main;

import fr.celso.insectwarfare.entity.Player;
import fr.celso.insectwarfare.object.GreatObject;
import fr.celso.insectwarfare.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 fr.celso.insectwarfare.tile
    final int scale = 3; // Vamos multiplicar 3x16

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol; // 864 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 672 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyBoard keyB = new KeyBoard();
    Sound sound = new Sound();
    public ColisionCheck cCheck = new ColisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread; // Para adicionarmos tempo real no jogo

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyB);
    public GreatObject obj [] =  new GreatObject[10];// I Can display up to 10 object in the same time

    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // para uma melhor performance
        this.addKeyListener(keyB);
        this.setFocusable(true); // o painel pode ser focado para receber entrada de teclado
        this.requestFocusInWindow(); // Garante que o painel tenha o foco para receber eventos de teclado
    }

    public void  setupGame(){

        aSetter.setObject(); // This method is to add other setup

        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this); // O `this` é para passar a classe Panel dentro do Thread
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // Essa função actualiza a informação do jogo como a posição do player
                update();
                // Vai desenhando à medida que for atualizando
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // melhor controle geométrico

        //Tile
        tileM.draw(g2d);//tem que estar em cima desenhamos o terreno depois o jogapdr

        //Object
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj [i].draw(g2d,this);
            }
        }

        //Player
        player.draw(g2d);
        g2d.dispose(); // boa prática para salvar memória
    }
    public void playMusic(int i) {

        sound.setFile(i);
        sound.play();
        sound.loop();

    }
    public void stopMusic() {
        sound.stop();
    }
    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }
}

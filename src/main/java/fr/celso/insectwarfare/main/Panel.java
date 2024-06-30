package fr.celso.insectwarfare.main;

import fr.celso.insectwarfare.entity.Entity;
import fr.celso.insectwarfare.entity.Player;
import fr.celso.insectwarfare.object.GreatObject;
import fr.celso.insectwarfare.tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Panel class serves as the main game panel where all game elements are drawn and updated.
 */

public class Panel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 fr.celso.insectwarfare.tile
    final int scale = 3; // Scale factor for tile size 3x16

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol; // 864 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 672 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;

    // FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyBoard keyB = new KeyBoard(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public EventHandler ehandler = new EventHandler(this);
    public ColisionCheck cCheck = new ColisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI (this);
    Thread gameThread; // Thread for running the game loop

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyB);
    public GreatObject obj [] =  new GreatObject[10];// display up to 10 object in the same time
    public Entity npc [] = new Entity[10];


    //GAME STATE
    public int gameState;
    public final int  titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int combatState = 4;

    // Combat
    public Combat combat = new Combat();
    public int combatChoice = 0;

    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Enables double buffering for smoother graphics
        this.addKeyListener(keyB);
        this.setFocusable(true); // Allows the panel to receive keyboard focus
        this.requestFocusInWindow(); // Ensures the panel has focus for receiving keyboard events
    }

    public void  setupGame(){

        aSetter.setObject(); // This method is to add other setup
        aSetter.setNPC();
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Creates a new thread for running the game loop
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;


        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;

            }

            if (timer >= 1000000000) { // If one second has passed
                timer = 0;
            }
        }
    }

    public void update() {

        if(gameState == playState){

            //Player
            player.update();
            ehandler.checkEvent();

            //NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Title Screen
        if (gameState == titleState) {
            ui.draw(g2d);
        }

        //Others
        else {
            //Tile
            tileM.draw(g2d);

            //Object
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2d, this);
                }
            }

            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2d);
                }
            }

            //Player
            player.draw(g2d);

            //UI
            ui.draw(g2d);
        }

        g2d.dispose();// Clean up Graphics2D resources
    }

    public void handleCombatInput(int choice) {
        combat.setPlayerChoice(choice);
        String result = combat.getResult();
        ui.showMessage(result);
        gameState = playState;
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
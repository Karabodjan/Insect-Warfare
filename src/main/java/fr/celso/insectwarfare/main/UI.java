package fr.celso.insectwarfare.main;
//Cette classe gere tout l'interface de l'utilisateur

import fr.celso.insectwarfare.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    Panel gp;
    Graphics2D g2d;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageON = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";


    public UI(Panel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageON = true;
    }

    public void draw(Graphics2D g2d) {

        this.g2d = g2d;
        g2d.setFont(arial_40);
        g2d.setColor(Color.white);
        g2d.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2d.drawString("Key = "+ gp.player.hasKey, 74, 65);

        //Message
        if (messageON == true) {
            g2d.setColor(Color.yellow);
            g2d.setFont(g2d.getFont().deriveFont(30f));
            g2d.drawString(message, gp.tileSize/2, gp.tileSize*5);

            messageCounter++;
            if (messageCounter >120) {
                messageCounter = 0;
                messageON = false;
            }
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {

        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        //Dialogue State
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }
    public void drawPauseScreen() {

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2d.drawString(text, x,y);

    }
    public void drawDialogueScreen() {

        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 32f));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n") ){
            g2d.drawString(line, x, y);
            y += 40;
        }

    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0,210);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }
    public int  getXforCenteredText(String text) {
        int lenght = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return  x;
    }
}

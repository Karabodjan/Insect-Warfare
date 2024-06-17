package fr.celso.insectwarfare.main;
//Cette classe gere tout l'interface de l'utilisateur

import fr.celso.insectwarfare.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    Panel gp;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageON = false;
    public String message = "";
    int messageCounter = 0;


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
    public void draw (Graphics2D g2d) {

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

    }
}

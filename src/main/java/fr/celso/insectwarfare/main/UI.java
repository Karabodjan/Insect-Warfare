package fr.celso.insectwarfare.main;
//Cette classe gere tout l'interface de l'utilisateur

import fr.celso.insectwarfare.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    Panel gp;
    Font arial_40;
    BufferedImage keyImage;

    public UI(Panel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void draw (Graphics2D g2d) {

        g2d.setFont(arial_40);
        g2d.setColor(Color.white);
        g2d.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2d.drawString("Key = "+ gp.player.hasKey, 74, 65);

    }
}

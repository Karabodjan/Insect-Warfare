package fr.celso.insectwarfare.object;

import java.awt.*;
import java.awt.image.BufferedImage;

//Parent class fot all object class
public class GreatObject {

    public BufferedImage image;
    public String name;
    public Boolean colision = false;
    public int worldX, worldY;

    public void draw(Graphics g2d,fr.celso.insectwarfare.main.Panel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {

            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }
    }
package fr.celso.insectwarfare.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Parent class for all object classes in the game
 */

public class GreatObject {

    public BufferedImage image;
    public String name;
    public Boolean colision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics g2d,fr.celso.insectwarfare.main.Panel gp) {

        // Calculate screen coordinates relative to the player's position
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Only draw the object if it is within the visible screen area
        if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {

            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }
}
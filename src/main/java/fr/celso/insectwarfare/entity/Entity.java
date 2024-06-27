package fr.celso.insectwarfare.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import fr.celso.insectwarfare.main.Panel;

//This class stores variables that will be used in player,NPC,Insects
public class Entity {

    Panel gp;
    public int worldX, worldY;
    public int speed;

    //BufferedImage describes an Image with an acessible buffer of image data
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String directon;

    //Walk animation
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean colisionOn = false;

    public Entity(Panel gp) {
        this.gp = gp;
    }

    public void draw(Graphics g2d) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {


            switch (directon) {
                case "up":
                    image = (spriteNum == 1) ? up1 : up2;
                    break;
                case "down":
                    image = (spriteNum == 1) ? down1 : down2;
                    break;
                case "left":
                    image = (spriteNum == 1) ? left1 : left2;
                    break;
                case "right":
                    image = (spriteNum == 1) ? right1 : right2;
                    break;
            }

            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
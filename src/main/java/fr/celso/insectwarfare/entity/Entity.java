package fr.celso.insectwarfare.entity;

import java.awt.image.BufferedImage;

//This class stores variables that will be used in player,NPC,Insects
public class Entity {

    public int worldX, worldY;
    public int speed;

    //BufferedImage describes an Image with an acessible buffer of image data
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String directon;

    //Walk animation
    public int spriteCounter = 0;
    public int spriteNum= 1;
}

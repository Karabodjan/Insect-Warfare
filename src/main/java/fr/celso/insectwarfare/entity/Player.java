package fr.celso.insectwarfare.entity;

import fr.celso.insectwarfare.main.KeyBoard;
import fr.celso.insectwarfare.main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    Panel gp;
    KeyBoard keyB;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(Panel gp, KeyBoard keyB) {

        this.gp = gp;
        this.keyB = keyB;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefautValues();
        getPlayerImage();
    }
    public void setDefautValues(){

        //Set player defaut position
        worldX= gp.tileSize * 39;
        worldY = gp.tileSize * 38;
        speed = 4;
        directon = "down";
    }
    //Load the images
    public void getPlayerImage(){

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up1.png.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up2.png.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down1.png.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down2.png.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left2.png.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left1.png.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right2.png.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right1.png.png"));


        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void update() {
        if (keyB.upPressed || keyB.downPressed || keyB.leftPressed || keyB.rightPressed) {
            if (keyB.upPressed) {
                directon = "up";

            } else if (keyB.downPressed) {
                directon = "down";

            } else if (keyB.leftPressed) {
                directon = "left";

            } else if (keyB.rightPressed) {
                directon = "right";

            }

            // CHECK TILE COLISION
            colisionOn = false;
            gp.cCheck.checkTile(this);

            // CHECK OBJECT COLISION
           int objIndex = gp.cCheck.checkObject(this, true);
            pickUpObject(objIndex);


            // IF COLISION = false, PLAYER CAN'T MOVE
            if (colisionOn == false) {

                switch (directon) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObject(int i){

        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(2);
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    break;

            }

        }

    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

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

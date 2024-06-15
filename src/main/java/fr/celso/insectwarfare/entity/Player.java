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

    public Player(Panel gp, KeyBoard keyB) {

        this.gp = gp;
        this.keyB = keyB;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefautValues();
        getPlayerImage();
    }
    public void setDefautValues(){

        //Set player defaut position
        worldX= gp.tileSize * 39;
        worldY = gp.tileSize * 39;
        speed = 4;
        directon = "down";
    }
    //Load the images
    public void getPlayerImage(){

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));


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

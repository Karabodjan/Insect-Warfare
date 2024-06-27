package fr.celso.insectwarfare.entity;

import fr.celso.insectwarfare.main.Panel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_Master extends Entity {

    public NPC_Master(Panel gp) {
        super(gp);

        directon = "right";
        speed = 1;
        getImage();
    }

    public void getImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_left2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_left1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_right2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/npc_right1.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {

        actionLockCounter ++;

        if (actionLockCounter == 60) {

            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number  form 1 to 100

            if (i <= 25) {
                directon = "up";
            }
            if (i > 25 && i <= 50) {
                directon = "down";
            }
            if (i > 50 && i <= 75) {
                directon = "left";
            }
            if (i > 75 && i <= 100) {
                directon = "right";
            }

            actionLockCounter = 0;

            }
        }
}


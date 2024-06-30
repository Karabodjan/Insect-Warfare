package fr.celso.insectwarfare.entity;

import fr.celso.insectwarfare.main.Panel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NPC_Rival  extends Entity{

    public NPC_Rival(Panel gp) {
        super(gp);

        directon = "right";
        speed = 1;
        getImage();
        setDialoque();
    }

    public void getImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/boy_left_2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/boy_left_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/boy_right_2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/boy_right_1.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDialoque(){

        dialogues[0] = "Go the other side of the table";
        dialogues[1] = "Let's fight!";
    }

    public void speak(){

        super.speak();
    }
}

package fr.celso.insectwarfare.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door  extends GreatObject{

    public OBJ_Door(){

        name = "Door";
        // To load the image
        try {
            image = ImageIO.read(getClass().getResource("/objects/door_iron.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        colision = true;
    }
}

package fr.celso.insectwarfare.object;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents a door object in the game.
 * Extends GreatObject to inherit common properties and methods.
 */

public class OBJ_Door  extends GreatObject{

    public OBJ_Door(){

        name = "Door";

        // Load the image of the door
        try {
            image = ImageIO.read(getClass().getResource("/objects/door_iron.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        colision = true;
    }
}

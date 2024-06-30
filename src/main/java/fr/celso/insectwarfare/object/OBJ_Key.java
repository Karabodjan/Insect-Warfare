package fr.celso.insectwarfare.object;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Represents a key object in the game.
 * Extends GreatObject to inherit common properties and methods.
 */

public class OBJ_Key extends GreatObject{

    public OBJ_Key(){

        name = "Key";

        // Load the image of the key
        try {
            image = ImageIO.read(getClass().getResource("/objects/key.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

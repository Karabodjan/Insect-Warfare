package fr.celso.insectwarfare.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends GreatObject{

    public OBJ_Key(){

        name = "Key";
        // To load the image
        try {
            image = ImageIO.read(getClass().getResource("/objects/key.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

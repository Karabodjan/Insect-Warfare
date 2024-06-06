package tile;

import main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    Panel gp;
    Tile[] tile;
    int mapTileNum[] [];

    public TileManager(Panel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();

    }

    public void getTileImage() {

        try {

            tile[0]= new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1]= new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2]= new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(tile[0].image,0,0, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tile[1].image,48,0, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(tile[2].image,96,0, gp.tileSize, gp.tileSize, null);
    }

}

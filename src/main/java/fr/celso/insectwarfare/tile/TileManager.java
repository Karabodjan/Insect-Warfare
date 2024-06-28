package fr.celso.insectwarfare.tile;

import fr.celso.insectwarfare.main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    Panel gp;
    public Tile[] tile;
    public int mapTileNum[] [] [];

    public TileManager(Panel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt",0);
        loadMap("/maps/map00.txt",1);
    }

    public void getTileImage() {

        try {

            tile[0]= new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1]= new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].colision = true;

            tile[2]= new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].colision = true;

            tile[3]= new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4]= new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].colision = true;
            tile[5]= new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            tile[6]= new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor01.png"));

            tile[7]= new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/table01.png"));
            tile[7].colision = true;

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath, int map){

        try {
            InputStream is = getClass().getResourceAsStream(filePath); //InputStream to import the file map00.txt
            BufferedReader br = new BufferedReader(new InputStreamReader(is));// BufferedReader gonna read the content of the text file

            int col = 0;
            int row =0;

            while (col< gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine(); //.readLine() Read a line of text

                while (col <gp.maxWorldCol) {

                    String numbers [] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col] [row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2d) {

        int worlCol = 0;
        int worldRow = 0;


        while (worlCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum [gp.currentMap][worlCol] [worldRow];

            int worldX = worlCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {

                g2d.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }
            worlCol++;

            if (worlCol == gp.maxWorldCol){
                worlCol = 0;
                worldRow++;
            }
        }
    }

}
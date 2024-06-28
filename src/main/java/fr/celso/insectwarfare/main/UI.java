package fr.celso.insectwarfare.main;
//Cette classe gere tout l'interface de l'utilisateur

import fr.celso.insectwarfare.object.OBJ_Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    Panel gp;
    Graphics2D g2d;
    Font arial_40;
    BufferedImage keyImage;
    BufferedImage titleImage;
    public boolean messageON = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    public int commandNum = 0;


    public UI(Panel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;

        // Carrega a imagem da tela de título
        try {
            BufferedImage originalTitleImage = ImageIO.read(getClass().getResourceAsStream("/tilte/title_screem.jpg"));
            titleImage = resizeImage(originalTitleImage, gp.screenWidth, gp.screenHeight); // Redimensiona a imagem para caber na tela
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para redimensionar a imagem
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        // Calcula a nova altura mantendo a proporção original da imagem
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        int newHeight = (originalHeight * targetWidth) / originalWidth;

        // Se a nova altura for maior que o targetHeight, ajuste a largura para caber na altura
        if (newHeight > targetHeight) {
            targetWidth = (originalWidth * targetHeight) / originalHeight;
            newHeight = targetHeight;
        }

        Image resultingImage = originalImage.getScaledInstance(targetWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }

    public void showMessage(String text) {
        message = text;
        messageON = true;
    }

    public void draw(Graphics2D g2d) {

        this.g2d = g2d;
        g2d.setFont(arial_40);
        g2d.setColor(Color.white);
        g2d.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2d.drawString("Key = "+ gp.player.hasKey, 74, 65);

        //Message
        if (messageON == true) {
            g2d.setColor(Color.yellow);
            g2d.setFont(g2d.getFont().deriveFont(30f));
            g2d.drawString(message, gp.tileSize/2, gp.tileSize*5);

            messageCounter++;
            if (messageCounter >120) {
                messageCounter = 0;
                messageON = false;
            }
        }

        //  Title STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {

        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        //Dialogue State
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen() {
        // Desenha a imagem da tela de título
        int x = (gp.screenWidth - titleImage.getWidth()) / 2;
        int y = (gp.screenHeight - titleImage.getHeight()) / 2;
        g2d.drawImage(titleImage, x, y, null);

        // Menu
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,52F));
        g2d.setColor(Color.black);

        String text = "NEW GAME" ;
        x = getXforCenteredText(text);
        y += gp.tileSize*9;
        g2d.drawString(text, x, y);
        if (commandNum == 0) {
            g2d.drawString(">", x-gp.tileSize, y);
        }

         text = "LOAD GAME" ;
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2d.drawString(text, x, y);
        if (commandNum == 1) {
            g2d.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT" ;
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2d.drawString(text, x, y);
        if (commandNum == 2) {
            g2d.drawString(">", x-gp.tileSize, y);
        }




    }
    public void drawPauseScreen() {

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2d.drawString(text, x,y);

    }
    public void drawDialogueScreen() {

        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 32f));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n") ){
            g2d.drawString(line, x, y);
            y += 40;
        }

    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0,210);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }
    public int  getXforCenteredText(String text) {
        int lenght = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return  x;
    }
}

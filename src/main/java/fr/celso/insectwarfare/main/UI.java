package fr.celso.insectwarfare.main;

import fr.celso.insectwarfare.object.OBJ_Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.BasicStroke;

import javax.imageio.ImageIO;

/**
 * The "UI" manages the user interface elements and screens such as the title screen, pause screen, dialogue screen, and combat screen.
 */

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
    public int combatChoice = 0;
    public boolean leftPressed, rightPressed, enterPressed;

    public UI(Panel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;

        // Load title screen image
        try {
            BufferedImage originalTitleImage = ImageIO.read(getClass().getResourceAsStream("/tilte/title_screem.jpg"));
            titleImage = resizeImage(originalTitleImage, gp.screenWidth, gp.screenHeight); // Resize image to fit screen
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to resize an image while maintaining aspect ratio
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        int newHeight = (originalHeight * targetWidth) / originalWidth;

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


    // Method to display a message on screen
    public void showMessage(String text) {
        message = text;
        messageON = true;
    }

    // Method to draw UI components based on game state
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setFont(arial_40);
        g2d.setColor(Color.white);
        g2d.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2d.drawString("Key = "+ gp.player.hasKey, 74, 65);

        // Display message
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

        //  TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        // COMBAT STATE
        if (gp.gameState == gp.combatState) {
            drawCombatScreen();
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {

        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    // Method to draw the title screen
    public void drawTitleScreen() {
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

    // Method to draw the pause screen
    public void drawPauseScreen() {
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2d.drawString(text, x, y);
    }

    // Method to draw the dialogue screen
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

        for (String line : currentDialogue.split("\n")) {
            g2d.drawString(line, x, y);
            y += 40;
        }
    }

    // Method to draw the combat screen
    public void drawCombatScreen() {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 36));

        String title = "Choose your move:";
        int titleWidth = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, (gp.screenWidth - titleWidth) / 2, gp.screenHeight / 4);

        String[] options = {"Rock", "Paper", "Scissors"};
        int startY = gp.screenHeight / 3;
        int optionSpacing = 60;

        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            int optionWidth = g2d.getFontMetrics().stringWidth(option);
            int x = (gp.screenWidth - optionWidth) / 2;
            int y = startY + i * optionSpacing;
            g2d.drawString(option, x, y);

            if (i == combatChoice) {
                g2d.drawString(">", x - 30, y);
            }
        }
    }

    // Method to draw a semi-transparent sub window
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0,210);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    // Method to calculate the X coordinate for centered text
    public int getXforCenteredText(String text) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
}

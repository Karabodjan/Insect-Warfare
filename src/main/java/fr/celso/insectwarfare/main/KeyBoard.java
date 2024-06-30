package fr.celso.insectwarfare.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *  This class handles keyboard input for different game states.
 */

public class KeyBoard implements KeyListener {

    Panel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,  enterPressed;

    public KeyBoard(Panel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but must be implemented due to KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // Get the code of the pressed key


        //TITLE STATE
        if (gp.gameState == gp.titleState){
            // Navigation through menu options using 'Z' and 'S' keys
            if (code == KeyEvent.VK_Z) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 1){
                   //add Later
                }
                if (gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }

        //PLay state
        if(gp.gameState == gp.playState ){

            if (code == KeyEvent.VK_Z) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_Q) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;

            }
        }

        //PAUSE STATE
       else if(gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;

            }
        }

        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState; // Return to gameplay after dialogue
            }
        }

        // COMBAT STATE
        else if (gp.gameState == gp.combatState) {
            if (code == KeyEvent.VK_Q) {
                gp.ui.leftPressed = true;
                gp.ui.combatChoice--;
                if (gp.ui.combatChoice < 0) {
                    gp.ui.combatChoice = 2;
                }
            }
            if (code == KeyEvent.VK_D) {
                gp.ui.rightPressed = true;
                gp.ui.combatChoice++;
                if (gp.ui.combatChoice > 2) {
                    gp.ui.combatChoice = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.ui.enterPressed = true;
                gp.handleCombatInput(gp.ui.combatChoice);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}

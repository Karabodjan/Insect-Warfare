package fr.celso.insectwarfare.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener { // KeyListener é a interface para receber informações do teclado

    Panel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,  enterPressed;

    public KeyBoard(Panel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não utilizado, mas necessário implementar
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // Pega os códigos das teclas

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
                gp.gameState = gp.playState;
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

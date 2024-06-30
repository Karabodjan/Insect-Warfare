package fr.celso.insectwarfare.main;

import java.awt.Rectangle;

public class EventHandler {

    Panel gp;
    Rectangle eventRect;
    int eventRectDefaulX, eventRectDefaulY;

    public EventHandler(Panel gp) {
        this.gp = gp;

        // Initialize event rectangle with default values
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaulX = eventRect.x;
        eventRectDefaulY = eventRect.y;

    }

    // Method to check if an event should be triggered
    public void checkEvent(){
        if (hit(25, 10, "any")) {
            gp.gameState = gp.combatState;
        }
    }

    // Method to check collision between player and event area
    public boolean hit (int eventCol, int eventRow, String reqDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)){
            if(gp.player.directon.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        // Restore player's solid area and event rectangle to default positions
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaulX;
        eventRect.y = eventRectDefaulY;

        return hit;
    }
}

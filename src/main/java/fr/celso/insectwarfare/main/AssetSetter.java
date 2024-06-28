package fr.celso.insectwarfare.main;

import fr.celso.insectwarfare.entity.NPC_Master;
import fr.celso.insectwarfare.object.OBJ_Door;
import fr.celso.insectwarfare.object.OBJ_Key;

public class AssetSetter {

    Panel gp;

    public AssetSetter(Panel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 38 * gp.tileSize;
        gp.obj[0].worldY = 20 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 24 * gp.tileSize;
        gp.obj[1].worldY = 15 * gp.tileSize;

    }
    public void setNPC() {
        gp.npc[0] = new NPC_Master(gp);
        gp.npc[0].worldX = gp.tileSize*11;
        gp.npc[0].worldY = gp.tileSize*39;
    }
}
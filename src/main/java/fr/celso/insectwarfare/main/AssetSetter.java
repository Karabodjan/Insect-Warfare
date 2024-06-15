package fr.celso.insectwarfare.main;

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

    }
}

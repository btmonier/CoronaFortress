package com.btmonier.coronafortress.unit;

import com.btmonier.coronafortress.*;
import com.btmonier.coronafortress.entity.Entity;

public class Unit extends Entity {
    private int ySpriteIndex;
    public Unit(int ySpriteIndex) {
        this.ySpriteIndex = ySpriteIndex;
    }

    public void render(Bitmap b) {
        int xp = (int) x;
        int yp = (int) y;

        int frame = 0;
        b.draw(Art.i.sprites[frame][ySpriteIndex], xp, yp);
    }

}

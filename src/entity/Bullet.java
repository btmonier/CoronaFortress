package com.btmonier.coronafortress.entity;

import com.btmonier.coronafortress.*;
import com.btmonier.coronafortress.unit.*;

public class Bullet extends Entity {
    public Unit owner;
    public double xo, yo, zo;

    public Bullet(Unit owner, double x, double y, double z, double xa, double ya, double za) {
        this.owner = owner;
        this.xo = this.x = x;
        this.yo = this.y = y;
        this.zo = this.z = z;
        this.xa = xa * 8;
        this.ya = ya * 8;
        this.za = za * 8;
    }

    public boolean blocks(Entity e) {
        if (e == owner) return false;
        return true;
    }

    public void tick() {
        xo = x;
        yo = y;
        super.tick();
        attemptMove();
    }

    public void render(Bitmap b) {
        double xd = xo - x;
        double yd = yo - y;
        int steps = (int) (Math.sqrt(xd * xd + yd * yd) + 1);
        for (int i = 0; i < steps; i++) {
            b.setPixel((int) (x + xd * i / steps), (int) (y + yd * i / steps) - 5, 0xffffffff);
        }
    }

    public void collide(Entity e, double xxa, double yya) {
        remove();
    }
}

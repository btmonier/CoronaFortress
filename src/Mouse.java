package com.btmonier.coronafortress;

public class Mouse {
    public int x, y;
    public boolean b0, b1;
    public boolean onScreen;

    public void update(int x, int y, boolean b0, boolean b1, boolean onScreen) {
        this.x = x;
        this.y = y;
        this.b0 = b0;
        this.b1 = b1;
        this.onScreen = onScreen;
    }
}

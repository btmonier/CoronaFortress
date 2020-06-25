package com.btmonier.coronafortress;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Bitmap {
    public final int[] pixels;
    public final int w, h;

    public Bitmap(int w, int h) {
        this.w = w;
        this.h = h;
        pixels = new int[w * h];
    }

    public Bitmap(int w, int h, int[] pixels) {
        this.w = w;
        this.h = h;
        this.pixels = pixels;
    }

    public Bitmap(BufferedImage img) {
        this.w = img.getWidth();
        this.h = img.getHeight();
        pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
    }

    public void draw(Bitmap b, int xp, int yp) {
        int x0 = xp;
        int x1 = xp + b.w;
        int y0 = yp;
        int y1 = yp + b.h;
        if (x0 < 0) x0 = 0;
        if (y0 < 0) y0 = 0;
        if (x1 > w) x1 = w;
        if (y1 > h) y1 = h;

        for (int y = y0; y < y1; y++) {
            int sp = (y - y0) * b.w + xp;
            int dp = (y) * w;

            for (int x = x0; x < x1; x++) {
                pixels[dp+x] = b.pixels[sp+x];
            }
        }
    }
}

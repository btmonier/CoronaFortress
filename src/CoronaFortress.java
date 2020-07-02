package com.btmonier.coronafortress;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class CoronaFortress extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 3;

    private boolean keepRunning = true;
    private BufferedImage screenImage;
    private Bitmap screenBitmap;

    public CoronaFortress() {
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);

        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    public void start() {
        new Thread(this, "Game Thread").start();
    }

    public void stop() {
        keepRunning = false;
    }

    public void init() {
        Art.init();
        screenImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        screenBitmap = new Bitmap(screenImage);
    }

    public void run() {
        init();

        while (keepRunning) {
            tick();

            render(screenBitmap);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            swap();
        }
    }

    private void swap() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        int screenW = getWidth();
        int screenH = getHeight();
        int w = WIDTH * SCALE;
        int h = HEIGHT * SCALE;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenW, screenH);
        g.drawImage(screenImage, (screenW - w) / 2, (screenH - h) / 2, w, h, null);
        g.dispose();

        bs.show();
    }


    private void render(Bitmap screen) {
        int frame = (step / 10) % 4;
        if (frame == 2) frame = 0;
        if (frame == 3) frame = 2;
        for (int x = 0; x < WIDTH / 16; x++) {
            for (int y = 0; y < HEIGHT / 16; y++) {
                screen.draw(Art.i.sprites[0][9], x * 16, y * 16);
            }
        }
        for (int i = 0; i < 9; i++) {
            screen.draw(Art.i.sprites[frame][i], i * 16, 0);
        }
    }

    public int step = 0;
    private void tick() {
        step++;
    }

    public static void main(String[] args) {
        CoronaFortress gameComponent = new CoronaFortress();

        JFrame frame = new JFrame("Corona Fortress");
        frame.add(gameComponent);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gameComponent.start();

    }
}

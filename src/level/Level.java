package com.btmonier.coronafortress.level;

import com.btmonier.coronafortress.Bitmap;
import com.btmonier.coronafortress.Sprite;
import com.btmonier.coronafortress.entity.Entity;
import com.btmonier.coronafortress.particle.Particle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Level {
    public List<Entity> entities = new ArrayList<Entity>();
    public List<Particle> particles = new ArrayList<Particle>();
    public final int w, h;
    public double maxHeight = 64;

    public Blockmap blockmap;
    public Comparator<Sprite> spriteComparator = new Comparator<Sprite>() {
        public int compare(Sprite s0, Sprite s1) {
            if (s0.y < s1.y) return -1;
            if (s0.y > s1.y) return 1;
            if (s0.z < s1.z) return -1;
            if (s0.z > s1.z) return 1;
            if (s0.x < s1.x) return -1;
            if (s0.x > s1.x) return 1;
            return 0;
        }
    };

    public Level(int w, int h) {
        this.w = w;
        this.h = h;
        blockmap = new Blockmap(w, h, 32);
    }

    public void add(Particle p) {
        particles.add(p);
        p.init(this);
    }

    public void add(Entity e) {
        entities.add(e);
        e.init(this);
    }

    public void tick() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (!e.removed) e.tick();
            if (e.removed) {
                entities.remove(i--);
                continue;
            }
        }

        for (int i = 0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            if (!p.removed) p.tick();
            if (p.removed) {
                particles.remove(i--);
                continue;
            }
        }
    }

    public List<Entity> getEntities(double x0, double y0, double z0, double x1, double y1, double z1) {
        return blockmap.getEntities(x0, y0, z0, x1, y1, z1);
    }

    public void render(Bitmap screen) {
        TreeSet<Sprite> sortedSprites = new TreeSet<Sprite>(spriteComparator);
        sortedSprites.addAll(entities);
        sortedSprites.addAll(particles);
        for (Sprite s : sortedSprites) {
            s.render(screen);
        }
    }


}

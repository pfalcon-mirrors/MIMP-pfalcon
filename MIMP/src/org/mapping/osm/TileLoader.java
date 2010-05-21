package org.mapping.osm;

import java.util.HashMap;
import java.util.LinkedList;

import android.os.Handler;

public class TileLoader implements Runnable {

    LinkedList<Tile> list;
    HashMap<Long, Tile> tiles;
    HashMap<Long, Tile> temp;
    Thread thread;
    OsmView view;
    Handler handler;

    public TileLoader(OsmView view) {
        this.view = view;
        handler = new Handler();
        list = new LinkedList<Tile>();
        tiles = new HashMap<Long, Tile>();
        temp = new HashMap<Long, Tile>();
        thread = new Thread(this);
        thread.start();
    }
    
    public TileLoader() {
        handler = new Handler();
        list = new LinkedList<Tile>();
        tiles = new HashMap<Long, Tile>();
        temp = new HashMap<Long, Tile>();
    }

    final Runnable update = new Runnable() {
        public void run() {
            view.invalidate();
        }
    };

    public void run() {
        for (int i = 0; i < 9; i++) {
            try {
                if (tiles.size() > 30) {
                    tiles = new HashMap<Long, Tile>(temp);
                    temp = new HashMap<Long, Tile>();
                    System.out.println("more than 30 tiles .... cleaning");
                }
                Tile t;
                synchronized (list) {
                    t = list.poll();
                }
                if (t == null) {
                    synchronized (this) {
                        wait();
                    }
                    continue;
                }
                t.load();
                t.save();
                handler.post(update);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onThreadOver() {
        System.out.println("Thread draws Overlays");
        view.drawOverlays();
    }

    public void exec() {
        for (int i = 0; i < 9; i++) {
            try {
                if (tiles.size() > 30) {
                    tiles = new HashMap<Long, Tile>(temp);
                    temp = new HashMap<Long, Tile>();
                    System.out.println("more than 30 tiles .... cleaning");
                }
                Tile t;
                synchronized (list) {
                    t = list.poll();
                }
                if (t == null) {
                    synchronized (this) {
                        wait();
                    }
                    continue;
                }
                t.load();
                t.save();
                handler.post(update);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public Tile getTile(boolean ortho, int tx, int ty, int tz, boolean force) {
        long key = (long) tz << 48 | (long) ty << 24 | tx & 0xFFFFFF;
        if (ortho)
            key |= 1L << 62;
        Tile t = tiles.get(key);
        if (t == null) {
            t = new Tile(ortho, tx, ty, tz);
            tiles.put(key, t);
            if (tiles.size() > 24) {
                temp.put(key, t);
            }
            if (force) {
                t.load();
                t.save();
            } else {
                synchronized (list) {
                    list.addFirst(t);
                }
                synchronized (this) {
                    notify();
                }
            }
        }
        return t;
    }
}

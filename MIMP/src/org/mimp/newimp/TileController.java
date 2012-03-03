package org.mimp.newimp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

import android.content.Context;
import android.os.Handler;
import android.view.View;

public class TileController extends Thread {

    LinkedList<Tile> mPendingList;
    Hashtable<Long,Tile> mTileMap;
    ArrayList<Long> mKeys;
    Thread mThreada;
    Thread mThreadb;
    Thread mThreadc;
    Thread mThreadd;
    View mView;
    Handler mHandler;
    String mProviderUrl;
    
    public TileController(View view, String providerUrl) {
        this.mView = view;
        this.mProviderUrl = providerUrl;
        mHandler = new Handler();
        mPendingList = new LinkedList<Tile>();
        mTileMap= new Hashtable<Long,Tile>();
        mKeys = new ArrayList<Long>();
        mThreada = new Thread(this);
        mThreada.start();
        mThreadb = new Thread(this);
        mThreadb.start();
        mThreadc = new Thread(this);
        mThreadc.start();
        mThreadd = new Thread(this);
        mThreadd.start();
    }
    
    final Runnable update = new Runnable() {
        public void run() {
            mView.postInvalidate();
        }
    };
    
    public void run() {
        while(!this.isInterrupted()) {
            try {
                Tile t;
                synchronized(mPendingList) { 
                    t = mPendingList.poll();
                }
                if (t == null) {
                    synchronized (this) { 
                        wait();
                    }
                    continue;
                }
                t.load();
                t.save();
                mHandler.post(update);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Interrupts all the Threads 
     */
    public void interrupt() {
        mThreada.interrupt();
        mThreadb.interrupt();
        mThreadc.interrupt();
        mThreadd.interrupt();
    }
    
    /**
     * 
     * @param tx
     *      The X position of the Tile to draw
     * @param ty
     *      The Y position of the Tile to draw
     * @param tz
     *      The Zoom value of the Tile to draw
     * @return
     *      The recovered Tile
     */
    public Tile getTile(int tx,int ty,int tz) {
        long key = (long) tz << 48 | (long) ty << 24 |  tx  & 0xFFFFFF ;
        Tile t = mTileMap.get(key);
        if (t == null) {
            t = new Tile(tx,ty,tz,mProviderUrl);
            mTileMap.put(key, t);
            mKeys.add(key);
            clean();
            synchronized (mPendingList) { 
                mPendingList.add(t);
            }
            synchronized(this) {
                notifyAll(); 
            }
        }
        return t;
    }
    
    /**
     * If necessary remove old Tiles from the HashTable
     */
    private void clean() {
        if (mTileMap.size() > 100) {
            for(int i=0; i < 50 ;i++) {
                mTileMap.remove(mKeys.get(0));
                mKeys.remove(0);
            }
        }
    }
    
    /**
     * Clear the List of Tiles to be loaded
     */
    public void shoke() {
        mPendingList = new LinkedList<Tile>();
    }
    
    /**
     * Reset the Tiles to 0
     */
    public void reset() {
        mTileMap = new Hashtable<Long, Tile>();
        mKeys = new ArrayList<Long>();
        mPendingList = new LinkedList<Tile>();
    }
}
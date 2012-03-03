package org.mimp.newimp;

import java.util.ArrayList;
import java.util.List;

import org.mimp.globals.S;
import org.mimp.newimp.interfaces.IMapView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class MapView extends View implements IMapView {

    protected static final Point[] move = { new Point(1, 0), new Point(0, 1), new Point( -1, 0), new Point(0, -1) };

    private TileController mTileController;
    private Context mContext;

    private MapZoomControls mZoomControls;
    private MyLocationOverlay mMapLocationOverlay;
    private MapController mMapController;
    private TouchListener mTouchListener;
    private List<Overlay> mOverlayList;
    private Projection mProjection;
    
    /***************************************************************************
     * 
     *      Object Life
     * 
     **************************************************************************/
    
    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mTileController = new TileController(this, context.getSharedPreferences(S.PREFS_NAME, 0).getString("map_provider_name", S.OpenCycleMapsURL));
        mTouchListener = new TouchListener(context, this);
        setOnTouchListener(mTouchListener);
        mOverlayList = new ArrayList<Overlay>();
        mMapController = new MapController(mContext,this);
        mZoomControls = new MapZoomControls(mContext, this);
        mProjection = new Projection(this);
        enableLocationOverlay();
    }
    
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        SharedPreferences settings = mContext.getSharedPreferences(S.PREFS_NAME, 0);
        Point center = new Point();
        center.x = settings.getInt("Xpos",128);
        center.y = settings.getInt("Ypos",128);
        int zoom = settings.getInt("Zpos",MapController.MIN);
        getController().setCenter(center);
        getController().setZoom(zoom);
        invalidate();
    }
        
    @Override
    protected void finalize() throws Throwable {
        mTileController.interrupt();
        disableLocationOverlay();
        super.finalize();
    }
    
    public void saveState() {
        SharedPreferences settings = mContext.getSharedPreferences(S.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        Point center = getController().getCenter();
        editor.putInt("Xpos", center.x);
        editor.putInt("Ypos", center.y);
        editor.putInt("Zpos", getZoomLevel());
        editor.commit();
    }
    
    /***************************************************************************
     * 
     *      Draw Functions
     * 
     **************************************************************************/
    
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        drawTiles(canvas);
        drawOverlays(canvas);
    }
    
    /**
     * Drawing tiles on canvas
     * @param canvas
     *      The canvas which will be used to draw on
     */
    void drawTiles(Canvas canvas) {
        int iMove = 0;
        int tilesize = 256;
        int tilex = mMapController.getCenter().x / tilesize;
        int tiley = mMapController.getCenter().y / tilesize;
        int off_x = (mMapController.getCenter().x % tilesize);
        int off_y = (mMapController.getCenter().y % tilesize);

        int w2 = getWidth() / 2;
        int h2 = getHeight() / 2;
        int posx = w2 - off_x;
        int posy = h2 - off_y;

        int diff_left = off_x;
        int diff_right = tilesize - off_x;
        int diff_top = off_y;
        int diff_bottom = tilesize - off_y;

        boolean start_left = diff_left < diff_right;
        boolean start_top = diff_top < diff_bottom;

        if (start_top) {
            if (start_left) {
                iMove = 2;
            } else {
                iMove = 3;
            }
        } else {
            if (start_left) {
                iMove = 1;
            } else {
                iMove = 0;
            }
        }
        int x_min = -tilesize;
        int y_min = -tilesize;
        int x_max = getWidth();
        int y_max = getHeight();

        boolean painted = true;
        int x = 0;
        while (painted) {
            painted = false;
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) {
                    x++;
                }
                for (int j = 0; j < x; j++) {
                    if (x_min <= posx && posx <= x_max && y_min <= posy && posy <= y_max) {
                        Tile tile = mTileController.getTile(tilex, tiley, getZoomLevel());
                        if (tile != null && tile.getBitmap() != null) {
                            painted = true;
                            canvas.drawBitmap(tile.getBitmap(),posx,posy,null);
                        }
                    }
                    Point p = move[iMove];
                    posx += p.x * tilesize;
                    posy += p.y * tilesize;
                    tilex += p.x;
                    tiley += p.y;
                }
                iMove = (iMove + 1) % move.length;
            }
        }
    }

    /**
     * Draws the Overlays contained by the MapView
     * @param canvas
     *      The canvas which will be used to draw on
     */
    public void drawOverlays(Canvas canvas) {
        for (int i=getOverlays().size()-1; i >= 0 ;i--) {
            getOverlays().get(i).draw(canvas, this, false);
        }
    }

    /***************************************************************************
     * 
     *      Overlays
     * 
     **************************************************************************/    
    
    /**
     * Returns the List of Overlays contained by the MapView
     * @return
     *      A List of the Overlays on the Map
     */
     public List<Overlay> getOverlays() {
        return mOverlayList;
    }
    
    /***************************************************************************
     * 
     *     New Controllers
     * 
     **************************************************************************/
     
    /**
     * Returns an instance of ZoomControls or create one if it doesn't exists
     * @return
     *      An instance of ZoomControls
     */
    public MapZoomControls getZoomControls() {
        return mZoomControls;
    }
    
    public TileController getTileController() {
        return mTileController;
    }

    /***************************************************************************
     * 
     * Location handling
     * 
     **************************************************************************/
    
    /**
     * Enable the LocationOverlay on the map
     */
    public void enableLocationOverlay() {
        if (mMapLocationOverlay == null) {
            mMapLocationOverlay = new MyLocationOverlay(mContext, this);
            mMapLocationOverlay.runOnFirstFix(new Runnable() {
                public void run() {
                    getController().animateTo(mMapLocationOverlay.getMyLocation());
                }
            });
        }
        getOverlays().add(mMapLocationOverlay);
    }
    
    public void disableLocationOverlay() {
        getOverlays().remove(mMapLocationOverlay);
        mMapLocationOverlay = null;
    }

    /**
     * Gets the currently displayed LocationOverlay
     * @return 
     *      An instance of the current LocationOverlay or null
     */
    public MyLocationOverlay getLocationOverlay() {
        return mMapLocationOverlay;
    }
    
    /***************************************************************************
     * 
     *      Reimplementation of Google Maps Class functions
     * 
     **************************************************************************/
    
    @Override
    public MapController getController() {
        return mMapController;
    }

    @Override
    public int getZoomLevel() {
        return getController().getZoom();
    }

    @Override
    public int getMaxZoomLevel() {
        return MapController.MAX;
    }
    
    @Override
    public int getLatitudeSpan() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getLongitudeSpan() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public GeoPoint getMapCenter() {
        Point p = getController().getCenter();
        double[] coords = TileFactory.PixelToLatLng(new int[] {p.x,p.y}, getZoomLevel());
        return new GeoPoint(coords[0], coords[1]);
    }

    @Override
    public void setMapCenter(int[] values) {
        getController().getCenter().x = values[0];
        getController().getCenter().y = values[1];
        invalidate();
    }
    
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
    }

    @Override
    public Projection getProjection() {
        return mProjection;
    }

    @Override
    public void displayZoomControls(boolean b) {
        if (b == false) {
            mZoomControls.setVisibility(INVISIBLE);
        }
        else {
            mZoomControls.setVisibility(VISIBLE);
        }
    }
}

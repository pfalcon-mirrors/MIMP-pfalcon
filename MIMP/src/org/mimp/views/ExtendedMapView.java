package org.mimp.views;

import java.util.Vector;

import org.mimp.displayables.BubbleOverlay;
import org.mimp.displayables.LineMapOverlay;
import org.mimp.displayables.OverlayGroup;
import org.mimp.displayables.TrackEndPoint;
import org.mimp.displayables.TrackStartPoint;
import org.mimp.dom.ParsedObject;
import org.mimp.newimp.GeoPoint;
import org.mimp.newimp.MapView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.location.Address;
import android.util.AttributeSet;

public class ExtendedMapView extends MapView {

    /*****************************************************************************
     * 
     * Members
     * 
     *****************************************************************************/

    private Context mContext;
    private Matrix mMatrix = new Matrix();
    private boolean mPerspective = false;
    private final SmoothCanvas mCanvas = new SmoothCanvas();
    private BubbleOverlay mBubbleOverlay;
    private OverlayGroup mOverlayGroup = new OverlayGroup();
    private ParsedObject mParsedObject;

    /*****************************************************************************
     * 
     * Life handling
     * 
     *****************************************************************************/

    public ExtendedMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getOverlays().add(mOverlayGroup);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /*****************************************************************************
     * 
     * Drawing methods + Overlays
     * 
     *****************************************************************************/

    public void setPerspective(boolean perspective) {
        this.mPerspective = perspective;
        this.invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mPerspective) {
            float src[] = new float[] { 0, 0, getWidth(), 0, getWidth(),
                    getHeight(), 0, getHeight() };
            float dst[] = new float[] { 0, 0, getWidth(), 0, getWidth() * 2,
                    getHeight(), -getWidth(), getHeight() };
            mMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            canvas.concat(mMatrix);
            canvas.rotate(0, getWidth() * 0.5f, getHeight() * 0.5f);
            mCanvas.delegate = canvas;
            super.draw(mCanvas);
            canvas.restore();
        }
        else {
            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            mCanvas.delegate = canvas;
            super.draw(canvas);
            if (mBubbleOverlay != null)
                mBubbleOverlay.draw(canvas, this, false);
            canvas.restore();
        }
    }

    public OverlayGroup getOverlayGroup() {
        return mOverlayGroup;
    }
    
    public void removeBubble() {
        mBubbleOverlay = null;
        invalidate();
    }
    
    public void unloadTracks() {
        mOverlayGroup.clear();
        invalidate();
    }
    
    public BubbleOverlay getBubbleOverlay() {
        return mBubbleOverlay;
    }
    
    public void setBubbleOverlay(BubbleOverlay bubbleOverlay) {
        mBubbleOverlay = bubbleOverlay;
        invalidate();
    }
    
    public void setBubbleLocation(Address addresses, GeoPoint p) {
        if (mBubbleOverlay != null) {
            getOverlays().remove(mBubbleOverlay);
            invalidate();
        }
        Vector<String> address = new Vector<String>();
        for (int i = 0; i < addresses.getMaxAddressLineIndex(); i++)
            address.add(addresses.getAddressLine(i).trim());
        mBubbleOverlay = new BubbleOverlay(address, p, mContext);
        invalidate();
    }

    public void setCurrentTrack(ParsedObject parsedObject) {
        Vector<GeoPoint> geo = parsedObject.getPoints();
        OverlayGroup overlays = getOverlayGroup();

        LineMapOverlay lineMapOverlay = new LineMapOverlay();
        lineMapOverlay.setLineMapOverlay(mContext, geo,
                getHeight(), getWidth());
        overlays.add(lineMapOverlay);

        TrackStartPoint startPoint = new TrackStartPoint(geo.get(0),
                mContext);
        overlays.add(startPoint);

        TrackEndPoint endPoint = new TrackEndPoint(geo.get(geo.size() - 1),
                mContext);
        overlays.add(endPoint);

        invalidate();
        getController().animateTo(geo.get(0));
        
        mParsedObject = parsedObject;
    }
    
    public ParsedObject getCurrentTrack()
    {
        return mParsedObject;
    }
}
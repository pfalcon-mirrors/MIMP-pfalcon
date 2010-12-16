package org.mimp.displayables;

import java.util.List;

import org.mimp.globals.S;
import org.mimp.screens.BubbleInteractionScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class BubbleOverlay extends Overlay {

    private GeoPoint selectedMapLocation;
    private Paint innerPaint;
    private Paint borderPaint;
    private Paint textPaint;
    private Paint mainTextPaint;
    private List<String> mAddress;
    private Context mContext;
    private float scale;
    private int mainFontSize;
    private int altFontSize;

    private int INFO_POINTER_WIDTH = 20;
    private int INFO_POINTER_HEIGHT = 20;
    private int INFO_WINDOW_WIDTH = 30;
    private int INFO_WINDOW_HEIGHT = 25;
    private int TEXT_OFFSET_X = 10;
    private int TEXT_OFFSET_Y = 5;

    public BubbleOverlay(List<String> address, GeoPoint location,
            Context context) {
        mAddress = address;
        selectedMapLocation = location;
        mContext = context;
        scale = mContext.getResources().getDisplayMetrics().density;
        mainFontSize = (int) (scale * 15 + 0.5f);
        altFontSize = mainFontSize - 5;
        generateWindowDimensions();
    }

    /**
     * searching for best bubble size considering text length and font size !
     * beware :D
     */
    private void generateWindowDimensions() {
        if (mAddress.size() > 0) {
            Rect rect = new Rect();
            getMainTextPaint().getTextBounds(mAddress.get(0), 0,
                    mAddress.get(0).length(), rect);
            /**
             * if the 1st line is the longest
             */
            INFO_WINDOW_WIDTH = rect.width();
            INFO_WINDOW_HEIGHT = mainFontSize;
            for (int i = 1; i < mAddress.size(); i++) {
                getTextPaint().getTextBounds(mAddress.get(i), 0,
                        mAddress.get(i).length(), rect);
                /**
                 * if it is another one
                 */
                if (INFO_WINDOW_WIDTH < rect.width()) {
                    INFO_WINDOW_WIDTH = rect.width();
                }
                INFO_WINDOW_HEIGHT += altFontSize;
            }
            /**
             * now to add the offsets
             */
            INFO_WINDOW_WIDTH += TEXT_OFFSET_X * 2;
            /**
             * inner offset (between lines)
             */
            INFO_WINDOW_HEIGHT += TEXT_OFFSET_Y * mAddress.size();
            /**
             * bottom offset
             */
            INFO_WINDOW_HEIGHT += TEXT_OFFSET_Y * 2;
        }
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        super.draw(canvas, mapView, shadow);
        if (shadow == false) { // bored to draw a shadow if you do it send me
                               // the code ;)
            // First determine the screen coordinates of the selected
            // MapLocation
            Point selDestinationOffset = new Point();
            mapView.getProjection().toPixels(selectedMapLocation,
                    selDestinationOffset);

            // Setup the info window position pointer
            RectF infoPointerRect = new RectF(0, 0, INFO_POINTER_WIDTH,
                    INFO_POINTER_HEIGHT);
            int infoPointerOffsetX = selDestinationOffset.x
                    - INFO_POINTER_WIDTH / 2;
            int infoPointerOffsetY = selDestinationOffset.y
                    - INFO_POINTER_HEIGHT - 10;
            infoPointerRect.offset(infoPointerOffsetX, infoPointerOffsetY);

            // Setup the info window with the right size & location
            RectF infoWindowRect = new RectF(0, 0, INFO_WINDOW_WIDTH,
                    INFO_WINDOW_HEIGHT);
            int infoWindowOffsetX = selDestinationOffset.x - INFO_WINDOW_WIDTH
                    / 2;
            int infoWindowOffsetY = selDestinationOffset.y - INFO_WINDOW_HEIGHT
                    - 30;
            infoWindowRect.offset(infoWindowOffsetX, infoWindowOffsetY);

            // Draw Pointer Border
            Path path = new Path();
            path.moveTo(infoPointerOffsetX, infoPointerOffsetY);
            float border[] = { infoPointerOffsetX + 10,
                    infoPointerOffsetY + 20, infoPointerOffsetX + 20,
                    infoPointerOffsetY, infoPointerOffsetX, infoPointerOffsetY };

            for (int i = 0; i < border.length; i += 2)
                path.lineTo(border[i], border[i + 1]);
            canvas.drawPath(path, getBorderPaint());

            // Draw inner info window
            canvas.drawRoundRect(infoWindowRect, 5, 5, getInnerPaint());

            // Draw border for info window
            canvas.drawRoundRect(infoWindowRect, 5, 5, getBorderPaint());

            // Draw Pointer inner
            path = new Path();
            infoPointerOffsetY = infoPointerOffsetY - 1;
            path.moveTo(infoPointerOffsetX, infoPointerOffsetY);
            float inner[] = { infoPointerOffsetX + 10, infoPointerOffsetY + 20,
                    infoPointerOffsetX + 20, infoPointerOffsetY,
                    infoPointerOffsetX, infoPointerOffsetY };

            for (int i = 0; i < border.length; i += 2)
                path.lineTo(inner[i], inner[i + 1]);
            canvas.drawPath(path, getInnerPaint());

            if (mAddress.size() > 0) {
                int height = TEXT_OFFSET_Y + mainFontSize;
                canvas.drawText(mAddress.get(0), infoWindowOffsetX
                        + TEXT_OFFSET_X, infoWindowOffsetY + height,
                        getMainTextPaint());
                for (int i = 1; i < mAddress.size(); i++) {
                    height += TEXT_OFFSET_Y + altFontSize;
                    canvas.drawText(mAddress.get(i), infoWindowOffsetX
                            + TEXT_OFFSET_X, infoWindowOffsetY + height,
                            getTextPaint());
                }
            }
        }
    }

    /*****************************************************************************
     * 
     * bubble color definitions
     * 
     *****************************************************************************/

    private Paint getInnerPaint() {
        if (innerPaint == null) {
            innerPaint = new Paint();
            innerPaint.setARGB(255, 231, 235, 231); // gray
            innerPaint.setAntiAlias(true);
        }
        return innerPaint;
    }

    private Paint getBorderPaint() {
        if (borderPaint == null) {
            borderPaint = new Paint();
            borderPaint.setARGB(255, 0, 0, 0);
            borderPaint.setAntiAlias(true);
            borderPaint.setStyle(Style.STROKE);
            borderPaint.setStrokeWidth(2);
        }
        return borderPaint;
    }

    private Paint getMainTextPaint() {
        if (mainTextPaint == null) {
            mainTextPaint = new Paint();
            mainTextPaint.setARGB(255, 0, 0, 0);
            mainTextPaint.setAntiAlias(true);
            mainTextPaint.setTextSize(mainFontSize);
            mainTextPaint.setFakeBoldText(true);
        }
        return mainTextPaint;
    }

    private Paint getTextPaint() {
        if (textPaint == null) {
            textPaint = new Paint();
            textPaint.setARGB(255, 0, 0, 0);
            textPaint.setAntiAlias(true);
            textPaint.setTextSize(altFontSize);
        }
        return textPaint;
    }

    /*****************************************************************************
     * 
     * Key controls
     * 
     *****************************************************************************/

    @Override
    public boolean onTap(GeoPoint p, MapView mapView) {
        super.onTap(p, mapView);
        if (isTapOnElement(p, mapView)) {
            String[] array = new String[mAddress.size()];
            for (int i = 0; i < mAddress.size(); i++) {
                array[i] = mAddress.get(i);
            }
            int[] coords = { selectedMapLocation.getLatitudeE6(),
                    selectedMapLocation.getLongitudeE6() };
            Intent mIntent = new Intent(mContext, BubbleInteractionScreen.class)
                    .putExtra("address", array).putExtra("coords", coords);
            ((Activity) mContext).startActivityForResult(mIntent,
                    S.BubbleInteractionScreen_RQC);
        }
        return false;
    }

    public boolean isTapOnElement(GeoPoint p, MapView mapView) {
        Point screenCoords = new Point();
        mapView.getProjection().toPixels(selectedMapLocation, screenCoords);
        RectF hitTestRecr = new RectF();
        hitTestRecr.set(-INFO_WINDOW_WIDTH / 2,
                -(INFO_WINDOW_HEIGHT + INFO_POINTER_HEIGHT),
                INFO_WINDOW_WIDTH / 2, 0);
        hitTestRecr.offset(screenCoords.x, screenCoords.y);
        mapView.getProjection().toPixels(p, screenCoords);
        if (hitTestRecr.contains(screenCoords.x, screenCoords.y)) {
            return true;
        }
        return false;
    }
}

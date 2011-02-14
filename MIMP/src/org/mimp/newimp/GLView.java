package org.mimp.newimp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class GLView extends ViewGroup {

    private static final float SQ2 = 1.414213562373095f;
    private final SmoothCanvas mCanvas = new SmoothCanvas();
    @SuppressWarnings("unused")
    private float mHeading = 0;
    private Matrix mMatrix;

    public GLView(Context context, Matrix matrix) {
        super(context);
        mMatrix = matrix;
    }

    public void onSensorChanged(int sensor, float[] values) {
        synchronized (this) {
            mHeading = values[0];
            invalidate();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.concat(mMatrix);
        // canvas.rotate(-mHeading, getWidth() * 0.5f, getHeight() * 0.5f);
        mCanvas.delegate = canvas;
        super.dispatchDraw(mCanvas);
        canvas.restore();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width = getWidth();
        final int height = getHeight();
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View view = getChildAt(i);
            final int childWidth = view.getMeasuredWidth();
            final int childHeight = view.getMeasuredHeight();
            final int childLeft = (width - childWidth) / 2;
            final int childTop = (height - childHeight) / 2;
            view.layout(childLeft, childTop, childLeft + childWidth, childTop
                    + childHeight);
        }
        float src[] = new float[] { 0, 0, width, 0, width, height, 0, height };
        float dst[] = new float[] { 0, 0, width, 0, width * 2, height, -width,
                height };
        mMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int h = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        int sizeSpec;
        if (w > h) {
            sizeSpec = MeasureSpec.makeMeasureSpec((int) (w * SQ2),
                    MeasureSpec.EXACTLY);
        }
        else {
            sizeSpec = MeasureSpec.makeMeasureSpec((int) (h * SQ2),
                    MeasureSpec.EXACTLY);
        }
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(sizeSpec, sizeSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO: rotate events too
        return super.dispatchTouchEvent(ev);
    }

    public void onAccuracyChanged(int sensor, int accuracy) {
        // TODO Auto-generated method stub

    }
}
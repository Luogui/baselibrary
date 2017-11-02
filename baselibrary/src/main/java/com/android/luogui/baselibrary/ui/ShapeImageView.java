package com.android.luogui.baselibrary.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.android.luogui.baselibrary.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述： 圆形图片
 * Created by LuoGui on 2017/8/28.
 */

public class ShapeImageView extends android.support.v7.widget.AppCompatImageView {

    public static Map<Integer, WeakReference<Bitmap>> shadowMap = new HashMap();
    public static Map<Float, WeakReference<Bitmap>> radiusMap = new HashMap();
    private WeakReference<Bitmap> mWeakBitmap;
    private Bitmap mMaskBitmap;
    private Paint mPaint = new Paint();
    private int shapeRes = 0;
    private float radius = 0.0F;
    private int borderWidth = 0;
    private int borderColor;

    public ShapeImageView(Context context) {
        super(context);
        this.mPaint.setAntiAlias(true);
    }

    public ShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPaint.setAntiAlias(true);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ShapeImageView);
        this.shapeRes = array.getResourceId(R.styleable.ShapeImageView_shape, 0);
        this.borderWidth = array.getInt(R.styleable.ShapeImageView_border_width, 0);
        this.borderColor = array.getColor(R.styleable.ShapeImageView_border_color, 0);
        this.radius = array.getDimension(R.styleable.ShapeImageView_radius, 0.0F);
    }

    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = this.mWeakBitmap == null?null:(Bitmap)this.mWeakBitmap.get();
        if(null != bitmap && !bitmap.isRecycled()) {
            if(bitmap != null) {
                this.mPaint.setXfermode((Xfermode)null);
                canvas.drawBitmap(bitmap, 0.0F, 0.0F, this.mPaint);
            }
        } else {
            Drawable drawable = this.getDrawable();
            if(drawable != null) {
                int dwidth = drawable.getIntrinsicWidth();
                int dheight = drawable.getIntrinsicHeight();
                int vwidth = this.getWidth() - 2 * this.borderWidth;
                int vheight = this.getHeight() - 2 * this.borderWidth;
                if(this.mMaskBitmap == null || this.mMaskBitmap.isRecycled()) {
                    this.mMaskBitmap = this.getBitmap();
                }

                bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_4444);
                bitmap.eraseColor(this.borderColor);
                Canvas can = new Canvas(bitmap);
                this.mPaint.reset();
                this.mPaint.setFilterBitmap(false);
                this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                can.drawBitmap(this.mMaskBitmap, 0.0F, 0.0F, this.mPaint);
                Bitmap insideBitmap = Bitmap.createBitmap(vwidth, vheight, Bitmap.Config.ARGB_4444);
                Canvas insidecan = new Canvas(insideBitmap);
                drawable.setBounds(0, 0, vwidth, vheight);
                drawable.draw(insidecan);
                Bitmap b = Bitmap.createScaledBitmap(this.mMaskBitmap, vwidth, vheight, true);
                this.mPaint.reset();
                this.mPaint.setFilterBitmap(false);
                this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                insidecan.drawBitmap(b, 0.0F, 0.0F, this.mPaint);
                can.drawBitmap(insideBitmap, (float)this.borderWidth, (float)this.borderWidth, (Paint)null);
                this.mPaint.setXfermode((Xfermode)null);
                canvas.drawBitmap(bitmap, 0.0F, 0.0F, (Paint)null);
            }
        }

    }

    public Bitmap getBitmap() {
        Bitmap bitmap;
        Canvas tb;
        Paint options;
        if(this.radius > 0.0F) {
            if(radiusMap.get(Float.valueOf(this.radius)) != null && ((WeakReference)radiusMap.get(Float.valueOf(this.radius))).get() != null && !((Bitmap)((WeakReference)radiusMap.get(Float.valueOf(this.radius))).get()).isRecycled()) {
                bitmap = (Bitmap)((WeakReference)radiusMap.get(Float.valueOf(this.radius))).get();
            } else {
                bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_4444);
                tb = new Canvas(bitmap);
                options = new Paint(1);
                tb.drawRoundRect(new RectF(tb.getClipBounds()), this.radius, this.radius, options);
                radiusMap.put(Float.valueOf(this.radius), new WeakReference(bitmap));
            }
        } else if(this.shapeRes == 0) {
            bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_4444);
            tb = new Canvas(bitmap);
            options = new Paint(1);
            options.setAlpha(255);
            tb.drawCircle((float)(this.getWidth() / 2), (float)(this.getWidth() / 2), (float)(this.getWidth() / 2), options);
        } else {
            Bitmap tb1;
            if(shadowMap.get(Integer.valueOf(this.shapeRes)) != null && ((WeakReference)shadowMap.get(Integer.valueOf(this.shapeRes))).get() != null && !((Bitmap)((WeakReference)shadowMap.get(Integer.valueOf(this.shapeRes))).get()).isRecycled()) {
                tb1 = (Bitmap)((WeakReference)shadowMap.get(Integer.valueOf(this.shapeRes))).get();
            } else {
                BitmapFactory.Options options1 = new BitmapFactory.Options();
                options1.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(this.getResources(), this.shapeRes, options1);
                int w = options1.outWidth;
                int h = options1.outHeight;
                float scale = 1.0F;
                scale = Math.max((float)w * 1.0F / (float)this.getWidth(), (float)h * 1.0F / (float)this.getHeight());
                int sample = Math.max((int)scale, 1);
                options1.inSampleSize = sample;
                options1.inJustDecodeBounds = false;
                options1.inPreferredConfig = Bitmap.Config.ALPHA_8;
                tb1 = BitmapFactory.decodeResource(this.getResources(), this.shapeRes, options1);
                shadowMap.put(Integer.valueOf(this.shapeRes), new WeakReference(tb1));
            }

            if(tb1 == null) {
                return null;
            }

            bitmap = Bitmap.createScaledBitmap(tb1, this.getWidth(), this.getHeight(), true);
        }

        return bitmap;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getShapeRes() {
        return this.shapeRes;
    }

    public void setShapeRes(int shapeRes) {
        this.shapeRes = shapeRes;
    }
}

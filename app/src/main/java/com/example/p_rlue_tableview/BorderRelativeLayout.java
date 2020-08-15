package com.example.p_rlue_tableview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

public class BorderRelativeLayout extends RelativeLayout {
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 边框颜色
     */
    private int mPaintColor;
    /**
     * 边框粗细
     */
    private float mBorderStrokeWidth;
    /**
     * 底边边线左边断开距离
     */
    private int mBorderBottomLeftBreakSize;
    /**
     * 底边边线右边断开距离
     */
    private int mBorderBottomRightBreakSize;
    /**
     * 是否需要上边框
     */
    private boolean isNeedTopBorder;
    /**
     * 是否需要左右边框
     */
    private boolean isNeedLeftAndRightBorder;

    /**
     * 是否需要右邊框
     */
    private boolean isNeedLeftBorder;

    /**
     * 是否需要右邊框
     */
    private boolean isNeedRightBorder;


    /**
     * 是否需要下边框
     */
    private boolean isNeedBottomBorder;


    public BorderRelativeLayout(Context context) {
        this(context, null);
    }

    public BorderRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取自定义属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BorderRelativeLayout);
        mPaintColor = ta.getColor(R.styleable.BorderRelativeLayout_borderColor, Color.GRAY);
        mBorderStrokeWidth = ta.getFloat(R.styleable.BorderRelativeLayout_borderStrokeWidth, 2.0f);
        mBorderBottomLeftBreakSize = ta.getDimensionPixelSize(R.styleable.BorderRelativeLayout_borderBottomLeftBreakSize, 0);
        mBorderBottomRightBreakSize = ta.getDimensionPixelSize(R.styleable.BorderRelativeLayout_borderBottomRightBreakSize, 0);
        isNeedTopBorder = ta.getBoolean(R.styleable.BorderRelativeLayout_needTopBorder, true);
        isNeedLeftAndRightBorder = ta.getBoolean(R.styleable.BorderRelativeLayout_needLeftAndRigtBorder, false);
        isNeedBottomBorder = ta.getBoolean(R.styleable.BorderRelativeLayout_needBottomBorder, true);
        isNeedLeftBorder = ta.getBoolean(R.styleable.BorderRelativeLayout_needLeftBorder, false);
        isNeedRightBorder = ta.getBoolean(R.styleable.BorderRelativeLayout_needRightBorder, false);
        ta.recycle();
        init();

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mPaintColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mBorderStrokeWidth);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        //  画4个边
        if (isNeedTopBorder) {
            canvas.drawLine(0, 0, this.getWidth(), 0, mPaint);
        }
        if (isNeedBottomBorder) {
            canvas.drawLine(mBorderBottomLeftBreakSize, this.getHeight(), this.getWidth() - mBorderBottomRightBreakSize, this.getHeight(), mPaint);
        }
        if (isNeedLeftAndRightBorder) {
            canvas.drawLine(0, 0, 0, this.getHeight(), mPaint);
            canvas.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight(), mPaint);
        }
        if (isNeedLeftBorder) {
            Log.v("hank", "isNeedLeftBorder:");
            canvas.drawLine(0, 0, 0, this.getHeight(), mPaint);
        }
        if (isNeedRightBorder) {
            Log.v("hank", "isNeedRightBorder:");
            canvas.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight(), mPaint);


        }
    }


    private void setRoundRect(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(2.5f, 2.5f, width - 2.5f, height - 2.5f, 100, 100, mPaint);
        }
    }

    /**
     * 设置边框颜色
     *
     * @param color
     */
    public void setBorderColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }

    /**
     * 设置边框宽度
     *
     * @param size
     */
    public void setBorderStrokeWidth(float size) {
        mPaint.setStrokeWidth(size);
        invalidate();
    }


    /**
     * 设置是否需要顶部边框
     *
     * @param needtopborder
     */
    public void setNeedTopBorder(boolean needtopborder) {
        isNeedTopBorder = needtopborder;
        invalidate();
    }

    /**
     * 设置是否需要底部边框
     *
     * @param needbottomborder
     */
    public void setNeedBottomBorder(boolean needbottomborder) {
        isNeedBottomBorder = needbottomborder;
        invalidate();
    }
}

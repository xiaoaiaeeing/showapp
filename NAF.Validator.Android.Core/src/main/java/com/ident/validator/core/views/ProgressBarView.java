package com.ident.validator.core.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.ident.validator.core.R;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/13 10:44
 */

public class ProgressBarView extends View {

    //空心
    public static final int STROKE = 1;
    //实心
    public static final int FILL = 0;

    /**
     * 判断数据的最小值
     */
    public static final int MIN = 1;

    /**
     * 最大进度
     */
    public static final int PROGRESS_BAR_MAX = 100;

    /**
     * 默认进度
     */
    public static final int CURRENT_PROGRESS = 0;

    /**
     * 默认圆环宽度
     */
    public static final float CIRCLES_WIDTH = 5;
    /**
     * 默认进度宽度
     */
    public static final float CURRENT_SCHEDULE_WIDTH = 6;

    /**
     * 默认圆环颜色
     */
    public static final int CIRCLES_COLOR = Color.GRAY;

    /**
     * 默认字体的粗细程度
     */
    public static final float TEXT_CRUDE = 0;

    /**
     * 默认字体颜色
     */
    public static final int TEXT_COLOR = Color.GRAY;

    /**
     * 默认字体大小
     */
    public static final float TEXT_SIZE = 25;

    /**
     * 默认进度的颜色
     */
    public static final int CURRENT_PROGRESS_COLOR = Color.YELLOW;

    /**
     * 是否显示百分比
     */
    public static final boolean IS_PERCENT = true;

    /**
     * 默认风格
     */
    public static final int STYLE = 1;


    private Paint mPaint;
    private int style;

    //圆环的宽度
    private float circlesWidth;
    //圆环的颜色
    private int circlesColor;
    //进度字体的粗细程度
    private float textCrude;
    //字体颜色
    private int textColor;
    //字体大小
    private float textSize;
    //设置字体
    private Typeface mTypeface;
    //当前进度
    private int currentProgress;
    //进度颜色
    private int currentProgressColor;
    //进度圆环的宽度
    private float currentScheduleWidth;
    //是否显示百分比
    private boolean isPercent;
    private RectF rectF;


    public ProgressBarView(Context context) {
        super(context);
    }

    public ProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        mPaint = new Paint();
        rectF = new RectF();
        mPaint.setAntiAlias(true);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CirclesProgressBar);


        //初始化圆环变量
        circlesWidth = mTypedArray.getDimension(R.styleable.CirclesProgressBar_circlesWidth, CIRCLES_WIDTH);
        circlesColor = mTypedArray.getColor(R.styleable.CirclesProgressBar_circlesColor, CIRCLES_COLOR);
        textCrude = mTypedArray.getDimension(R.styleable.CirclesProgressBar_textCrude, TEXT_CRUDE);
        textColor = mTypedArray.getColor(R.styleable.CirclesProgressBar_textColor, TEXT_COLOR);
        textSize = mTypedArray.getDimension(R.styleable.CirclesProgressBar_textSize, TEXT_SIZE);
        currentProgress = mTypedArray.getInt(R.styleable.CirclesProgressBar_currentProgress, CURRENT_PROGRESS);
        currentProgressColor = mTypedArray.getColor(R.styleable.CirclesProgressBar_currentProgressColor, CURRENT_PROGRESS_COLOR);
        isPercent = mTypedArray.getBoolean(R.styleable.CirclesProgressBar_isPercent, IS_PERCENT);
        style = mTypedArray.getInt(R.styleable.CirclesProgressBar_style, STYLE);
        currentScheduleWidth = mTypedArray.getDimension(R.styleable.CirclesProgressBar_currentScheduleWidth, CURRENT_SCHEDULE_WIDTH);

        mTypedArray.recycle();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int anInt = getWidth() / 2;
        int circlesRadius = (int) (anInt - circlesWidth / 2);//半径


        //圆环
        mPaint.setColor(circlesColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(circlesWidth);
        canvas.drawCircle(anInt, anInt, circlesRadius, mPaint);

        //百分比
        if (isPercent && style == STROKE) {
            mPaint.setStrokeWidth(textCrude);
            mPaint.setColor(textColor);
            mPaint.setTextSize(textSize);
            mPaint.setTypeface(mTypeface);
            int percent = (int) (((float) currentProgress / (float) PROGRESS_BAR_MAX) * 100);
            float textWidth = mPaint.measureText(percent + "%");
            canvas.drawText(percent + "%", anInt - textWidth / 2, anInt + textSize / 2, mPaint);
        }


        //进度的圆环
        mPaint.setColor(currentProgressColor);
        mPaint.setStrokeWidth(currentScheduleWidth);
        rectF.set(anInt - circlesRadius, anInt - circlesRadius, anInt + circlesRadius, anInt + circlesRadius);
        //选择风格
        switch (style) {

            case STROKE:
                if (currentProgress != 0) {
                    mPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawArc(rectF, 0, 360 * currentProgress / PROGRESS_BAR_MAX, false, mPaint);
                }
                break;
            case FILL:
                if (currentProgress != 0) {
                    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                    canvas.drawArc(rectF, 0, 360 * currentProgress / PROGRESS_BAR_MAX, true, mPaint);
                }
                break;
        }
    }


    public synchronized void setCurrentProgress(int currentProgress) {
        if (currentProgress < CURRENT_PROGRESS) {
            currentProgress = CURRENT_PROGRESS;
        }
        if (currentProgress > PROGRESS_BAR_MAX) {
            currentProgress = PROGRESS_BAR_MAX;
        }
        if (currentProgress <= PROGRESS_BAR_MAX) {
            this.currentProgress = currentProgress;
            postInvalidate();
        }
    }

    public synchronized float getCurrentProgress() {
        return currentProgress;
    }


    public void setScheduleWidth(float currentScheduleWidth) {
        this.currentScheduleWidth = currentScheduleWidth;
    }

    public float getScheduleWidth() {
        return currentScheduleWidth;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getStyle() {
        return style;
    }

    public void setPercent(boolean isPercent) {
        this.isPercent = isPercent;
    }

    public boolean getPercent() {
        return isPercent;
    }

    public void setCurrentProgressColor(int currentProgressColor) {
        if (currentProgressColor < MIN) {
            currentProgressColor = CURRENT_PROGRESS_COLOR;
        }
        this.currentProgressColor = currentProgressColor;
    }

    public int getCurrentProgressColor() {
        return currentProgressColor;
    }

    public void setTypeface(Typeface typeface) {
        this.mTypeface = typeface;
    }

    public void setCirclesWidth(float circlesWidth) {
        if (circlesWidth < MIN) {
            circlesWidth = CIRCLES_WIDTH;
        }
        this.circlesWidth = circlesWidth;
    }

    public float getCirclesWidth() {
        return circlesWidth;
    }

    public void setCirclesColor(int circlesColor) {
        if (circlesColor < MIN) {
            circlesColor = CIRCLES_COLOR;
        }
        this.circlesColor = circlesColor;
    }

    public int getCirclesColor() {
        return circlesColor;
    }

    public void setTextCrude(float textCrude) {
        if (textCrude < MIN) {
            textCrude = TEXT_CRUDE;
        }
        this.textCrude = textCrude;
    }

    public float getTextCrude() {
        return textCrude;
    }

    public void setTextColor(int textColor) {
        if (textColor < MIN) {
            textColor = TEXT_COLOR;
        }
        this.textColor = textColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextSize(float textSize) {
        if (textSize < MIN) {
            textSize = TEXT_SIZE;
        }
        this.textSize = textSize;
    }
}
package com.example.progressviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:进度控件
 * <p>
 * Time: 2017/11/16 0016
 */
public class ProgressView extends View {

    private Paint mBackGroundPaint;//背景色画笔
    private Paint mArcPaint;//进度画笔
    private Paint mTextPaint;//中间文本的画笔

    private int mProgress = 0;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始化
     */
    public void initPaint() {
        mBackGroundPaint = new Paint();
        mBackGroundPaint.setColor(Color.BLACK);
        mBackGroundPaint.setAntiAlias(true);
        mBackGroundPaint.setStrokeWidth(5);
        mBackGroundPaint.setStyle(Paint.Style.STROKE);

        mArcPaint = new Paint();
        mArcPaint.setColor(Color.RED);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStrokeWidth(10);
        mArcPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.RED);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(30);
        mTextPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制底部圆圈
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 150, mBackGroundPaint);
        //绘制进度的圆弧
        canvas.drawArc(new RectF(getMeasuredWidth() / 2 - 150, getMeasuredHeight() / 2 - 150,
                        getMeasuredWidth() / 2 + 150, getMeasuredHeight() / 2 + 150), 0,
                360 * mProgress / 100, false, mArcPaint);
        //绘制中间进度文本
        String text = mProgress + " %";
        //使文本居中摆放
        Rect txtRect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), txtRect);
        canvas.drawText(text, getMeasuredWidth() / 2 - txtRect.width() / 2, getMeasuredHeight() / 2 + txtRect.height() / 2, mTextPaint);
    }

    /**
     * 开始进度
     */
    public void startProgress() {
        mProgress = 0;
        mHandler.sendEmptyMessageDelayed(0, 10);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (mProgress < 100) {
                    mProgress++;
                    invalidate();
                    sendEmptyMessageDelayed(0, 10);
                }
            }
        }
    };
}

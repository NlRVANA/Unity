package com.zwq65.unity.ui.custom.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jingewenku.abrahamcaijin.commonutil.DensityUtils;
import com.zwq65.unity.R;

/**
 * Created by zwq65 on 2017/08/28
 */

public class LoadingButtonView extends View implements View.OnClickListener {
    private int mHeight = DensityUtils.dip2px(getContext(), 200);
    private int mWidth = DensityUtils.dip2px(getContext(), 200);
    private int color;
    private int backgroundColor;
    private String startText, endText;
    private RectF rectF;//弧形外接矩形
    private int radius;//弧形的半径
    private Paint textPaint, mPaint, bPaint;
    private Path path;
    private float angle;


    public LoadingButtonView(Context context) {
        this(context, null);
    }

    public LoadingButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingButtonView);
        color = typedArray.getColor(R.styleable.LoadingButtonView_main_color, Color.rgb(41, 163, 254));
        backgroundColor = typedArray.getColor(R.styleable.LoadingButtonView_background_color, Color.rgb(41, 163, 254));
        startText = typedArray.getString(R.styleable.LoadingButtonView_start_text);
        endText = typedArray.getString(R.styleable.LoadingButtonView_end_text);
        typedArray.recycle();

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        bPaint.setColor(backgroundColor);

        textPaint.setColor(Color.WHITE);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(20);

        path = new Path();

        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (MeasureSpec.EXACTLY == widthMode) {
            mWidth = width;
        }
        if (MeasureSpec.EXACTLY == heightMode) {
            mHeight = height;
        }
        textPaint.setTextSize(mWidth / 4);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF = new RectF();
        //获取弧形的半径大小
        radius = (int) (Math.min(getWidth(), getHeight()) / 2 - mPaint.getStrokeWidth());
        rectF.left = getWidth() / 2 - radius;
        rectF.top = getHeight() / 2 - radius;
        rectF.right = getWidth() / 2 + radius;
        rectF.bottom = getHeight() / 2 + radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, bPaint);
        canvas.drawText(startText, mWidth / 2, mHeight / 2, textPaint);
        path.addArc(rectF, -90, angle);
        canvas.drawPath(path, mPaint);
    }

    private void startProcess() {
        path.reset();
        ValueAnimator animator = ValueAnimator.ofFloat(-90, 270);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                angle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    public void onClick(View v) {
        startProcess();
    }
}

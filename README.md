效果：

![image](https://raw.githubusercontent.com/Urwateryi/MarkDownPic/master/progressview/progressview.gif)

ProgressView
```java
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
```

activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.progressviewdemo.MainActivity">

    <com.example.progressviewdemo.ProgressView
        android:id="@+id/progressView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</android.support.constraint.ConstraintLayout>
```
MainActivity
```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressView.setOnClickListener({ progressView.startProgress() })
    }
}
```
传送门：

https://github.com/Urwateryi/ProgressViewDemo.git
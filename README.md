# DavidMarqueeControlProject
【Android】Android开发可以手动进行控制的跑马灯效果，包括从左到右，以及从右到左，

作者：程序员小冰，GitHub主页：https://github.com/QQ986945193 

新浪微博：http://weibo.com/mcxiaobing 

首先给大家看一下我们今天这个最终实现的效果图： 

![这里写图片描述](http://img.blog.csdn.net/20160918100607422)

这个主要是用到的自定义TextView，然后里面开一个线程进行，进行滚动效果。

我这里写了两个自定义TextView，分别是从左到右，以及从右到左。利用滚动

的偏移量，进行设置滚动的位置。然后利用一个变量，进行区分是否是滚动状态。

我这里给大家看一下代码吧，只给大家看一下其中一个自定义View的。

```
package davidmarqueecontrolproject.qq986945193.davidmarqueecontrolproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @码云OsChina ：http://git.oschina.net/MCXIAOBING
 * <p/>
 * 从左到右 滚动自定义TextView
 */
public class AutoText extends TextView implements Runnable {
    private int currentScrollX;// 当前滚动的位置
    private boolean isStop = false;
    private int textWidth;
    private boolean isMeasure = false;

    public AutoText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public AutoText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if (!isMeasure) {// 文字宽度只需获取一次就可以了
            getTextWidth();
            isMeasure = true;
        }
    }

    /**
     * 获取文字宽度
     */
    private void getTextWidth() {
        Paint paint = this.getPaint();
        String str = this.getText().toString();
        textWidth = (int) paint.measureText(str);
    }

    // 重写setText 在setText的时候重新计算text的宽度
    @Override
    public void setText(CharSequence text, BufferType type) {
        // TODO Auto-generated method stub
        super.setText(text, type);
        this.isMeasure = false;
    }

    @Override
    public void run() {
        currentScrollX -= 2;// 滚动速度
        scrollTo(currentScrollX, 0);
        if (isStop) {
            return;
        }
        if (getScrollX() <= -(this.getWidth())) {
            scrollTo(textWidth, 0);
            currentScrollX = textWidth;
            // return;
        }
        postDelayed(this, 5);
    }

    // 开始滚动
    public void startScroll() {
        isStop = false;
        this.removeCallbacks(this);
        post(this);
    }

    // 停止滚动
    public void stopScroll() {
        isStop = true;
        // textWidth=currentScrollX; //随时停止
    }

    // 从头开始滚动
    public void startFor0() {
        currentScrollX = 0;
        startScroll();
    }
}

```

布局我就不写了，直接引用这个view就行了。然后java类中看一下吧，其实
也是直接调用它的方法。

```
package davidmarqueecontrolproject.qq986945193.davidmarqueecontrolproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import davidmarqueecontrolproject.qq986945193.davidmarqueecontrolproject.MarqueeText;
import davidmarqueecontrolproject.qq986945193.davidmarqueecontrolproject.R;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @码云OsChina ：http://git.oschina.net/MCXIAOBING
 */
public class MainActivity extends Activity {
	private MarqueeText test;
	private AutoText auto_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		test = (MarqueeText) findViewById(R.id.test);
		auto_text = (AutoText) findViewById(R.id.auto_text);
	}

	public void start(View v) {
		test.startScroll();
//		auto_text.startScroll();
	}

	public void stop(View v) {
		test.stopScroll();
//		auto_text.stopScroll();
	}

	public void startFor0(View v) {
		test.startFor0();
//		auto_text.startFor0();
	}

}

```

好了。到此结束吧，不懂得请留言或者私信。项目源代码就是此project。
最后直接运行即可看到上面的效果。如果对您有帮助，欢迎star，fork。。。

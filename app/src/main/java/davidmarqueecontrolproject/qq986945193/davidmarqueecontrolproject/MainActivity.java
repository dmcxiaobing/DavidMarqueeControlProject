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

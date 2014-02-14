package com.kkbox.toolkit.carousel;

import java.util.Random;

import com.kkbox.toolkit.carousel.R;
import com.kkbox.toolkit.carousel.utils.CarouselAdapter;
import com.kkbox.toolkit.carousel.utils.CarouselPager;
import com.kkbox.toolkit.carousel.utils.VerticalViewPager.PageTransformer;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private CarouselPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewpager();
    }
    
    private void setViewpager() {
        mViewPager = (CarouselPager) findViewById(R.id.vp_main);
        MyPagerAdapter adapter = new MyPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
    } 
    
    private class MyPagerAdapter extends CarouselAdapter {

        private Random mRandom = new Random();

        @Override
        public int getRealCount() {
            return 100;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            RelativeLayout rl = new RelativeLayout(getApplicationContext());
            RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, 
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            rlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            
            TextView tv = new TextView(MainActivity.this);
            tv.setLayoutParams(rlp);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(30);
            tv.setBackgroundColor(Color.rgb(mRandom.nextInt(255),
                    mRandom.nextInt(255), mRandom.nextInt(255)));
            tv.setTextColor(Color.WHITE);
            int realPosition = getRealPosition(position);
            tv.setText("Pager: " + realPosition);
            rl.addView(tv);
            
            Log.w("position", "position:" + position);
            container.addView(rl);
            return rl;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}

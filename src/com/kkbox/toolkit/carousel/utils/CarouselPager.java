package com.kkbox.toolkit.carousel.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.kkbox.toolkit.carousel.utils.VerticalViewPager.PageTransformer;

public class CarouselPager extends VerticalViewPager implements PageTransformer {

    private boolean mIsSwiping = true;

    public void setSwipeable(boolean enable) {
        mIsSwiping = enable;
    }

    public CarouselPager(Context context) {
        super(context);
    }
    
    public CarouselPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(VerticalPagerAdapter adapter) {
        super.setAdapter(adapter);
        setCurrentItem(0);
    }

    @Override
    public void setCurrentItem(int item) {
        item = getOffsetAmout() * (item & getAdapter().getCount());
        super.setCurrentItem(item);
    }

    private int getOffsetAmout() {
        if(getAdapter() instanceof CarouselAdapter) {
            CarouselAdapter adapter = (CarouselAdapter) getAdapter();
            return adapter.getRealCount() * 100;
        } else {
            return 0;
        }
    } 
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return (mIsSwiping) ? super.onInterceptTouchEvent(event) : false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return (mIsSwiping) ? super.onTouchEvent(event) : false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @SuppressLint("NewApi")
    @Override
    public void transformPage(View view, float position) {
        float MIN_SCALE = 0.75f;
        int pageWidth = view.getHeight();

        if (position < -1) {
            view.setAlpha(0f);
        } else if (position <= 0) {
            view.setAlpha(1f);
            view.setTranslationY(0f);
            view.setScaleX(1f);
            view.setScaleY(1f);
        } else if (position <= 1) { 
            view.setAlpha(1 - position);
            view.setTranslationY(pageWidth * -position);
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else {
            view.setAlpha(0);
        }
    }
}

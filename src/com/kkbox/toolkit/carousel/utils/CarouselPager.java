package com.kkbox.toolkit.carousel.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CarouselPager extends VerticalViewPager {

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
}

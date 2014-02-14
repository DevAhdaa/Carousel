package com.kkbox.toolkit.carousel.utils;

import android.view.View;
import android.view.ViewGroup;

public abstract class CarouselAdapter extends VerticalPagerAdapter {

    private boolean mIsInfinity = false;

    public void setInfiniteMode(boolean enable) {
        mIsInfinity = enable;
    } 

    public abstract int getRealCount(); 
    public int getRealPosition(int position) {
        return position % getRealCount();
    };
    
    @Override
    public int getCount() {
        return (mIsInfinity) ? Integer.MAX_VALUE : getRealCount();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, getRealPosition(position));
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, getRealPosition(position), object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public float getPageHeight(int position) {
        return 1.0f;
    }
}

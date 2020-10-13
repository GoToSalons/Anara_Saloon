package com.anara.salon.Adapters;

import android.annotation.SuppressLint;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.anara.salon.Activities.SingleSalonActivity;
import com.anara.salon.R;
import com.bumptech.glide.Glide;
import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<String> mSliderItems;
    SingleSalonActivity singleSalonActivity;

    public SliderAdapter(List<String> mSliderItems, SingleSalonActivity singleSalonActivity) {
        this.mSliderItems = mSliderItems;
        this.singleSalonActivity = singleSalonActivity;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View imageLayout = singleSalonActivity.getLayoutInflater().inflate(R.layout.slider_adapter, container, false);
//        assert imageLayout != null;
        ImageView imageView = imageLayout.findViewById(R.id.imageView_slider_adapter);

        Glide.with(singleSalonActivity).load(mSliderItems.get(position)).centerCrop().into(imageView);

        container.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);

    }
    @Override
    public Parcelable saveState() {
        return null;
    }

}

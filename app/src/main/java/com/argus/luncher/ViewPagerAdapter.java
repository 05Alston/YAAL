package com.argus.luncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<PagerObject> pagerAppList;
    public ViewPagerAdapter(Context context,
                            ArrayList<PagerObject> pagerAppList) {
        this.context = context;
        this.pagerAppList = pagerAppList;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        LayoutInflater inflater = LayoutInflater.from(context);

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pager_layout,
                container, false);

        final GridView mGridView = layout.findViewById(R.id.HomeGrid);
        mGridView.setAdapter(new AppAdapter(context,
                pagerAppList.get(position).getAppList()));
//        return super.instantiateItem(container, position);
        container.addView(layout);
        return layout;
    }
    @Override
    public int getCount() {
        return pagerAppList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}

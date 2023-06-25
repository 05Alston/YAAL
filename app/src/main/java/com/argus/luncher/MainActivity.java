package com.argus.luncher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    boolean isBottom = true;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseHome();
        initialiseDrawer();
    }

    private void initialiseHome() {

        ArrayList<PagerObject> pagerAppList = new ArrayList<>();
        ArrayList<AppObject> appList = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            appList.add(new AppObject("", "",
                    getResources().getDrawable(R.drawable.ic_launcher_background)));

        pagerAppList.add(new PagerObject(appList));
        mViewPager = findViewById(R.id.ViewPager);
        mViewPager.setAdapter(new ViewPagerAdapter(this, pagerAppList));
    }


    List<AppObject> installedAppList = new ArrayList<>();
    private void initialiseDrawer() {
        View BottomSheet = findViewById(R.id.DrawerFrame);
        final GridView DrawerGrid = findViewById(R.id.DrawerGrid);

        final BottomSheetBehavior<View> DrawerBottomSheetBehavior =
                BottomSheetBehavior.from(BottomSheet);

        DrawerBottomSheetBehavior.setHideable(false);
        DrawerBottomSheetBehavior.setPeekHeight(300);

        installedAppList = getInstalledAppList();
        DrawerGrid.setAdapter(new AppAdapter(getApplicationContext(), installedAppList));

        DrawerBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_HIDDEN && DrawerGrid.getChildAt(0).getY() != 0)
                    DrawerBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                if(newState == BottomSheetBehavior.STATE_DRAGGING && DrawerGrid.getChildAt(0).getY() != 0)
                    DrawerBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private List<AppObject> getInstalledAppList() {
        List<AppObject> list = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> untreatedAppList =
                getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);

        for( ResolveInfo untreatedApp: untreatedAppList){
            String appName =
                    untreatedApp.activityInfo.loadLabel(getPackageManager()).toString();
            String appPackageName =
                    untreatedApp.activityInfo.packageName;
            Drawable appIcon =
                    untreatedApp.activityInfo.loadIcon(getPackageManager());

            AppObject app = new AppObject(appPackageName, appName, appIcon);
            if(!list.contains(app))
                list.add(app);
        }

        return list;
    }
}
package com.argus.luncher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseDrawer();
    }


    List<AppObject> appList = new ArrayList<>();
    private void initialiseDrawer() {
        View BottomSheet = findViewById(R.id.DrawerFrame);
        final GridView DrawerGrid = findViewById(R.id.DrawerGrid);

        BottomSheetBehavior<View> DrawerBottomSheetBehavior =
                BottomSheetBehavior.from(BottomSheet);

        DrawerBottomSheetBehavior.setHideable(false);
        DrawerBottomSheetBehavior.setPeekHeight(300);

        for (int i = 0; i < 20; i++)
            appList.add(new AppObject("", String.valueOf(i),
                    getResources().getDrawable(R.drawable.ic_launcher_background)));
        DrawerGrid.setAdapter(new AppAdapter(getApplicationContext(), appList));
    }
}
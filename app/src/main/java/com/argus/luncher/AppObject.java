package com.argus.luncher;

import android.graphics.drawable.Drawable;

public class AppObject {
    private String name, packageName;
    private Drawable icon;

    public AppObject(String packageName, String name, Drawable icon){
        this.name = name;
        this.packageName = packageName;
        this.icon = icon;
    }

    public String getPackageName(){return packageName;}
    public String getName(){return name;}
    public Drawable getIcon(){return icon;}
}

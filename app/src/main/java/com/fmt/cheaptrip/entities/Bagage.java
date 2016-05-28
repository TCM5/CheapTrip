package com.fmt.cheaptrip.entities;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;

/**
 * Created by santostc on 28-05-2016.
 */
public class Bagage {

    private String key;
    private String desc;
    private Drawable icon;

    public Bagage() {

    }

    public Bagage(String key, String desc, Drawable icon) {
        this.key = key;
        this.desc = desc;
        this.icon = icon;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return getDesc().toString();
    }
}

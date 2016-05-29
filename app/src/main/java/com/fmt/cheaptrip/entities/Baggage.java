package com.fmt.cheaptrip.entities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.fmt.cheaptrip.R;

/**
 * Created by santostc on 28-05-2016.
 */
public class Baggage {

    public static final String BAGGAGE_TYPE_SMALL_KEY = "1";
    public static final String BAGGAGE_TYPE_MEDIUM_KEY = "2";
    public static final String BAGGAGE_TYPE_LARGE_KEY = "3";

    private static final String BAGGAGE_TYPE_SMALL_DESC = "Small";
    private static final String BAGGAGE_TYPE_MEDIUM_DESC = "Medium";
    private static final String BAGGAGE_TYPE_LARGE_DESC = "Large";

    private String key;
    private String desc;
    private Drawable icon;

    public Baggage() {

    }

    public Baggage(String key, Context context) {
        this.key = key;

        setDescByKey(key);
        setIconByKey(key, context);
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

    public void setDescByKey(String key) {
        switch (key) {
            case BAGGAGE_TYPE_SMALL_KEY:
                this.desc = BAGGAGE_TYPE_SMALL_DESC;
                break;
            case BAGGAGE_TYPE_MEDIUM_KEY:
                this.desc = BAGGAGE_TYPE_MEDIUM_DESC;
                break;
            case BAGGAGE_TYPE_LARGE_KEY:
                this.desc = BAGGAGE_TYPE_LARGE_DESC;
                break;
            default:
                this.desc = "N/A";
                break;
        }
    }

    public void setIconByKey(String key, Context context) {
        switch (key) {
            case BAGGAGE_TYPE_SMALL_KEY:
                this.icon = ContextCompat.getDrawable(context, R.drawable.bagage_icon_small);
                break;
            case BAGGAGE_TYPE_MEDIUM_KEY:
                this.icon = ContextCompat.getDrawable(context, R.drawable.bagage_icon_medium);
                break;
            case BAGGAGE_TYPE_LARGE_KEY:
                this.icon = ContextCompat.getDrawable(context, R.drawable.bagage_icon_large);
                break;
            default:
                //Nothing to do here
                break;
        }
    }

    @Override
    public String toString() {
        return getDesc().toString();
    }
}

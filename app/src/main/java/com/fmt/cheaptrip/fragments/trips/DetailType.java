package com.fmt.cheaptrip.fragments.trips;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by santostc on 29-05-2016.
 */
public enum DetailType implements Parcelable {

    GIVEN, RECEIVED, SEARCHED;

    public static final Parcelable.Creator<DetailType> CREATOR = new Creator<DetailType>() {

        @Override
        public DetailType[] newArray(int size) {
            return new DetailType[size];
        }

        @Override
        public DetailType createFromParcel(Parcel source) {
            return DetailType.values()[source.readInt()];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ordinal());
    }

}

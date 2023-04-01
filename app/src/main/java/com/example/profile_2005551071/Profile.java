package com.example.profile_2005551071;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    String nama;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
    }

    public Profile(String nama){
        this.nama = nama;
    }
    public String getNama(){
        return this.nama;
    }
    protected Profile(Parcel in) {
        this.nama = in.readString();
    }
    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}

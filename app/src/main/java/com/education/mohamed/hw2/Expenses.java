package com.education.mohamed.hw2;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Expenses implements Parcelable {
    long _id;
    String name, date;
    Double price;
    Uri image;
    int category;


    public Expenses(String name, int category, String date, Double price, Uri image) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.price = price;
        this.image=image;
    }
    public Expenses()
    {
    }



    public static Creator<Expenses> getCREATOR() {
        return CREATOR;
    }

    protected Expenses(Parcel in) {
        _id=in.readLong();
        name = in.readString();
        category = in.readInt();
        date = in.readString();
        if (in.readByte() == 0)
        {
            price = null;
        }
        else
        {
            price = in.readDouble();
        }
        image= Uri.parse(in.readString());
    }

    public static final Creator<Expenses> CREATOR = new Creator<Expenses>() {
        @Override
        public Expenses createFromParcel(Parcel in) {
            return new Expenses(in);
        }

        @Override
        public Expenses[] newArray(int size) {
            return new Expenses[size];
        }
    };


    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Expenses{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", image=" + image +
                ", category=" + category +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(_id);
        parcel.writeString(name);
        parcel.writeInt(category);
        parcel.writeString(date);
        if (price == null)
        {
            parcel.writeByte((byte) 0);
        } else
        {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(price);
        }
        parcel.writeString(String.valueOf(image));
    }
}

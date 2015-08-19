package us.allok.megacont;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private String name, phone, city;

    public Contact(String n, String p, String c){
        name = n;
        phone = p;
        city = c;
    }

    public Contact(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);
        name = data[0];
        phone = data[1];
        city = data[2];
    }

    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String getCity(){
        return city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] { name, phone, city });
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {

        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}

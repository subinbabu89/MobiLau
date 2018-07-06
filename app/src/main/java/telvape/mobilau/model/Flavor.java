package telvape.mobilau.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Created by sbabu on 3/1/18.
 */

@SuppressWarnings("unused")
public class Flavor implements Parcelable{

    @SerializedName("id")
    private int id;
    @SerializedName("juice_name")
    private String title;
    @SerializedName("type")
    private String description;

    private int percentage = 0;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    protected Flavor(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        percentage = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(percentage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Flavor> CREATOR = new Parcelable.Creator<Flavor>() {
        @Override
        public Flavor createFromParcel(Parcel in) {
            return new Flavor(in);
        }

        @Override
        public Flavor[] newArray(int size) {
            return new Flavor[size];
        }
    };
}

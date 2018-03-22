package telvape.mobilau.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sbabu on 3/21/18.
 */

public class Juice implements Parcelable{
    @SerializedName("juices")
    private String name;
    @SerializedName("description")
    private String desc;
    @SerializedName("rating")
    private String rating;
    @SerializedName("pgvg_rating")
    private String pgvg;
    @SerializedName("flavors")
    private String flavors;
    @SerializedName("nicotine")
    private String nicotine;
    @SerializedName("ingredients")
    private String ingredients;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getRating() {
        return rating;
    }

    public String getPgvg() {
        return pgvg;
    }

    public List<String> getFlavors() {
        List<String> flavors = new ArrayList<>();
        flavors.addAll(Arrays.asList(this.flavors.split(",")));
        return flavors;
    }

    public String getNicotine() {
        return nicotine;
    }

    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.addAll(Arrays.asList(this.ingredients.split(",")));
        return ingredients;
    }

    protected Juice(Parcel in) {
        name = in.readString();
        desc = in.readString();
        rating = in.readString();
        pgvg = in.readString();
        flavors = in.readString();
        nicotine = in.readString();
        ingredients = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(rating);
        dest.writeString(pgvg);
        dest.writeString(flavors);
        dest.writeString(nicotine);
        dest.writeString(ingredients);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Juice> CREATOR = new Parcelable.Creator<Juice>() {
        @Override
        public Juice createFromParcel(Parcel in) {
            return new Juice(in);
        }

        @Override
        public Juice[] newArray(int size) {
            return new Juice[size];
        }
    };
}

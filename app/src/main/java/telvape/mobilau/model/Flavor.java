package telvape.mobilau.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Created by sbabu on 3/1/18.
 */

public class Flavor {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
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
}

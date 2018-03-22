package telvape.mobilau.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sbabu on 3/21/18.
 */

public class JuiceResponse {
    @SerializedName("juices")
    List<Juice> juices;

    public List<Juice> getJuices() {
        return juices;
    }
}

package telvape.mobilau.model;

/**
 *
 * Created by sbabu on 3/1/18.
 */

public class Flavor {
    private String name;
    private int percentage;

    public Flavor(String name) {
        this.name = name;
        this.percentage = 0;
    }

    public String getName() {
        return name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}

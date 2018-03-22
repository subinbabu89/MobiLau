package telvape.mobilau.view.juice.custom;

import java.util.List;

import telvape.mobilau.model.Flavor;

/**
 *
 * Created by sbabu on 2/27/18.
 */

public interface CustomJuiceView {

    void displayFlavors(List<Flavor> ingredients);

    void addFlavor(Flavor flavor);

    void removeFlavor(Flavor flavor);
}

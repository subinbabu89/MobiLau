package telvape.mobilau.view;

import java.util.List;

import telvape.mobilau.model.Flavor;

/**
 *
 * Created by sbabu on 2/27/18.
 */

public interface MainView {

    void displayFlavors(List<Flavor> ingredients);

    void addFlavor(Flavor flavor);
}

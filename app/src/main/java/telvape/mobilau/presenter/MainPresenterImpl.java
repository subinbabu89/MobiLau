package telvape.mobilau.presenter;

import java.util.ArrayList;
import java.util.List;

import telvape.mobilau.model.Flavor;
import telvape.mobilau.view.MainView;

/**
 *
 * Created by sbabu on 2/27/18.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void fetchIngredients() {
        List<Flavor> ingredients = new ArrayList<>();
        ingredients.add(new Flavor("Spearmint"));
        ingredients.add(new Flavor("Fruit"));
        ingredients.add(new Flavor("Cream"));
        ingredients.add(new Flavor("Tears"));
        ingredients.add(new Flavor("Spearmint"));
        ingredients.add(new Flavor("Fruit"));
        ingredients.add(new Flavor("Cream"));
        ingredients.add(new Flavor("Tears"));
        ingredients.add(new Flavor("Spearmint"));
        ingredients.add(new Flavor("Fruit"));
        ingredients.add(new Flavor("Cream"));
        ingredients.add(new Flavor("Tears"));
        ingredients.add(new Flavor("Spearmint"));
        ingredients.add(new Flavor("Fruit"));
        ingredients.add(new Flavor("Cream"));
        ingredients.add(new Flavor("Tears"));

        mainView.displayFlavors(ingredients);
    }


}

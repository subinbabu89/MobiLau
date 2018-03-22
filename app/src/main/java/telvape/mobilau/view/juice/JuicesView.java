package telvape.mobilau.view.juice;

import java.util.List;

import telvape.mobilau.model.Juice;

/**
 * Created by sbabu on 3/21/18.
 */

public interface JuicesView {

    void displayJuices(List<Juice> ingredients);

    void navigateToDetail(Juice juice);
}

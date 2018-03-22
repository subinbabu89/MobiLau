package telvape.mobilau.presenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import telvape.mobilau.model.Flavor;
import telvape.mobilau.network.FlavorFetchInterface;
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

        FlavorFetchInterface flavorFetchInterface = FlavorFetchInterface.FlavorFetchAPI.flavorFetchAPI();
        Call<List<Flavor>> flavors = flavorFetchInterface.getFlavors();

        flavors.enqueue(new Callback<List<Flavor>>() {
            @Override
            public void onResponse(Call<List<Flavor>> call, Response<List<Flavor>> response) {
                mainView.displayFlavors(response.body());
            }

            @Override
            public void onFailure(Call<List<Flavor>> call, Throwable t) {

            }
        });
    }
}

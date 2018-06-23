package telvape.mobilau.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import telvape.mobilau.model.Flavor;
import telvape.mobilau.network.FlavorFetchInterface;
import telvape.mobilau.view.flavors.FlavorsView;

/**
 *
 * Created by sbabu on 2/27/18.
 */
@SuppressWarnings("unused")
public class CustomJuicePresenterImpl implements CustomJuicePresenter {
    private static final String TAG = "CustomJuicePresenterImp";

    private FlavorsView flavorsView;

    public CustomJuicePresenterImpl(FlavorsView flavorsView) {
        this.flavorsView = flavorsView;
    }

    @Override
    public void fetchIngredients() {

        FlavorFetchInterface flavorFetchInterface = FlavorFetchInterface.FlavorFetchAPI.flavorFetchAPI();
        Call<List<Flavor>> flavors = flavorFetchInterface.getFlavors();

        flavors.enqueue(new Callback<List<Flavor>>() {
            @Override
            public void onResponse(@NonNull Call<List<Flavor>> call, @NonNull Response<List<Flavor>> response) {
                flavorsView.displayFlavors(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Flavor>> call, @NonNull Throwable t) {

            }
        });
    }
}

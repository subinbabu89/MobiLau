package telvape.mobilau.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import telvape.mobilau.model.Flavor;
import telvape.mobilau.network.FlavorFetchInterface;
import telvape.mobilau.view.juice.custom.CustomJuiceView;

/**
 *
 * Created by sbabu on 2/27/18.
 */
@SuppressWarnings("unused")
public class CustomJuicePresenterImpl implements CustomJuicePresenter {
    private static final String TAG = "CustomJuicePresenterImp";

    private CustomJuiceView customJuiceView;

    public CustomJuicePresenterImpl(CustomJuiceView customJuiceView) {
        this.customJuiceView = customJuiceView;
    }

    @Override
    public void fetchIngredients() {

        FlavorFetchInterface flavorFetchInterface = FlavorFetchInterface.FlavorFetchAPI.flavorFetchAPI();
        Call<List<Flavor>> flavors = flavorFetchInterface.getFlavors();

        flavors.enqueue(new Callback<List<Flavor>>() {
            @Override
            public void onResponse(@NonNull Call<List<Flavor>> call, @NonNull Response<List<Flavor>> response) {
                customJuiceView.displayFlavors(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Flavor>> call, @NonNull Throwable t) {

            }
        });
    }
}

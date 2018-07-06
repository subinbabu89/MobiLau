package telvape.mobilau.presenter;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import telvape.mobilau.model.Juice;
import telvape.mobilau.network.JuiceFetchInterface;

public class JuiceAddPresenterImpl implements JuiceAddPresenter {
    private static final String TAG = "JuiceAddPresenterImpl";

    @Override
    public void putJuice(Juice juice) {
        JuiceFetchInterface juiceFetchInterface = JuiceFetchInterface.JuicesFetchAPI.api();
        Call<String> stringCall = juiceFetchInterface.putJuice(juice);
        stringCall.request().body();
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}

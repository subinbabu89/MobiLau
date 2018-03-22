package telvape.mobilau.presenter;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import telvape.mobilau.model.Juice;
import telvape.mobilau.model.JuiceResponse;
import telvape.mobilau.network.JuiceFetchInterface;
import telvape.mobilau.view.JuicesView;

/**
 * Created by sbabu on 3/21/18.
 */

public class JuicesPresenterImpl implements JuicesPresenter {

    private static final String TAG = "JuicesPresenterImpl";

    private JuicesView juicesView;

    public JuicesPresenterImpl(JuicesView juicesView) {
        this.juicesView = juicesView;
    }

    @Override
    public void fetchJuices() {
        JuiceFetchInterface juiceFetchInterface = JuiceFetchInterface.JuicesFetchAPI.api();
        Call<JuiceResponse> juices = juiceFetchInterface.getJuices();
        juices.enqueue(new Callback<JuiceResponse>() {
            @Override
            public void onResponse(Call<JuiceResponse> call, Response<JuiceResponse> response) {
                Log.d(TAG, "onResponse: called");
                juicesView.displayJuices(response.body().getJuices());
            }

            @Override
            public void onFailure(Call<JuiceResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: called "+t.getMessage());
            }
        });

    }
}

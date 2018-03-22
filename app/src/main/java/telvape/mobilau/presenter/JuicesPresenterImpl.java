package telvape.mobilau.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import telvape.mobilau.model.JuiceResponse;
import telvape.mobilau.network.JuiceFetchInterface;
import telvape.mobilau.view.juice.JuicesView;

/**
 *
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
            public void onResponse(@NonNull Call<JuiceResponse> call, @NonNull Response<JuiceResponse> response) {
                Log.d(TAG, "onResponse: called");
                if(response.body()!=null && null != response.body().getJuices())
                    juicesView.displayJuices(response.body().getJuices());
            }

            @Override
            public void onFailure(@NonNull Call<JuiceResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: called "+t.getMessage());
            }
        });

    }
}

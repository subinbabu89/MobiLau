package telvape.mobilau.presenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import telvape.mobilau.model.Job;
import telvape.mobilau.model.Jobs;
import telvape.mobilau.network.JobFetchInterface;
import telvape.mobilau.view.MainView;

/**
 * Created by sbabu on 2/27/18.
 */

public class MainPresenterImpl implements MainPresenter {

    MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void fetchJobs() {
        JobFetchInterface jobFetchInterface = JobFetchInterface.JobFetchApi.jobFetchApi();
        Call<Jobs> jobs = jobFetchInterface.getJobs();
        jobs.enqueue(new Callback<Jobs>() {
            @Override
            public void onResponse(Call<Jobs> call, Response<Jobs> response) {
                mainView.displayJobs(response.body().getCurrent());
            }

            @Override
            public void onFailure(Call<Jobs> call, Throwable t) {
                mainView.displayJobs(new ArrayList<Job>());
            }
        });
    }
}

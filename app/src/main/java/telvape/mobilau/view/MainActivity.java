package telvape.mobilau.view;

import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import telvape.mobilau.R;
import telvape.mobilau.BaseActivity;
import telvape.mobilau.adapter.JobAdapter;
import telvape.mobilau.model.Job;
import telvape.mobilau.presenter.MainPresenter;
import telvape.mobilau.presenter.MainPresenterImpl;

public class MainActivity extends BaseActivity implements MainView {

    private static final String TAG = "MainActivity";

    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.recyclerview_jobs)
    RecyclerView recyclerView;

    MainPresenter mainPresenter;

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewComponents() {
        mainPresenter = new MainPresenterImpl(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter.fetchJobs();
    }

    @OnClick(R.id.floatingActionButton)
    public void startMix(){

        AnimatedVectorDrawableCompat animatedVector = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_anim);
        floatingActionButton.setImageDrawable(animatedVector);

        Drawable d = floatingActionButton.getDrawable();
        ((AnimatedVectorDrawableCompat)d).registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
                Log.d(TAG, "onAnimationStart: animation started");
            }

            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                Log.d(TAG, "onAnimationEnd: animation ended");
            }
        });
        ((AnimatedVectorDrawableCompat)d ).start();
    }

    @Override
    public void displayJobs(List<Job> jobs) {
        recyclerView.setAdapter(new JobAdapter(jobs));
    }
}

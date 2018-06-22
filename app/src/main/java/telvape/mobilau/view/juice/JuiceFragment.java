package telvape.mobilau.view.juice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import telvape.mobilau.R;
import telvape.mobilau.adapter.JuicesAdapter;
import telvape.mobilau.custom.IngredientItemDecoration;
import telvape.mobilau.model.Juice;
import telvape.mobilau.presenter.JuicesPresenter;
import telvape.mobilau.presenter.JuicesPresenterImpl;
import telvape.mobilau.view.BaseFragment;
import telvape.mobilau.view.TestFragment;
import telvape.mobilau.view.juice.detail.JuiceDetailActivity;

public class JuiceFragment extends BaseFragment implements JuicesView{

    @BindView(R.id.recyclerview_juices)
    RecyclerView recyclerViewJuices;

    @Override
    public int initLayoutID() {
        return R.layout.activity_juices;
    }

    @Override
    public void initViews(View parent) {

    }

    @Override
    public void customizeFragment() {
        JuicesPresenter juicesPresenter = new JuicesPresenterImpl(this);
        juicesPresenter.fetchJuices();
    }

    @Override
    public void displayJuices(List<Juice> juices) {
        recyclerViewJuices.setLayoutManager(new GridLayoutManager(getContext(),2));
        int spacinginPixels = getResources().getDimensionPixelSize(R.dimen.ingredient_spacing);
        recyclerViewJuices.addItemDecoration(new IngredientItemDecoration(spacinginPixels));
        recyclerViewJuices.setAdapter(new JuicesAdapter(this,juices));
    }

    @Override
    public void navigateToDetail(Juice juice) {
        Intent intent = new Intent(getActivity(),JuiceDetailActivity.class);
        intent.putExtra("juice",juice);
        startActivity(intent);
    }
}

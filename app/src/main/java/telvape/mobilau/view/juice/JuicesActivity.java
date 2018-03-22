package telvape.mobilau.view.juice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import telvape.mobilau.BaseActivity;
import telvape.mobilau.R;
import telvape.mobilau.adapter.JuicesAdapter;
import telvape.mobilau.custom.IngredientItemDecoration;
import telvape.mobilau.model.Juice;
import telvape.mobilau.presenter.JuicesPresenter;
import telvape.mobilau.presenter.JuicesPresenterImpl;
import telvape.mobilau.view.juice.detail.JuiceDetailActivity;

/**
 *
 * Created by sbabu on 3/21/18.
 */

@SuppressLint("Registered")
public class JuicesActivity extends BaseActivity implements JuicesView{

    @BindView(R.id.recyclerview_juices)
    RecyclerView recyclerViewJuices;

    @Override
    public int getContentLayout() {
        return R.layout.activity_juices;
    }

    @Override
    public void initViewComponents() {
        JuicesPresenter juicesPresenter = new JuicesPresenterImpl(this);
        juicesPresenter.fetchJuices();
    }

    @Override
    public void displayJuices(List<Juice> juices) {
        recyclerViewJuices.setLayoutManager(new GridLayoutManager(this,2));
        int spacinginPixels = getResources().getDimensionPixelSize(R.dimen.ingredient_spacing);
        recyclerViewJuices.addItemDecoration(new IngredientItemDecoration(spacinginPixels));
        recyclerViewJuices.setAdapter(new JuicesAdapter(this,juices));
    }

    @Override
    public void navigateToDetail(Juice juice) {
        Intent intent = new Intent(JuicesActivity.this,JuiceDetailActivity.class);
        intent.putExtra("juice",juice);
        startActivity(intent);
    }
}

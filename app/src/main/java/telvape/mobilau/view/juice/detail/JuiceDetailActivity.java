package telvape.mobilau.view.juice.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import telvape.mobilau.view.BaseActivity;
import telvape.mobilau.R;
import telvape.mobilau.adapter.RecipeIngredientAdapter;
import telvape.mobilau.model.Juice;

public class JuiceDetailActivity extends BaseActivity {

    @BindView(R.id.txtv_name)
    TextView juiceName;
    @BindView(R.id.txtv_rating)
    TextView juiceRating;
    @BindView(R.id.txtv_pgvg)
    TextView juicePGVG;
    @BindView(R.id.txtv_description)
    TextView juiceDesc;
    @BindView(R.id.txtv_nicotine)
    TextView juiceNicotine;

    @BindView(R.id.recyclerview_ingredients)
    RecyclerView ingredients;

    @Override
    public int getContentLayout() {
        return R.layout.activity_juice_detail;
    }

    @Override
    public void initViewComponents() {
        Juice juice  = getIntent().getParcelableExtra("juice");

        juiceName.setText(juice.getName());
        juiceDesc.setText(juice.getDesc());
        juiceNicotine.setText(juice.getNicotine());
        juicePGVG.setText(juice.getPgvg());
        juiceRating.setText(juice.getRating());

        ingredients.setLayoutManager(new LinearLayoutManager(this));
        ingredients.setAdapter(new RecipeIngredientAdapter(juice.getIngredients()));
    }
}

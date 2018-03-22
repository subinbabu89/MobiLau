package telvape.mobilau.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import telvape.mobilau.BaseActivity;
import telvape.mobilau.R;
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
    }
}

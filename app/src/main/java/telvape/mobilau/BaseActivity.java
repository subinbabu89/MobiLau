package telvape.mobilau;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 *
 * Created by sbabu on 2/27/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public BaseActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        ButterKnife.bind(this);
        initViewComponents();
    }

    @LayoutRes
    public abstract int getContentLayout();
    public abstract void initViewComponents();

}

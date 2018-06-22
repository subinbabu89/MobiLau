package telvape.mobilau.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import telvape.mobilau.view.juice.JuiceFragment;

public abstract class BaseFragment extends Fragment {

    private View parent;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayoutID(), container, false);
        ButterKnife.bind(this, view);
        this.parent = view;
        initViews(view);
        customizeFragment();
        return view;
    }

    public View getParent() {
        return parent;
    }

    public BaseFragment initWithBundle(Bundle bundle){
        this.setArguments(bundle);
        return this;
    }
    public abstract @LayoutRes int initLayoutID();
    public abstract void initViews(View parent);
    public abstract void customizeFragment();
}

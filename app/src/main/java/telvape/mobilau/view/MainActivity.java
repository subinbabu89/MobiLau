package telvape.mobilau.view;

import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import telvape.mobilau.BaseActivity;
import telvape.mobilau.R;
import telvape.mobilau.adapter.AllFlavorAdapter;
import telvape.mobilau.custom.IngredientItemDecoration;
import telvape.mobilau.model.Flavor;
import telvape.mobilau.presenter.MainPresenter;
import telvape.mobilau.presenter.MainPresenterImpl;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.recyclerview_jobs)
    RecyclerView recyclerView;

    MainPresenter mainPresenter;

    List<Flavor> recipeFlavors;

    BottomSheetView bottomSheetView;

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewComponents() {
        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.fetchIngredients();

        recipeFlavors = new ArrayList<>();
        bottomSheetView = new BottomSheetImpl(this,recipeFlavors);
    }

    @Override
    public void displayFlavors(List<Flavor> flavors) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        int spacinginPixels = getResources().getDimensionPixelSize(R.dimen.ingredient_spacing);
        recyclerView.addItemDecoration(new IngredientItemDecoration(spacinginPixels));
        recyclerView.setAdapter(new AllFlavorAdapter(this, flavors));
    }

    @Override
    public void addFlavor(Flavor flavor) {
        animateMix();
        recipeFlavors.add(flavor);
        recalculatePercentages(recipeFlavors);
        bottomSheetView.showBottomSheet();
    }

    @Override
    public void removeFlavor(Flavor flavor) {
        recipeFlavors.remove(flavor);
        if(recipeFlavors.size()==0){
            bottomSheetView.hideBottomSheet();
        }else{
            recalculatePercentages(recipeFlavors);
            bottomSheetView.showBottomSheet();
        }

    }

    private void animateMix() {
        AnimatedVectorDrawableCompat animatedVector = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_anim);
        floatingActionButton.setImageDrawable(animatedVector);

        Drawable d = floatingActionButton.getDrawable();
        ((AnimatedVectorDrawableCompat) d).start();
    }

    void recalculatePercentages(List<Flavor> flavors){
        int size = flavors.size();
        for (int i = 0; i < size; i++) {
            flavors.get(i).setPercentage(100/size);
        }
    }


}

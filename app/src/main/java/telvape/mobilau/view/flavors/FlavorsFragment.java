package telvape.mobilau.view.flavors;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import telvape.mobilau.R;
import telvape.mobilau.adapter.AllFlavorAdapter;
import telvape.mobilau.custom.IngredientItemDecoration;
import telvape.mobilau.model.Flavor;
import telvape.mobilau.model.Juice;
import telvape.mobilau.presenter.CustomJuicePresenter;
import telvape.mobilau.presenter.CustomJuicePresenterImpl;
import telvape.mobilau.view.BaseFragment;
import telvape.mobilau.view.ReviewFragment;
import telvape.mobilau.view.flavors.bottomsheet.BottomSheetImpl;
import telvape.mobilau.view.flavors.bottomsheet.BottomSheetView;

public class FlavorsFragment extends BaseFragment implements FlavorsView {

    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.recyclerview_jobs)
    RecyclerView recyclerView;

    CustomJuicePresenter customJuicePresenter;

    ArrayList<Flavor> recipeFlavors;

    BottomSheetView bottomSheetView;

    @BindView(R.id.bottom_sheet)
    ConstraintLayout llBottomSheet;

    @BindView(R.id.txtv_review)
    TextView review;

    @Override
    public int initLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(View parent) {

    }

    @Override
    public void customizeFragment() {
        customJuicePresenter = new CustomJuicePresenterImpl(this);
        customJuicePresenter.fetchIngredients();

        recipeFlavors = new ArrayList<>();
        bottomSheetView = new BottomSheetImpl(getContext(), getParent(), llBottomSheet, recipeFlavors, this);
    }

    @Override
    public void displayFlavors(List<Flavor> ingredients) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        int spacinginPixels = getResources().getDimensionPixelSize(R.dimen.ingredient_spacing);
        recyclerView.addItemDecoration(new IngredientItemDecoration(spacinginPixels));
        recyclerView.setAdapter(new AllFlavorAdapter(this, ingredients));
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
        if (recipeFlavors.size() == 0) {
            bottomSheetView.hideBottomSheet();
        } else {
            recalculatePercentages(recipeFlavors);
            bottomSheetView.showBottomSheet();
        }
    }

    private void animateMix() {
        AnimatedVectorDrawableCompat animatedVector = AnimatedVectorDrawableCompat.create(getContext(), R.drawable.avd_anim);
        floatingActionButton.setImageDrawable(animatedVector);

        Drawable d = floatingActionButton.getDrawable();
        ((AnimatedVectorDrawableCompat) d).start();
    }

    void recalculatePercentages(List<Flavor> flavors) {
        int size = flavors.size();
        for (int i = 0; i < size; i++) {
            flavors.get(i).setPercentage(100 / size);
        }
    }

    @OnClick(R.id.txtv_review)
    void review(){
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (Flavor flavor: flavors
//             ) {
//            stringBuilder.append(flavor.getTitle());
//            stringBuilder.append(",");
//        }
//        String s = stringBuilder.toString();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment = new ReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("juice",recipeFlavors);
        fragment.setArguments(bundle);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

}

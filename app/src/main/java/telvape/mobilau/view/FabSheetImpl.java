package telvape.mobilau.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import java.util.List;

import telvape.mobilau.R;
import telvape.mobilau.adapter.FabFlavorsAdapter;
import telvape.mobilau.custom.Fab;
import telvape.mobilau.custom.IngredientItemDecoration;
import telvape.mobilau.model.Flavor;

/**
 *
 * Created by sbabu on 3/2/18.
 */

public class FabSheetImpl implements FabSheetView {

    private MainView mainView;
    private List<Flavor> recipe;

    private Fab fab;
    private FabFlavorsAdapter fabIngredientsAdapter;

    FabSheetImpl(MainView mainView,List<Flavor> recipe) {
        this.mainView = mainView;
        this.recipe = recipe;

        initFabSheet();
    }

    @SuppressWarnings("unused")
    private void initFabSheet(){
        fab = ((MainActivity)mainView).findViewById(R.id.floatingActionButton);
        View sheetView = ((MainActivity)mainView).findViewById(R.id.fab_sheet);
        View overlay = ((MainActivity)mainView).findViewById(R.id.overlay);
        int sheetColor = ((MainActivity)mainView).getResources().getColor(R.color.colorPrimaryDark);
        int fabColor = ((MainActivity)mainView).getResources().getColor(R.color.colorAccent);

        RecyclerView ingredientList = new RecyclerView(((MainActivity)mainView));
        ingredientList.setLayoutManager(new LinearLayoutManager(((MainActivity)mainView)));
        int spacinginPixels = ((MainActivity)mainView).getResources().getDimensionPixelSize(R.dimen.ingredient_spacing);
        ingredientList.addItemDecoration(new IngredientItemDecoration(spacinginPixels));
        fabIngredientsAdapter = new FabFlavorsAdapter(recipe);
        ingredientList.setAdapter(fabIngredientsAdapter);
        ((CardView) sheetView).addView(ingredientList);

        // Initialize material sheet FAB
        MaterialSheetFab<Fab> materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay,
                sheetColor, fabColor);
    }

    @Override
    public void animateFab(float slideOffset) {
        fab.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start();
    }

    @Override
    public void notifyFabUpdate() {
        fabIngredientsAdapter.notifyDataSetChanged();
    }
}

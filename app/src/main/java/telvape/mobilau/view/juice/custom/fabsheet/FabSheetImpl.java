package telvape.mobilau.view.juice.custom.fabsheet;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import java.util.List;

import telvape.mobilau.R;
import telvape.mobilau.adapter.FabFlavorsAdapter;
import telvape.mobilau.custom.Fab;
import telvape.mobilau.custom.IngredientItemDecoration;
import telvape.mobilau.custom.RecyclerItemTouchHelper;
import telvape.mobilau.model.Flavor;
import telvape.mobilau.view.juice.custom.CustomJuiceActivity;
import telvape.mobilau.view.juice.custom.CustomJuiceView;

/**
 *
 * Created by sbabu on 3/2/18.
 */

public class FabSheetImpl implements FabSheetView,RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private CustomJuiceView customJuiceView;
    private List<Flavor> recipe;

    private Fab fab;
    private FabFlavorsAdapter fabIngredientsAdapter;

    public FabSheetImpl(CustomJuiceView customJuiceView, List<Flavor> recipe) {
        this.customJuiceView = customJuiceView;
        this.recipe = recipe;

        initFabSheet();
    }

    @SuppressWarnings("unused")
    private void initFabSheet(){
        fab = ((CustomJuiceActivity) customJuiceView).findViewById(R.id.floatingActionButton);
        View sheetView = ((CustomJuiceActivity) customJuiceView).findViewById(R.id.fab_sheet);
        View overlay = ((CustomJuiceActivity) customJuiceView).findViewById(R.id.overlay);
        int sheetColor = ((CustomJuiceActivity) customJuiceView).getResources().getColor(R.color.colorPrimaryDark);
        int fabColor = ((CustomJuiceActivity) customJuiceView).getResources().getColor(R.color.colorAccent);

        RecyclerView ingredientList = new RecyclerView(((CustomJuiceActivity) customJuiceView));
        ingredientList.setLayoutManager(new LinearLayoutManager(((CustomJuiceActivity) customJuiceView)));
        int spacinginPixels = ((CustomJuiceActivity) customJuiceView).getResources().getDimensionPixelSize(R.dimen.ingredient_spacing);
        ingredientList.addItemDecoration(new IngredientItemDecoration(spacinginPixels));
        fabIngredientsAdapter = new FabFlavorsAdapter(recipe);
        ingredientList.setAdapter(fabIngredientsAdapter);
        ((CardView) sheetView).addView(ingredientList);

        // Initialize material sheet FAB
        MaterialSheetFab<Fab> materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay,
                sheetColor, fabColor);

        customizeRecyclerView(ingredientList);
    }

    @Override
    public void animateFab(float slideOffset) {
        fab.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start();
    }

    @Override
    public void notifyFabUpdate() {
        fabIngredientsAdapter.notifyDataSetChanged();
    }


    void customizeRecyclerView(RecyclerView recyclerView){
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if(viewHolder instanceof FabFlavorsAdapter.ViewHolder){
            customJuiceView.removeFlavor(recipe.get(viewHolder.getAdapterPosition()));
        }
    }
}

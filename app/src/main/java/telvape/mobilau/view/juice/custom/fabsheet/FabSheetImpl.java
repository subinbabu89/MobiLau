package telvape.mobilau.view.juice.custom.fabsheet;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import telvape.mobilau.R;
import telvape.mobilau.adapter.FabFlavorsAdapter;
import telvape.mobilau.custom.Fab;
import telvape.mobilau.custom.IngredientItemDecoration;
import telvape.mobilau.custom.RecyclerItemTouchHelper;
import telvape.mobilau.model.Flavor;
import telvape.mobilau.view.juice.custom.CustomJuiceView;

/**
 * Created by sbabu on 3/2/18.
 */

public class FabSheetImpl implements FabSheetView, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private List<Flavor> recipe;

    @BindView(R.id.floatingActionButton)
    public Fab fab;
    @BindView(R.id.fab_sheet)
    public View sheetView;
    @BindView(R.id.overlay)
    public View overlay;

    private FabFlavorsAdapter fabIngredientsAdapter;
    private Context context;

    private CustomJuiceView customJuiceView;

    public FabSheetImpl(Context context, View parent, List<Flavor> recipe, CustomJuiceView customJuiceView) {
        ButterKnife.bind(this, parent);
        this.context = context;
        this.recipe = recipe;
        this.customJuiceView = customJuiceView;
        initFabSheet();
    }

    @SuppressWarnings("unused")
    private void initFabSheet() {
        int sheetColor = context.getResources().getColor(R.color.colorPrimaryDark);
        int fabColor = context.getResources().getColor(R.color.colorAccent);

        RecyclerView ingredientList = new RecyclerView(context);
        ingredientList.setLayoutManager(new LinearLayoutManager(context));
        int spacinginPixels = context.getResources().getDimensionPixelSize(R.dimen.ingredient_spacing);
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


    private void customizeRecyclerView(RecyclerView recyclerView) {
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof FabFlavorsAdapter.ViewHolder) {
            customJuiceView.removeFlavor(recipe.get(viewHolder.getAdapterPosition()));
        }
    }
}

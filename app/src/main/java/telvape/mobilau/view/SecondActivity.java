package telvape.mobilau.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import telvape.mobilau.BaseActivity;
import telvape.mobilau.R;
import telvape.mobilau.adapter.FabFlavorsAdapter;
import telvape.mobilau.custom.Fab;
import telvape.mobilau.model.Flavor;
import telvape.mobilau.model.Job;

public class SecondActivity extends BaseActivity {
    private static final String TAG = "SecondActivity";

    @BindView(R.id.txtv_job_name)
    TextView jobName;
    private MaterialSheetFab<Fab> materialSheetFab;

    RecyclerView ingredientList;

    @Override
    public int getContentLayout() {
        return R.layout.activity_second;
    }

    @Override
    public void initViewComponents() {
        Job job = getIntent().getParcelableExtra("key");
        jobName.setText(job.getJob_name());

        Fab fab = findViewById(R.id.fab);
        View sheetView = findViewById(R.id.fab_sheet);
        View overlay = findViewById(R.id.overlay);
        int sheetColor = getResources().getColor(R.color.colorPrimaryDark);
        int fabColor = getResources().getColor(R.color.colorAccent);

        initIngredientList();
        ((CardView)sheetView).addView(ingredientList);

        // Initialize material sheet FAB
        materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay,
                sheetColor, fabColor);

        materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
                // Called when the material sheet's "show" animation starts.
                Log.d(TAG, "onShowSheet: called");
            }

            @Override
            public void onSheetShown() {
                // Called when the material sheet's "show" animation ends.
                Log.d(TAG, "onSheetShown: called");
            }

            @Override
            public void onHideSheet() {
                // Called when the material sheet's "hide" animation starts.
                Log.d(TAG, "onHideSheet: called");
            }

            public void onSheetHidden() {
                // Called when the material sheet's "hide" animation ends.
                Log.d(TAG, "onSheetHidden: called");
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(materialSheetFab.isSheetVisible()){
            materialSheetFab.hideSheet();
        }else {
            super.onBackPressed();
        }
    }

    private void initIngredientList(){
        ingredientList = new RecyclerView(this);
        ingredientList.setLayoutManager(new LinearLayoutManager(this));
//        ingredientList.setAdapter(new FabFlavorsAdapter(ingredients));
    }
}

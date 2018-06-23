package telvape.mobilau.view.flavors.bottomsheet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import telvape.mobilau.R;
import telvape.mobilau.custom.ColorTemplate;
import telvape.mobilau.model.Flavor;
import telvape.mobilau.view.flavors.FlavorsView;
import telvape.mobilau.view.flavors.fabsheet.FabSheetImpl;
import telvape.mobilau.view.flavors.fabsheet.FabSheetView;
import telvape.mobilau.view.flavors.MultiSliderImpl;
import telvape.mobilau.view.flavors.MultiSliderView;

/**
 *
 * Created by sbabu on 3/2/18.
 */

public class BottomSheetImpl implements BottomSheetView {
    private static final String TAG = "BottomSheetImpl";

//    private FlavorsView customJuiceView;
    private List<Flavor> recipe;

    private MultiSliderView multiSliderView;
    private FabSheetView fabSheetView;

    private BottomSheetBehavior bottomSheetBehavior;
    @BindView(R.id.chart1)
    public PieChart mChart;
    private Typeface tf;

    private Context context;
    private View parent;

    public BottomSheetImpl(Context context, View parent, View bottomSheetLayout, List<Flavor> recipe,FlavorsView flavorsView) {
        this.parent = parent;
        this.context = context;
        this.recipe = recipe;
        this.multiSliderView = new MultiSliderImpl(bottomSheetLayout,this, recipe);
        ButterKnife.bind(this,bottomSheetLayout);

        initBottomView(bottomSheetLayout,recipe, flavorsView);
        initGraph();
        customizeBottomView();

    }

    private void initBottomView(View bottomSheetLayout, List<Flavor> recipe,FlavorsView flavorsView){

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        fabSheetView = new FabSheetImpl(context,parent,recipe, flavorsView);
    }

    private void customizeBottomView(){
        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.d(TAG, "onStateChanged: newstate "+newState);
                if(newState==BottomSheetBehavior.STATE_DRAGGING){
                    multiSliderView.notifySlider();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d(TAG, "onSlide: called");
                fabSheetView.animateFab(slideOffset);
            }
        });
    }

    private void notifyGraph(PieData data) {
        mChart.setData(data);
        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    @Override
    public void setData(List<Flavor> recipe) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < recipe.size(); i++) {
            entries.add(new PieEntry(recipe.get(i).getPercentage(), recipe.get(i).getTitle()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.getAllColors());

        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(tf);
        notifyGraph(data);
    }

    @Override
    public void showBottomSheet() {
        if (recipe.size() > 0) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            bottomSheetBehavior.setHideable(false);
        }
        fabSheetView.notifyFabUpdate();
    }

    @Override
    public void hideBottomSheet() {
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        fabSheetView.notifyFabUpdate();
    }

    private void initGraph(){
        Log.d(TAG, "initGraph: called");

        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

        mChart.setCenterTextTypeface(Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf"));
        mChart.setCenterText("Something");

        mChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.BLACK);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
    }

}

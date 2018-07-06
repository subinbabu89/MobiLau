package telvape.mobilau.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import telvape.mobilau.R;
import telvape.mobilau.custom.ColorTemplate;
import telvape.mobilau.model.Flavor;
import telvape.mobilau.model.Juice;
import telvape.mobilau.presenter.JuiceAddPresenter;
import telvape.mobilau.presenter.JuiceAddPresenterImpl;

public class ReviewFragment extends BaseFragment {

    private static final String TAG = "ReviewFragment";

    @BindView(R.id.summary_chart)
    PieChart summaryChart;

    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.flavor_name)
    TextInputEditText flavorName;

    @BindView(R.id.summary_ingredients)
    RecyclerView summaryIngredients;
    private Typeface tf;

    private Juice juice;

    @Override
    public int initLayoutID() {
        return R.layout.fragment_review;
    }

    @Override
    public void initViews(View parent) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Flavor> juice = getArguments().getParcelableArrayList("juice");
        setChart(juice);
        for (Flavor flavor: juice
                ) {
            stringBuilder.append(flavor.getTitle());
            stringBuilder.append(",");
        }
        String s = stringBuilder.toString();
        this.juice = new Juice("","","","","","",s.substring(0,s.lastIndexOf(",")));
    }

    @Override
    public void customizeFragment() {
        initGraph();
    }

    @OnClick(R.id.submit)
    void submit() {
        juice.setName(flavorName.getEditableText().toString());
        JuiceAddPresenter juicesPresenter = new JuiceAddPresenterImpl();
        juicesPresenter.putJuice(juice);
    }

    void setChart(List<Flavor> recipe) {
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
        summaryChart.setData(data);
        // undo all highlights
        summaryChart.highlightValues(null);

        summaryChart.invalidate();
    }

    private void initGraph() {
        Log.d(TAG, "initGraph: called");

        summaryChart.setUsePercentValues(true);
        summaryChart.getDescription().setEnabled(false);
        summaryChart.setExtraOffsets(5, 10, 5, 5);

        summaryChart.setDragDecelerationFrictionCoef(0.95f);

        tf = Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Regular.ttf");

        summaryChart.setCenterTextTypeface(Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf"));
        summaryChart.setCenterText("Something");

        summaryChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        summaryChart.setDrawHoleEnabled(true);
        summaryChart.setHoleColor(Color.WHITE);

        summaryChart.setTransparentCircleColor(Color.BLACK);
        summaryChart.setTransparentCircleAlpha(110);

        summaryChart.setHoleRadius(58f);
        summaryChart.setTransparentCircleRadius(61f);

        summaryChart.setDrawCenterText(true);

        summaryChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        summaryChart.setRotationEnabled(true);
        summaryChart.setHighlightPerTapEnabled(true);
    }
}
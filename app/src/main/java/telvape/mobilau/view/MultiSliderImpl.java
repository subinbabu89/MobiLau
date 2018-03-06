package telvape.mobilau.view;

import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import java.util.List;

import io.apptik.widget.MultiSlider;
import telvape.mobilau.R;
import telvape.mobilau.custom.ColorTemplate;
import telvape.mobilau.model.Flavor;

/**
 * Created by sbabu on 3/2/18.
 *
 */

public class MultiSliderImpl implements MultiSliderView {
    private static final String TAG = "MultiSliderImpl";

    private MultiSlider multiSlider;
    private List<Flavor> recipe;

    private BottomSheetView bottomSheetView;

    MultiSliderImpl(MainView mainView, BottomSheetView bottomSheetView, List<Flavor> recipe) {
        multiSlider = ((MainActivity)mainView).findViewById(R.id.multiSlider);
        this.recipe = recipe;

        this.bottomSheetView = bottomSheetView;
    }

    @Override
    public void notifySlider() {
        int totalThumbs = recipe.size();
        multiSlider.setOnThumbValueChangeListener(null);
        multiSlider.clearThumbs();
        multiSlider.setNumberOfThumbs(totalThumbs);

        List<Integer> allColors = ColorTemplate.getAllColors();

        multiSlider.addThumbOnPos(0, 0);
        multiSlider.getThumb(0).setRange(new ColorDrawable(ColorTemplate.COLOR_NONE));

        int total = 0;
        for (int i = 1; i <= recipe.size(); i++) {
            multiSlider.getThumb(i).setRange(new ColorDrawable(allColors.get(i-1)));
            int percentage = recipe.get(i - 1).getPercentage();
            total+=percentage;
            multiSlider.getThumb(i).setValue(total);
        }
        multiSlider.getThumb(0).setMax(0);
        multiSlider.getThumb(totalThumbs).setMin(100);

        multiSlider.setOnThumbValueChangeListener(new MultiSlider.SimpleChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                Log.d(TAG, "onValueChanged: called");
                int previousThumbValue , currentThumbValue, nextThumbValue ;
                if (thumbIndex != 0 && thumbIndex < recipe.size()) {
                    previousThumbValue = multiSlider.getThumb(thumbIndex - 1).getValue();
                    currentThumbValue = thumb.getValue();
                    nextThumbValue = multiSlider.getThumb(thumbIndex+1).getValue();
                    recipe.get(thumbIndex - 1).setPercentage(currentThumbValue - previousThumbValue);
                    recipe.get(thumbIndex ).setPercentage(nextThumbValue - currentThumbValue);
                    bottomSheetView.setData(recipe);
                }
            }
        });
        bottomSheetView.setData(recipe);
    }
}

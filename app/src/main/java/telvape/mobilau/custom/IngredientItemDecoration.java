package telvape.mobilau.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 * Created by sbabu on 3/1/18.
 */

public class IngredientItemDecoration extends RecyclerView.ItemDecoration{
    private int space;

    public IngredientItemDecoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = space;
        outRect.right = space;

        outRect.bottom = space;

            outRect.top = space;
    }
}

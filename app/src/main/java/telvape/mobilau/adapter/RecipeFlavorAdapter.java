package telvape.mobilau.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import telvape.mobilau.R;
import telvape.mobilau.model.Flavor;

/**
 *
 * Created by sbabu on 3/1/18.
 */

public class RecipeFlavorAdapter extends RecyclerView.Adapter<RecipeFlavorAdapter.ViewHolder>{

    private List<Flavor> flavors;

    public RecipeFlavorAdapter(List<Flavor> recipeIngredients) {
        this.flavors = recipeIngredients;
    }

    @Override
    public RecipeFlavorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_ingredient,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeFlavorAdapter.ViewHolder holder, int position) {
        holder.bind(flavors.get(position));
    }

    @Override
    public int getItemCount() {
        return flavors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtv_ingredient_name)
        TextView ingredientName;

        @BindView(R.id.txtv_percentage)
        TextView ingredientPercentage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Flavor flavor){
            ingredientName.setText(flavor.getName());
            ingredientPercentage.setText(String.valueOf(flavor.getPercentage()));
        }
    }
}

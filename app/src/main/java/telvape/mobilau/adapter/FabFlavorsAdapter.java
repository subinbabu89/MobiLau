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

public class FabFlavorsAdapter extends RecyclerView.Adapter<FabFlavorsAdapter.ViewHolder> {
    private List<Flavor> ingredients;

    public FabFlavorsAdapter(List<Flavor> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flavor,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtv_label)
        TextView textView;

        Flavor ingredient;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(Flavor ingredient){
            this.ingredient = ingredient;
            textView.setText(ingredient.getName());

        }
    }
}

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
import telvape.mobilau.view.juice.custom.CustomJuiceView;

/**
 * 
 * Created by sbabu on 3/1/18.
 */

public class AllFlavorAdapter extends RecyclerView.Adapter<AllFlavorAdapter.ViewHolder> {

    private List<Flavor> flavors;
    private CustomJuiceView customJuiceView;

    public AllFlavorAdapter(CustomJuiceView customJuiceView, List<Flavor> flavors) {
        this.customJuiceView = customJuiceView;
        this.flavors = flavors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flavor,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(flavors.get(position));
    }

    @Override
    public int getItemCount() {
        return flavors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.txtv_label)
        TextView txtvFlavorName;

        Flavor flavor;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Flavor flavor){
            txtvFlavorName.setText(flavor.getTitle());
            this.flavor = flavor;
        }

        @Override
        public void onClick(View view) {
            customJuiceView.addFlavor(flavor);
        }
    }
}

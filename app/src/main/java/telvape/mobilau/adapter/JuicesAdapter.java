package telvape.mobilau.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import telvape.mobilau.R;
import telvape.mobilau.model.Juice;
import telvape.mobilau.view.juice.JuicesView;

/**
 *
 * Created by sbabu on 3/21/18.
 */

public class JuicesAdapter extends RecyclerView.Adapter<JuicesAdapter.ViewHolder> {
    private static final String TAG = "JuicesAdapter";

    private List<Juice> juices;
    private JuicesView juicesView;

    private Context context;

    public JuicesAdapter(JuicesView juicesView, List<Juice> juices) {
        this.juicesView = juicesView;
        this.juices = juices;
    }

    @Override
    public JuicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_juice,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JuicesAdapter.ViewHolder holder, int position) {
        holder.bind(juices.get(position));
    }

    @Override
    public int getItemCount() {
        return juices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.txtv_label)
        TextView txtv_label;
        @BindView(R.id.ll_flavors)
        LinearLayout flavors;

        Juice juice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Juice juice){
            this.juice = juice;
            Log.d(TAG, "bind: Name "+juice.getName());
            txtv_label.setText(juice.getName());
            int flavorCount = juice.getFlavors().size();
            Log.d(TAG, "bind: value is " + (flavorCount<3?flavorCount:3));
            flavors.removeAllViews();
            for (int i = 0; i < (flavorCount<3?flavorCount:3); i++) {
                ImageView flavorImage = new ImageView(context);
                Drawable d;
                switch (juice.getFlavors().get(i)){
                    case "Sweet":
                        d = context.getDrawable(R.drawable.ic_flavor_sweet);
                        break;
                     case "Creamy":
                         d = context.getDrawable(R.drawable.ic_flavor_creamy);
                         break;
                     case "Fruity":
                         d = context.getDrawable(R.drawable.ic_flavor_fruity);
                         break;
                     case "Rich":
                         d = context.getDrawable(R.drawable.ic_flavor_rich);
                         break;
                     case "Spiced":
                         d = context.getDrawable(R.drawable.ic_flavor_spicy);
                         break;
                     case "Tobacco":
                         d = context.getDrawable(R.drawable.ic_flavor_tobacco);
                         break;
                     case "Cool":
                         d = context.getDrawable(R.drawable.ic_flavor_cool);
                         break;
                     case "Nutty":
                         d = context.getDrawable(R.drawable.ic_flavor_nutty);
                         break;
                     case "Coffee":
                         d = context.getDrawable(R.drawable.ic_flavor_coffee);
                         break;
                        default:
                            d = context.getDrawable(R.drawable.ic_flavor_all);
                            break;
                }
                flavorImage.setImageDrawable(d);
                flavors.addView(flavorImage);
            }
        }

        @Override
        public void onClick(View view) {
            juicesView.navigateToDetail(juice);
        }
    }
}

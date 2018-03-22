package telvape.mobilau.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import telvape.mobilau.R;
import telvape.mobilau.model.Juice;
import telvape.mobilau.view.JuicesView;

/**
 *
 * Created by sbabu on 3/21/18.
 */

public class JuicesAdapter extends RecyclerView.Adapter<JuicesAdapter.ViewHolder> {
    private static final String TAG = "JuicesAdapter";

    private List<Juice> juices;
    private JuicesView juicesView;

    public JuicesAdapter(JuicesView juicesView, List<Juice> juices) {
        this.juicesView = juicesView;
        this.juices = juices;
    }

    @Override
    public JuicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flavor,parent,false);
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

        Juice juice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Juice juice){
            this.juice = juice;
            txtv_label.setText(juice.getName());
        }

        @Override
        public void onClick(View view) {
            juicesView.navigateToDetail(juice);
        }
    }
}

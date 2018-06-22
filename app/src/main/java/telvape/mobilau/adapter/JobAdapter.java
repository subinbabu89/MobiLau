package telvape.mobilau.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v13.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import telvape.mobilau.R;
import telvape.mobilau.model.Job;
import telvape.mobilau.view.LandingActivity;
import telvape.mobilau.view.SecondActivity;

/**
 * Adapter for the list of all running jobs to display
 *
 * Created by sbabu on 2/27/18.
 */

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder>{

    private Context context;
    private List<Job> jobs;

    public JobAdapter(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context =parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(jobs.get(position));
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtv_job_id)
        TextView id;
        @BindView(R.id.txtv_job_name)
        TextView name;
        @BindView(R.id.txtv_job_start_by)
        TextView startedBy;
        @BindView(R.id.txtv_job_complete_at)
        TextView completedAt;

        Job holderJob;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.cv_parent)
        void animateTransition(CardView card_view){
            Intent intent = new Intent(context, SecondActivity.class);
            intent.putExtra("key",holderJob);

            String transitionName = context.getResources().getString(R.string.transition_string);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation((LandingActivity)context,
                            card_view,   // Starting view
                            transitionName    // The String
                    );
            ActivityCompat.startActivity(context, intent, options.toBundle());
        }

        void bind(Job job){
            holderJob = job;

            id.setText(String.valueOf(job.getId()));
            name.setText(job.getJob_name());
            startedBy.setText(job.getStarted_by());
            completedAt.setText(String.valueOf(job.getTime_to_complete()));
        }
    }
}

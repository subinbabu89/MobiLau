package telvape.mobilau.view;

import android.widget.TextView;

import butterknife.BindView;
import telvape.mobilau.BaseActivity;
import telvape.mobilau.R;
import telvape.mobilau.model.Job;

public class SecondActivity extends BaseActivity {

    @BindView(R.id.txtv_job_name)
    TextView jobName;

    @Override
    public int getContentLayout() {
        return R.layout.activity_second;
    }

    @Override
    public void initViewComponents() {
        Job job = getIntent().getParcelableExtra("key");
        jobName.setText(job.getJob_name());
    }
}

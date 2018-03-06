package telvape.mobilau.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Created by sbabu on 2/27/18.
 */

public class Job implements Parcelable{
    private int id;
    private String job_name;
    private String started_by;
    private int time_to_complete;

    private Job(Parcel in) {
        id = in.readInt();
        job_name = in.readString();
        started_by = in.readString();
        time_to_complete = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(job_name);
        dest.writeString(started_by);
        dest.writeInt(time_to_complete);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Job> CREATOR = new Parcelable.Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getJob_name() {
        return job_name;
    }

    public String getStarted_by() {
        return started_by;
    }

    public int getTime_to_complete() {
        return time_to_complete;
    }


}

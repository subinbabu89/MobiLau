package telvape.mobilau.network;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import telvape.mobilau.model.Jobs;

/**
 * Created by sbabu on 2/27/18.
 */

public interface JobFetchInterface {

    @GET("job")
    Call<Jobs> getJobs();

    class JobFetchApi{
        private static JobFetchInterface jobFetchInterface;

        public static JobFetchInterface jobFetchApi() {
            if(jobFetchInterface ==null){
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://my-json-server.typicode.com/subinbabu89/dummy-rest-api/").addConverterFactory(GsonConverterFactory.create(new Gson())).build();
                jobFetchInterface = retrofit.create(JobFetchInterface.class);
            }
            return jobFetchInterface;
        }
    }
}

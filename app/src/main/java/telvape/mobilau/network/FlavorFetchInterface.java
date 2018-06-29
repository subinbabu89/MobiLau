package telvape.mobilau.network;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import telvape.mobilau.model.Flavor;

/**
 *
 * Created by sbabu on 3/6/18.
 */

public interface FlavorFetchInterface {

    @GET("flavorjson")
    Call<List<Flavor>> getFlavors();

    class FlavorFetchAPI{
        private static FlavorFetchInterface flavorFetchInterface;

        public static FlavorFetchInterface flavorFetchAPI(){

            if(flavorFetchInterface==null){
//                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://my-json-server.typicode.com/subinbabu89/dummy-rest-api/").addConverterFactory(GsonConverterFactory.create(new Gson())).build();
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://vape-r-ware.us-west-1.elasticbeanstalk.com/").addConverterFactory(GsonConverterFactory.create(new Gson())).build();
                flavorFetchInterface = retrofit.create(FlavorFetchInterface.class);
            }
            return flavorFetchInterface;
        }
    }
}

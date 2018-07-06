package telvape.mobilau.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import telvape.mobilau.model.Juice;
import telvape.mobilau.model.JuiceResponse;

/**
 *
 * Created by sbabu on 3/21/18.
 */

public interface JuiceFetchInterface {

//    @GET("12pckb")
    @GET("juicejson")
    Call<List<Juice>> getJuices();

    @POST("putJuice")
    Call<String> putJuice(@Body Juice juice);

    class JuicesFetchAPI{
        private static JuiceFetchInterface juiceFetchInterface;

        public static JuiceFetchInterface api(){
            if (juiceFetchInterface == null) {
//                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.myjson.com/bins/").addConverterFactory(GsonConverterFactory.create(new Gson())).build();
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://vape-r-ware.us-west-1.elasticbeanstalk.com/").addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).build();
                juiceFetchInterface = retrofit.create(JuiceFetchInterface.class);
            }
            return juiceFetchInterface;
        }
    }

}

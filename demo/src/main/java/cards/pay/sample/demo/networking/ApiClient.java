package cards.pay.sample.demo.networking;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class ApiClient{
    private static Retrofit retrofit = null;

    static APIInterface getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(APIInterface.class);
    }

    interface APIInterface {

        @GET("/api/unknown")
        Call<ResponseBody> doGetListResources();

        @POST
        Call<String> createUser(@Url String user);

        @GET("/api/users?")
        Call<ResponseBody> doGetUserList(@Query("page") String page);

        @FormUrlEncoded
        @POST("/api/users?")
        Call<ResponseBody> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
    }

}

package im.jersuen.com.retrofitdemo.newUtil.service;


import im.jersuen.com.retrofitdemo.newUtil.bean.ResultBean;
import im.jersuen.com.retrofitdemo.newUtil.bean.UserBean;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by silver on 16-6-7.
 * blog:http://blog.csdn.net/vfush
 */
public interface MyTestApiService {

    /**
     * sayHello 动态get 参数
     *
     * @param username
     * @param age
     * @return
     */
    @GET("test/sayHello")
    Call<String> sayHello(@Query("username") String username, @Query("age") String age);

    @POST("test/sayHello")
    Call<ResultBean> postSayHello(@Query("username") String username, @Query("age") String age);

    @POST
    Call<ResultBean> postSayHelloByURL(@Url String url, @Query("username") String username, @Query("age") String age);

    @FormUrlEncoded
    @POST("test/sayHello")
    Call<ResultBean> postSayHelloByForm(@Field("username") String username, @Field("age") String age);

    @POST("test/sayHi")
    @Headers("Accept-Encoding: application/json")
        //使用@Headers 可添加header
    Call<ResultBean> postSayHi(@Body UserBean userBean);

    @POST("test/sayHi")
    @Headers("Accept-Encoding: application/json")
        //也可以使用@Header 可添加header
    Call<ResultBean> postSayHi(@Body UserBean userBean, @Header("city") String city);

    @Multipart
    @POST("test/upload")
    Call<ResultBean> upload(@Part("file\"; filename=\"launcher_icon.png") RequestBody file);

    //http://localhost:8080/app/res/atom-amd64.deb
    @Streaming
    @GET
    Call<ResponseBody> downloadFileByDynamicUrlAsync(@Url String downloadUrl);


}

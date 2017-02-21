package im.jersuen.com.retrofitdemo.newUtil.service;


import im.jersuen.com.retrofitdemo.newUtil.bean.StartImageBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by silver on 16-6-7.
 * blog:http://blog.csdn.net/vfush
 */
public interface ZhihuService {

    @GET("news/latest")
    void getNewsLatest();

    @GET("start-image/1080*1776")
    Call<StartImageBean> getStartImage();

    @GET("start-image/{size}")
    Call<StartImageBean> getStartImageByPath(@Path("size") String size);
}

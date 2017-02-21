package im.jersuen.com.retrofitdemo.newUtil.service;


import im.jersuen.com.retrofitdemo.newUtil.bean.UserBean;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by silver on 16-6-7.
 * blog:http://blog.csdn.net/vfush
 */
public interface DouBanService {

    @POST("fm/lyric")
    Call<UserBean> postLyric(@Query("sid") int sid, @Query("ssid") String ssid);
}

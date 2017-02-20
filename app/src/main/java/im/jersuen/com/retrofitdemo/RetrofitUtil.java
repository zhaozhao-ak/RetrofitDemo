package im.jersuen.com.retrofitdemo;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/2/20.
 */

public class RetrofitUtil {

    /**
     * 声明请求的接口
     */
    public static NetWorkService netWorkService;

    /**
     * 网络请求框架
     */
    public static Retrofit retrofit;
//    String path = "http://192.168.1.102:8080/Retrofit/";
//
//    public RetrofitUtil (String url){
//        grtRetrofit(url);
//    }

    public static NetWorkService grtRetrofit(String url) {
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            //让框架自动实现我们的请求接口,让我们的请求接口可以被调用
            netWorkService = retrofit.create(NetWorkService.class);
            return netWorkService;
        }
        return netWorkService;
    }

}

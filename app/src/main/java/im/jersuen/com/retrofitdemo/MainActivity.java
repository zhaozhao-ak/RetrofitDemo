package im.jersuen.com.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void getTest(View view) {

        String url = "http://192.168.1.102:8080/Retrofit/";
        Call<String> call = RetrofitUtil.grtRetrofit(url).get();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                Toast.makeText(MainActivity.this, "get成功", Toast.LENGTH_SHORT).show();
                tv.setText(result);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "get失败", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void postTest(View view) {

        String url = "http://192.168.1.102:8080/Retrofit/";
        Call<String> call = RetrofitUtil.grtRetrofit(url).register("cxj", "123");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //拿到返回的json数据
                String result = response.body();
                //打印结果
                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                tv.setText(result);
                System.out.println("zhao---post--result = " + result);
                //利用Gson转化json为实体对象
                Gson gson = new GsonBuilder().create();
                //传化后的实体对象
                Msg msg = gson.fromJson(result, Msg.class);
                //提示实体对象中的信息
//                Toast.makeText(NewMainActivity.this, msg.getMsgText(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(NewMainActivity.this, "post失败", Toast.LENGTH_SHORT).show();
                System.out.println("zhao---post-失败-result = ");

            }
        });
    }
//
//    public void postFile(View v) {
//        //需要上传的文件
//        File f = new File(Environment.getExternalStorageDirectory(), "address.db");
//
//        //创建文件部分的请求体对象
//        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), f);
//
//        //普通键值对的请求体
//        RequestBody nameBody = RequestBody.create(null,"小金子");
//        RequestBody passBody = RequestBody.create(null,"123");
//
//        String url = "http://192.168.1.102:8080/Retrofit/";
//        Call<String> call = new RetrofitUtil(url).netWorkService.postFile(fileBody, nameBody, passBody);
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//
//                System.out.println("zhao----上传成功" + response.body() + response.code() + response.errorBody());
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                System.out.println("zhao---上传文件失败");
//            }
//        });
//
//    }

}

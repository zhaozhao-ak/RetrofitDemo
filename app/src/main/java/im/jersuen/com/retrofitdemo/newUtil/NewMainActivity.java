package im.jersuen.com.retrofitdemo.newUtil;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import im.jersuen.com.retrofitdemo.R;
import im.jersuen.com.retrofitdemo.newUtil.bean.ResultBean;
import im.jersuen.com.retrofitdemo.newUtil.bean.StartImageBean;
import im.jersuen.com.retrofitdemo.newUtil.bean.UserBean;
import im.jersuen.com.retrofitdemo.newUtil.service.MyTestApiService;
import im.jersuen.com.retrofitdemo.newUtil.service.ZhihuService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by silver on 16-6-7.
 * blog:http://blog.csdn.net/vfush
 */
public class NewMainActivity extends Activity implements AdapterView.OnItemClickListener {


    private ListView actionListView;
    private TextView resultTextView;
    public static final String TAG = "NewMainActivity";
    public static final String API_BASE_URL = "http://139.129.46.115:8080/app/";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        actionListView = (ListView) findViewById(R.id.lv_action);
        resultTextView = (TextView) findViewById(R.id.tv_result);

        actionListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        actionListView.setOnItemClickListener(this);

        copyFile();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /***
     * 最初的开始
     */
    private void getStartImage() {
        Retrofit retrofit = new Retrofit.Builder()
                //设置baseUrl,注意baseUrl 应该以/ 结尾。
                .baseUrl("http://news-at.zhihu.com/api/4/")
                //使用Gson解析器,可以替换其他的解析器
                .addConverterFactory(GsonConverterFactory.create())
                //设置OKHttpClient,如果不设置会提供一个默认的
                //.client(new OkHttpClient())
                .build();

        ZhihuService messageService = retrofit.create(ZhihuService.class);
        Call<StartImageBean> startImage = messageService.getStartImage();

        startImage.enqueue(new Callback<StartImageBean>() {

            @Override
            public void onResponse(Call<StartImageBean> call, Response<StartImageBean> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                    resultTextView.setText("" + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<StartImageBean> call, Throwable t) {
                resultTextView.setText("" + "error:" + t.getMessage());
            }
        });

    }

    private void getStartImageByPath() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .addConverterFactory(GsonConverterFactory.create())
                //设置OKHttpClient,如果不设置会提供一个默认的
                //.client(new OkHttpClient())
                .build();
        ZhihuService messageService = retrofit.create(ZhihuService.class);

        /**
         * 可使用以下格式做动态URL参数
         * 320*432
         480*728
         720*1184
         1080*1776
         */
        Call<StartImageBean> startImage = messageService.getStartImageByPath("320*432");
        //异步请求
        startImage.enqueue(new Callback<StartImageBean>() {

            @Override
            public void onResponse(Call<StartImageBean> call, Response<StartImageBean> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                    resultTextView.setText("" + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<StartImageBean> call, Throwable t) {
                resultTextView.setText("" + "error:" + t.getMessage());
            }
        });

        //同步
//        try {
//            Response<StartImageBean> execute = startImage.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    private void postSayHello() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //设置OKHttpClient,如果不设置会提供一个默认的
                //.client(new OkHttpClient())
                .build();
        MyTestApiService myTestApiService = retrofit.create(MyTestApiService.class);
//        Call<ResultBean> doubanCall = myTestApiService.postSayHello("fuchenxuan", "110");
//        Call<ResultBean> doubanCall = myTestApiService.postSayHelloByForm("fuchenxuan", "110");

        Call<ResultBean> doubanCall = myTestApiService.postSayHelloByURL(API_BASE_URL.concat("test/sayHello"), "fuchenxuan", "110");
        doubanCall.enqueue(new Callback<ResultBean>() {
            @Override
            public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                    resultTextView.setText("" + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<ResultBean> call, Throwable t) {

            }
        });
    }

    private void getSayHello() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                //在build.gradle添加
                // compile 'com.squareup.retrofit2:converter-scalars:2.1.0+'
                .addConverterFactory(ScalarsConverterFactory.create())
                //设置OKHttpClient,如果不设置会提供一个默认的
                //.client(new OkHttpClient())
                .build();
        MyTestApiService myTestApiService = retrofit.create(MyTestApiService.class);
        Call<String> doubanCall = myTestApiService.sayHello("fuchenxuan", "110");
        doubanCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                    resultTextView.setText("" + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    private void postSayHi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                //检测接口定义是否正确,适合在开发、测试时使用
                .validateEagerly(true)
                .addConverterFactory(GsonConverterFactory.create())
                //设置OKHttpClient,如果不设置会提供一个默认的
                //.client(new OkHttpClient())
                //使用RxJava
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        MyTestApiService myTestApiService = retrofit.create(MyTestApiService.class);
        UserBean userBean = new UserBean("fuchenxuan", "1110");

        Call<ResultBean> doubanCall = myTestApiService.postSayHi(userBean);

        doubanCall.enqueue(new Callback<ResultBean>() {
            @Override
            public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {

                if (response.isSuccessful()) {
                    //返回原始数据格式
                    try {
                        Log.d(TAG, response.raw().body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    resultTextView.setText("" + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<ResultBean> call, Throwable t) {

            }
        });
    }


    private void upload() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //设置OKHttpClient,如果不设置会提供一个默认的
                //.client(new OkHttpClient())
                .build();

        MyTestApiService myTestApiService = retrofit.create(MyTestApiService.class);

        File file = new File(getExternalFilesDir(null), "launcher_icon.png");
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);
        Call<ResultBean> doubanCall = myTestApiService.upload(fileBody);

        doubanCall.enqueue(new Callback<ResultBean>() {
            @Override
            public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, response.body().toString());
                    resultTextView.setText("" + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<ResultBean> call, Throwable t) {
//                Log.d(TAG, response.body().toString());
                resultTextView.setText("" + "error:" + t.getMessage());
            }
        });
    }


    private void downloadFileByDynamicUrlAsync() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //设置OKHttpClient,如果不设置会提供一个默认的
                //.client(new OkHttpClient())
                .build();


        final MyTestApiService myTestApiService = retrofit.create(MyTestApiService.class);

        new AsyncTask<Void, Long, Void>() {


            @Override
            protected Void doInBackground(Void... voids) {
                Call<ResponseBody> call = myTestApiService.downloadFileByDynamicUrlAsync(API_BASE_URL.concat("/res/atom-amd64.deb"));


                try {
                    Response<ResponseBody> response = call.execute();
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());
                    Log.d(TAG, "下载文件 " + writtenToDisk);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        }.execute();


    }

    //写入到磁盘
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            File futureStudioIconFile = new File(Environment.getExternalStorageDirectory() + File.separator + "atom.deb");
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                final long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);
                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);

                    final long finalFileSizeDownloaded = fileSizeDownloaded;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            resultTextView.setText("file download: " + finalFileSizeDownloaded + " of " + fileSize);
                        }
                    });
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


    private void copyFile() {
        InputStream is = null;
        try {
            is = getAssets().open("launcher_icon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(getExternalFilesDir(null), "launcher_icon.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        int byteCount = 0;
        try {
            while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
            }

            fos.flush();
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("普通 GET 返回实体对象");
        data.add("动态URL GET 返回实体对象");
        data.add("GET 动态参数 返回String");
        data.add("普通 POST 返回实体对象");
        data.add("POST JSON 参数 ");
        data.add("文件上传 ");
        data.add("文件下载 ");
        return data;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://普通 GET 无参数 和case 2一样只是没有参数
                getStartImage();
                break;
            case 1://动态URL GET restFul
                getStartImageByPath();
                break;
            case 2://GET 动态参数 返回Json String
                getSayHello();
                break;
            case 3://普通POST
                postSayHello();
                break;
            case 4://POST JSON 参数
                postSayHi();
                break;
            case 5://文件上传
                upload();
                break;
            case 6://大文件下载
                downloadFileByDynamicUrlAsync();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}

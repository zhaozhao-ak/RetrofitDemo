package im.jersuen.com.retrofitdemo.newUtil.bean;

import java.io.Serializable;

/**
 * Created by silver on 16-6-20.
 */
public class ResultBean implements Serializable {

    private int code;//请求成功200
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", message=" + message + ", data=" + data + "]";
    }
}

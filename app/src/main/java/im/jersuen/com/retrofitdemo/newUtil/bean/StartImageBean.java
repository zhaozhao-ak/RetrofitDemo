package im.jersuen.com.retrofitdemo.newUtil.bean;

import java.io.Serializable;

/**
 * Created by silver on 16-6-7.
 * blog:http://blog.csdn.net/vfush
 */
public class StartImageBean implements Serializable {
    public String text;
    public String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "StartImageBean{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

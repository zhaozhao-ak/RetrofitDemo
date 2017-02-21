package im.jersuen.com.retrofitdemo.newUtil.bean;

/**
 * Created by silver on 16-6-7.
 * blog:http://blog.csdn.net/vfush
 */
public class UserBean {

    public String username;
    public String age;

    public UserBean(String username, String age) {
        this.username = username;
        this.age = age;
    }

    public UserBean() {
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getSid() {
        return age;
    }

    public void setSid(String sid) {
        this.age = sid;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                ", username='" + username + '\'' +
                ", sid='" + age + '\'' +
                '}';
    }
}

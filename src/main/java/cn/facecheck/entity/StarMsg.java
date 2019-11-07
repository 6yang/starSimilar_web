package cn.facecheck.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/***
 * @ClassName: StarMsg
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/620:17
 * @version : V1.0
 */
public class StarMsg {

    public String status;

    @SerializedName("data")
    public List<Data> data = new ArrayList<>();

    public class Data{

        public String display_title;

        @SerializedName("result")
        public List<Actor> actor = new ArrayList<>();

        public class Actor{

            public String ename;

            public String pic_4n_78;

            @Override
            public String toString() {
                return "Actor{" +
                        "ename='" + ename + '\'' +
                        ", pic_4n_78='" + pic_4n_78 + '\'' +
                        '}';
            }

        }

        @Override
        public String toString() {
            return "Data{" +
                    "display_title='" + display_title + '\'' +
                    ", actor=" + actor +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "StarMsg{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}

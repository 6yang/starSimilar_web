package cn.facecheck.entity;

import java.util.List;

/***
 * @ClassName: Msg
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/417:01
 * @version : V1.0
 */
public class Msg {

    private String error_msg;

    private String result;

    private class result{

        public String face_token;

//        public List<user_list> user_list;

//        public class user_list{
//
//            public String group_id;
//
//            public String user_id;
//
//            public String user_info;
//
//            public String score;
//
//            @Override
//            public String toString() {
//                return "user_list{" +
//                        "group_id='" + group_id + '\'' +
//                        ", user_id='" + user_id + '\'' +
//                        ", user_info='" + user_info + '\'' +
//                        ", score='" + score + '\'' +
//                        '}';
//            }
//        }

        @Override
        public String toString() {
            return "result{" +
                    "face_token='" + face_token + '\'' +
                    ", user_list=" + " " +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Msg{" +
                "error_msg='" + error_msg + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

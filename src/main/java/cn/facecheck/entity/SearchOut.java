package cn.facecheck.entity;

/***
 * @ClassName: SearchOut
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/417:40
 * @version : V1.0
 */
public class SearchOut {

    private String success_tag;

    private String face_token;

    private String group_id;

    private String user_id;

    private String user_info;

    private String score;

    private String img_url;

    public String getSuccess_tag() {
        return success_tag;
    }

    public void setSuccess_tag(String success_tag) {
        this.success_tag = success_tag;
    }

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}

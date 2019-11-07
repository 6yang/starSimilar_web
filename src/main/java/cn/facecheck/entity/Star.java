package cn.facecheck.entity;

import com.google.gson.annotations.SerializedName;

/***
 * @ClassName: Star
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/48:29
 * @version : V1.0
 */
public class Star {
    private Integer id;

    private String groupId;

    private String starInfo;

    private String face_taken;

    private String img_url;

    private String starId;

    private String group_name;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStarId() {
        return starId;
    }

    public void setStarId(String starId) {
        this.starId = starId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStarInfo() {
        return starInfo;
    }

    public void setStarInfo(String starInfo) {
        this.starInfo = starInfo;
    }

    public String getFace_taken() {
        return face_taken;
    }

    public void setFace_taken(String face_taken) {
        this.face_taken = face_taken;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "Star{" +
                "id=" + id +
                ", groupId='" + groupId + '\'' +
                ", starInfo='" + starInfo + '\'' +
                ", face_taken='" + face_taken + '\'' +
                ", img_url='" + img_url + '\'' +
                ", starId='" + starId + '\'' +
                ", group_name='" + group_name + '\'' +
                '}';
    }
}

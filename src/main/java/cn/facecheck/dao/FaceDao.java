package cn.facecheck.dao;


import cn.facecheck.entity.Star;
import org.apache.ibatis.annotations.Insert;

public interface FaceDao {

    @Insert("insert into face(group_id,star_info,face_taken,img_url,star_id,group_name)  " +
            " values(#{groupId},#{starInfo},#{face_taken},#{img_url},#{starId},#{groupName}) ")
    void addFace(Star star);
}

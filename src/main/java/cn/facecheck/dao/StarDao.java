package cn.facecheck.dao;


import cn.facecheck.entity.Star;
import org.apache.ibatis.annotations.*;

public interface StarDao {

    @Select("select * from star where id = #{starId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "starInfo", column = "star_info"),
            @Result(property = "face_taken", column = "face_taken"),
            @Result(property = "img_url", column = "img_url"),
            @Result(property = "starId", column = "star_id")
    })
    Star findById(int starId);

    @Insert("insert into star(group_id,star_info,face_taken,img_url,star_id) " +
            " values(#{groupId},#{starInfo},#{face_token},#{imgHttpUrl},#{uuid})")
    void insertStarFace(@Param("groupId") String groupId, @Param("starInfo") String starInfo, @Param("face_token") String face_token, @Param("imgHttpUrl") String imgHttpUrl,@Param("uuid") String uuid);


    @Select("select * from face where star_id = #{star_id}")
    Star findStarImgUrl(String star_id);
}

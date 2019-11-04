package cn.facecheck.service;

import cn.facecheck.entity.Star;

public interface StarService {

    Star findById(int starId);

    void insertStarFace(String groupId, String starInfo, String face_token, String imgHttpUrl, String uuid);
}

package cn.facecheck.service.impl;

import cn.facecheck.dao.StarDao;
import cn.facecheck.entity.Star;
import cn.facecheck.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @ClassName: StarServiceImpl
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/49:25
 * @version : V1.0
 */
@Service
public class StarServiceImpl implements StarService {

    @Autowired
    public StarDao starDao;

    @Override
    public Star findById(int starId) {
        return starDao.findById(starId);
    }

    @Override
    public void insertStarFace(String groupId, String starInfo, String face_token, String imgHttpUrl, String uuid) {
        starDao.insertStarFace(groupId,starInfo,face_token,imgHttpUrl,uuid);
    }

    @Override
    public Star findStarImgUrl(String star_id) {
        return starDao.findStarImgUrl(star_id);
    }


}

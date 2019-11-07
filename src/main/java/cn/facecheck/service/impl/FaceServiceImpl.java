package cn.facecheck.service.impl;

import cn.facecheck.dao.FaceDao;
import cn.facecheck.entity.Star;
import cn.facecheck.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @ClassName: FaceServiceImpl
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/79:32
 * @version : V1.0
 */
@Service
public class FaceServiceImpl implements FaceService {

    @Autowired
    private FaceDao faceDao;

    @Override
    public void addFace(Star star) {
        faceDao.addFace(star);
    }
}

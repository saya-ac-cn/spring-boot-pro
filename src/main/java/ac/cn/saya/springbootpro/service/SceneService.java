package ac.cn.saya.springbootpro.service;

import ac.cn.saya.springbootpro.entity.SceneRecordEntity;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Title: SceneService
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 9/25/21 21:40
 * @Description:
 */

public interface SceneService {

    public List<SceneRecordEntity> queryScenesList(Integer projectId);

    public JSONObject showScene(Long uuid, boolean flag);

}

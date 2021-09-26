package ac.cn.saya.springbootpro.service;


import lombok.Getter;

/**
 * 场景类型
 *
 * @author lnk
 * @version 0.0.1
 * @date 2021/8/17 9:19
 */
@Getter
public enum SceneTypeEnum {

    BUILDING("buildings"),
    OUTDOOR("outdoors"),
    PLAN("plans"),
    // 在处理以下的数据时，如果userid为空，直接不予处理
    ROOM("rooms"),
    PLACEMENT("placements"),
    PIPE_LINES("pipelines"),
    ROUTE_LINES("routelines"),
    LEAK_WATER_LINES("leakwaterlines"),
    CURVE_LINES("curvelines"),
    ARROW_LINES("arrowlines"),
    ARROW_DATA_LINES("arrowdatalines");


    private final String val;


    SceneTypeEnum(String val) {
        this.val = val;
    }

}

package ac.cn.saya.springbootpro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * @Title: NewsEntity
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-15 22:01
 * @Description:
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
/// 实体类的类名和数据库表名不一致
@TableName(value = "news")
@Alias("news")
public class NewsEntity {

    /**
     * 编号
     * @@TableId : 实体类的主键名称和表中主键名称不一致
     *      value:指定表中的主键列的列名，如果字段和实体名一致，则可以忽略，采用驼峰命名
     *      type：指定主键策略
     */
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;

    /**
     * 主题
     * 实体类中的成员名称和表中字段名称不一致
     */
    @TableField(value = "topic")
    private String topic;

    /**
     * 标签
     */
    private String label;

    /**
     * 正文
     */
    private String content;

    /**
     * 作者
     */
    private String source;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 修改时间
     */
    private String updatetime;

    /**
     * 序列号
     * 不是本表的字段，即，插入记录时，本属性不会作为值插入到表中
     */
    @TableField(exist = false)
    private String seqNo;

}

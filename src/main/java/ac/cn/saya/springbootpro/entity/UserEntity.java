package ac.cn.saya.springbootpro.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @Title: UserEntity
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 6/20/21 23:53
 * @Description:
 */

@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity{

    private Integer id;

    @NotNull(message = "name cannot be null",groups = UserEntity.InsetScene.class)
    private String name;

    @NotNull(message = "age cannot be null",groups = UserEntity.InsetScene.class)
    private Integer age;

}

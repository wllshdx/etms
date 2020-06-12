package cn.wllnb.etms.model.bo;

import cn.wllnb.etms.common.constants.UserType;
import cn.wllnb.etms.model.entities.AdminEntity;
import cn.wllnb.etms.model.entities.StudentEntity;
import cn.wllnb.etms.model.entities.TeacherEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author WLL
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfoBO {
    @NotNull
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Integer userType;

    private Integer permission = 0;

    public AuthInfoBO(@NotNull Integer id, @NotNull String username, @NotNull String password, @NotNull Integer userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public static AuthInfoBO fromStudent(StudentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(Integer.valueOf(entity.getNumber()), entity.getName(),
                entity.getPassword(), UserType.STUDENT);
    }

    public static AuthInfoBO fromTeacher(TeacherEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(Integer.valueOf(entity.getNumber()), entity.getName(), entity.getPassword(), UserType.TEACHER);
    }

    public static AuthInfoBO fromAdmin(AdminEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getId(), entity.getUsername(), entity.getPassword(), UserType.ADMIN,
                entity.getPrivilege());
    }
}

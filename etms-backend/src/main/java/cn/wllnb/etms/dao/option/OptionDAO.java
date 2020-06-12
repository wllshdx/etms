package cn.wllnb.etms.dao.option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author WLL
 */
@Repository
public class OptionDAO {
    private static final String HASH_NAME = "etms_option";
    private static final String ALLOW_STUDENT_SELECT = "allow_student_select";
    private static final String ALLOW_TEACHER_GRADE = "allow_teacher_grade";

    private static final Boolean DEFAULT_ALLOW_STUDENT_SELECT = true;
    private static final Boolean DEFAULT_ALLOW_TEACHER_GRADE = true;


    @Autowired
    private RedisTemplate<String, Object> template;

    public Boolean getAllowStudentSelect() {
        Boolean res = (Boolean) get(ALLOW_STUDENT_SELECT);
        if (res == null) {
            setAllowStudentSelect(DEFAULT_ALLOW_STUDENT_SELECT);
            res = true;
        }
        return res;
    }

    public void setAllowStudentSelect(Boolean status) {
        set(ALLOW_STUDENT_SELECT, status);
    }

    public Boolean getAllowTeacherGrade() {
        Boolean res = (Boolean) get(ALLOW_TEACHER_GRADE);
        if (res == null) {
            setAllowTeacherGrade(DEFAULT_ALLOW_TEACHER_GRADE);
            res = true;
        }
        return res;
    }

    public void setAllowTeacherGrade(Boolean status) {
        set(ALLOW_TEACHER_GRADE, status);
    }


    private Object get(String key) {
        return template.opsForHash().get(HASH_NAME, key);
    }

    private void set(String key, Object value) {
        template.opsForHash().put(HASH_NAME, key, value);
    }

}

package cn.wllnb.etms.service;

import cn.wllnb.etms.dao.option.OptionDAO;

import cn.wllnb.etms.model.vo.response.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WLL
 */
@Service
public class OptionService extends BaseService {
    @Autowired
    private OptionDAO optionDAO;

    public ResultVO setAllowStudentSelect(Boolean status) {
        optionDAO.setAllowStudentSelect(status);
        return result("成功");
    }

    public ResultVO getAllowStudentSelect() {
        return result(optionDAO.getAllowStudentSelect());
    }

    public ResultVO setAllowTeacherGrade(Boolean status) {
        optionDAO.setAllowTeacherGrade(status);
        return result("成功");
    }

    public ResultVO getAllowTeacherGrade() {
        return result(optionDAO.getAllowTeacherGrade());
    }

}

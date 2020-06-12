package cn.wllnb.etms.controller.admin;

import cn.wllnb.etms.authentication.annotation.Admin;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.entities.ClassEntity;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.admin.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author WLL
 */
@Admin(Admin.CLASS_MANAGE)
@RestController
@RequestMapping("/admin/class")
public class ClassController extends BaseController {

    @Autowired
    private ClassService service;

    @GetMapping("/{number}")
    public ResultVO get(@PathVariable Integer number) {
        return service.get(number);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated ClassEntity entity) {
        return service.create(entity);
    }

    @DeleteMapping("/{number}")
    public ResultVO delete(@PathVariable Integer number){
        return service.delete(number);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated ClassEntity entity){
        return service.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String majorName, String name) {
        return service.getPageCount(departmentName, majorName, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String departmentName, String majorName, String name) {
        return service.getPage(1, departmentName, majorName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String majorName, String name) {
        return service.getPage(index, departmentName, majorName, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return service.listName();
    }
}

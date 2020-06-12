package cn.wllnb.etms.controller.admin;

import cn.wllnb.etms.authentication.annotation.Admin;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.entities.MajorEntity;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.admin.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author WLL
 */
@Admin(Admin.MAJOR_MANAGE)
@RequestMapping("/admin/major")
@RestController
public class MajorController extends BaseController {
    @Autowired
    private MajorService service;

    @GetMapping("/{number}")
    public ResultVO get(@PathVariable Integer number) {
        return service.get(number);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated MajorEntity entity) {
        System.out.println(entity);
        return service.create(entity);
    }

    @DeleteMapping("/{number}")
    public ResultVO delete(@PathVariable Integer number) {
        return service.delete(number);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated MajorEntity entity) {
        return service.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String name) {
        return service.getPageCount(departmentName, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String departmentName, String name) {
        return service.getPage(1, departmentName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String name) {
        return service.getPage(index, departmentName, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return service.listName();
    }
}

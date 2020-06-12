package cn.wllnb.etms.controller.admin;

import cn.wllnb.etms.authentication.annotation.Admin;
import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.model.entities.AdminEntity;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author WLL
 */
@Admin(Admin.ADMIN_MANAGE)
@RequestMapping("/admin/admin")
@RestController
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return adminService.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated AdminEntity entity) {
        return adminService.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return adminService.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated AdminEntity entity) {
        return adminService.update(entity);
    }

    @GetMapping
    public ResultVO list(){
        return adminService.list();
    }

}

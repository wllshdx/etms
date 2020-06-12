package cn.wllnb.etms.service.admin;

import cn.wllnb.etms.controller.BaseController;
import cn.wllnb.etms.mapper.AdminMapper;
import cn.wllnb.etms.model.entities.AdminEntity;
import cn.wllnb.etms.model.vo.response.ResultVO;
import cn.wllnb.etms.utils.Md5Encrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author WLL
 */
@Service
public class AdminService extends BaseController {
    @Resource
    private AdminMapper adminMapper;

    public ResultVO get(Integer id) {
        AdminEntity adminEntity = adminMapper.selectById(id);
        if (adminEntity == null) {
            return failedResult("管理员Id：" + id + "不存在！");
        }

        adminEntity.setPassword("");
        return result(adminEntity);
    }

    public ResultVO update(AdminEntity adminEntity) {
        AdminEntity originEntity = adminMapper.selectById(adminEntity.getId());
        if (originEntity == null) {
            return failedResult("管理员Id: " + adminEntity.getId() + "不存在!");
        }
        AdminEntity entity = adminMapper.selectOne(new LambdaQueryWrapper<AdminEntity>().eq(AdminEntity::getUsername, adminEntity.getUsername()));
        if (entity != null) {
            if (!entity.getId().equals(adminEntity.getId())) {
                return failedResult("管理员姓名: " + entity.getUsername() + "已存在!");
            }
        }

        if ("".equals(adminEntity.getPassword())) {
            adminEntity.setPassword(originEntity.getPassword());
        } else {
            adminEntity.setPassword(Md5Encrypt.computePasswordHash(adminEntity.getPassword()));
        }

        adminMapper.updateById(adminEntity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (adminMapper.selectById(id) == null) {
            return failedResult("管理员Id: " + id + "不存在!");
        }
        adminMapper.deleteById(id);

        return result("删除成功");
    }

    public ResultVO create(AdminEntity entity) {
        if (adminMapper.selectById(entity.getId()) != null) {
            return failedResult("管理员Id: " + entity.getId() + "已存在!");
        }
        if (adminMapper.selectOne(new LambdaQueryWrapper<AdminEntity>().eq(AdminEntity::getUsername, entity.getUsername())) != null) {
            return failedResult("管理员姓名: " + entity.getUsername() + "已存在!");
        }

        adminMapper.insert(entity);
        return result("添加新管理员成功");
    }

    public ResultVO list() {
        return result(adminMapper.selectList(new LambdaQueryWrapper<>()));
    }
}

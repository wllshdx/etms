package cn.wllnb.etms.dao.log;

import cn.wllnb.etms.model.entities.mongo.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author WLL
 */
@Repository
public class LogDAO {

    @Autowired
    private LogRepository repository;

    public void insert(LogEntity entity) {
        repository.insert(entity);
    }
}

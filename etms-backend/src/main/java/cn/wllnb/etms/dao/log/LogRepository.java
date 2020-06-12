package cn.wllnb.etms.dao.log;

import cn.wllnb.etms.model.entities.mongo.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author WLL
 */
@Repository
public interface LogRepository extends MongoRepository<LogEntity, Long> {
}

package ${package}.service.impl;

import lombok.extern.log4j.Log4j2;
import ${package}.repository.schema_name.TableNameRepository;
import ${package}.entity.schema_name.TableName;
import ${package}.service.CustomService;
import ${package}.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
@Log4j2
public class CustomServiceImpl implements CustomService {

    @Autowired
    private TableNameRepository repo;

    @Override
    @Cacheable("customCacheName")
    @Transactional(transactionManager = "schema_nameTransactionManager")
    public TableName getCustomValue() {
        Optional<TableName> opt = this.repo.getCustomValue("test123");
        opt.orElseThrow(() -> new CustomException("Not found!"));

        return opt.get();
    }

    @CacheEvict(value = "customCacheName", allEntries = true)
    @Scheduled(fixedDelayString = "${cache.customCacheName.clearInterval}")
    public void cacheScoresEvict() {
        log.info("Cache 'customCacheName' fully cleared");
    }

}

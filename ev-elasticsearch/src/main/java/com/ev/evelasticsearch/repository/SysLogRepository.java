package com.ev.evelasticsearch.repository;

import com.ev.evelasticsearch.entity.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

/**
 * @author
 * @create 2019-02-09 下午7:39
 **/
@Repository
public interface SysLogRepository extends ElasticsearchRepository<SysLog, String> {

}

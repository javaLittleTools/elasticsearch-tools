package com.oc.elastic.repository;

import com.oc.elastic.entity.House;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author SxL
 * Created on 11/15/2018 13:43.
 */
public interface HouseRepository extends ElasticsearchRepository<House, Long> {

}

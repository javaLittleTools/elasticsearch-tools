package com.oc.elastic.dto;

import lombok.Data;

/**
 * 查询条件
 * @author SxL
 * Created on 11/17/2018 14:03.
 */
@Data
public class QueryBody {

    private String index;

    private Integer page = 0;

    private Integer size = 15;

    private Match match;

    private QueryString queryString;

    private Term term;
}

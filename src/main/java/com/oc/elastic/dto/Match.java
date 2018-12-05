package com.oc.elastic.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author SxL
 * Created on 11/18/2018 11:54.
 */
@Data
@ToString
public class Match {

    private String field;

    private List<String> fieldList;

    private String query;

    private Integer slop = 0;

    private String operator = "or";

    private String miniShouldMatch = "1";
}

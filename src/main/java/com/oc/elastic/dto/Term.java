package com.oc.elastic.dto;

import lombok.Data;

import java.util.List;

/**
 * @author SxL
 * Created on 12/3/2018 3:13 PM.
 */
@Data
public class Term {

    private String query;

    private String field;

    private List<String> queryList;
}

package com.oc.elastic.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author SxL
 * Created on 11/18/2018 12:35.
 */
@Data
@ToString
public class QueryString {

    private String query;

    private String defaultField;

    private List<String> fieldList;
}

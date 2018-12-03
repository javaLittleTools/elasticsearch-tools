package com.oc.elastic.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author SxL
 * Created on 12/3/2018 3:25 PM.
 */
@Data
public class Range {

    private String name;

    private String gte;

    private String gt;

    private String lte;

    private String lt;

    private Date from;

    private Date to;

    private Float boost;
}

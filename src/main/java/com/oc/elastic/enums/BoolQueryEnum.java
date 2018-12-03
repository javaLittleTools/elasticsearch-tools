package com.oc.elastic.enums;

/**
 * @author SxL
 * Created on 12/3/2018 5:15 PM.
 */
public enum BoolQueryEnum {
    /**
     * BoolQuery
     */
    FILTER("filter"),
    SHOULD("should"),
    MUST("must"),
    MUST_NOT("must_not");

    BoolQueryEnum(String value) {
    }

}

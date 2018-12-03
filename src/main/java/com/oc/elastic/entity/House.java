package com.oc.elastic.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SxL
 * Created on 11/15/2018 13:36.
 */
@Document(indexName = "demo", type = "doc")
@Data
@ToString
public class House implements Serializable {
    private Long id;

    private Integer accommodates;

    private Float bathrooms;

    private String bed_type;

    private Integer bedrooms;

    private Integer beds;

    private Date date_from;

    private Date date_to;

    private Boolean has_availability;

    private String host_image;

    private String host_name;

    private String image;

    private String listing_url;

    private GeoPoint location;

    private String name;

    private Float price;

    private String property_type;

    private String room_type;
}

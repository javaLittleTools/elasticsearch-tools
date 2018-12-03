package com.oc.elastic.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;

/**
 * @author SxL
 * Created on 11/15/2018 16:33.
 */
@Data
@ToString
public class HouseVO {
    private Long id;

    private Integer accommodates;

    private Float bathrooms;

    private String bedType;

    private Integer bedrooms;

    private Integer beds;

    private Date dateFrom;

    private Date dateTo;

    private Boolean hasAvailability;

    private String hostImage;

    private String hostName;

    private String image;

    private String listingUrl;

    private GeoPoint location;

    private String name;

    private Float price;

    private String propertyType;

    private String roomType;
}

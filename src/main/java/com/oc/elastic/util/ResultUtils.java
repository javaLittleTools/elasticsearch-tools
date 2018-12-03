package com.oc.elastic.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author SxL
 * Created on 11/17/2018 11:34.
 */
public class ResultUtils {

    public static ResponseEntity searchResult(Page page, List<Object> list) {
        if (page.getTotalPages() > 0) {
            list.addAll(page.getContent());
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public static ResponseEntity searchResult(Page housePage, List<Object> houseList, List<Object> houseVOList) {
        if (housePage.getTotalPages() > 0) {
            houseList.addAll(housePage.getContent());
            try {
                BeanUtils.turnToVo(houseList, houseVOList);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(houseVOList, HttpStatus.OK);
    }
}

package com.oc.elastic.controller;

import com.oc.elastic.dto.QueryBody;
import com.oc.elastic.entity.House;
import com.oc.elastic.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.oc.elastic.util.QueryUtils.*;
import static com.oc.elastic.util.ResultUtils.searchResult;

/**
 * @author SxL
 * Created on 11/15/2018 13:44.
 */
@RestController
public class HouseController {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @GetMapping("/house")
    public ResponseEntity matchAllHouse(@RequestBody QueryBody queryBody) {
        List<Object> houseList = new ArrayList<>();
        List<Object> houseVOList = new ArrayList<>();

        SearchQuery searchQuery = buildQuery(queryBody, matchAll());

        Page housePage = houseRepository.search(searchQuery);

       return searchResult(housePage, houseList, houseVOList);
    }

    @GetMapping("/house/match")
    public ResponseEntity matchHouse(@RequestBody QueryBody queryBody) {
        List<Object> houseList = new ArrayList<>();
        List<Object> houseVOList = new ArrayList<>();

        SearchQuery searchQuery = buildQuery(queryBody, match(queryBody));

        Page<House> housePage = houseRepository.search(searchQuery);

        return searchResult(housePage, houseList, houseVOList);
    }

    @GetMapping("/house/multi_match_value")
    public ResponseEntity multiMatchSameValueHouse(@RequestBody QueryBody queryBody) {
        List<Object> houseList = new ArrayList<>();
        List<Object> houseVOList = new ArrayList<>();

        SearchQuery searchQuery = buildQuery(queryBody, multiMatchForSameValue(queryBody));

        Page<House> housePage = houseRepository.search(searchQuery);

        return searchResult(housePage, houseList, houseVOList);
    }

    @GetMapping("/house/match_phrase")
    public ResponseEntity matchPhraseHouse(@RequestBody QueryBody queryBody) {
        List<Object> houseList = new ArrayList<>();
        List<Object> houseVOList = new ArrayList<>();

        SearchQuery searchQuery = buildQuery(queryBody, matchPhrase(queryBody));

        Page<House> housePage = houseRepository.search(searchQuery);

        return searchResult(housePage, houseList, houseVOList);
    }

    @GetMapping("/house/query_string")
    public ResponseEntity queryStringHouse(@RequestBody QueryBody queryBody) {
        List<Object> houseList = new ArrayList<>();
        List<Object> houseVOList = new ArrayList<>();

        SearchQuery searchQuery = buildQuery(queryBody, queryString(queryBody));

        Page<House> housePage = houseRepository.search(searchQuery);

        return searchResult(housePage, houseList, houseVOList);
    }
}

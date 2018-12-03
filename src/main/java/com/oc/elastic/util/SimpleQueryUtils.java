package com.oc.elastic.util;

import com.google.common.collect.Maps;
import com.oc.elastic.dto.QueryBody;
import org.elasticsearch.index.query.Operator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @author SxL
 * Created on 11/17/2018 11:14.
 */
public class SimpleQueryUtils {
    public static SearchQuery matchAll(QueryBody queryBody) {
        return new NativeSearchQueryBuilder()
                .withIndices(queryBody.getIndex())
                .withQuery(matchAllQuery())
                .withPageable(PageRequest.of(queryBody.getPage(), queryBody.getSize()))
                .build();
    }

    public static SearchQuery match(QueryBody queryBody) {
        return new NativeSearchQueryBuilder()
                .withIndices(queryBody.getIndex())
                .withQuery(matchQuery(queryBody.getMatch().getName(), queryBody.getMatch().getQuery())
                        .operator(Operator.fromString(queryBody.getMatch().getOperator()))
                        .minimumShouldMatch(queryBody.getMatch().getMiniShouldMatch()))
                .withPageable(PageRequest.of(queryBody.getPage(), queryBody.getSize()))
                .build();
    }

    public static SearchQuery multiMatchForSameValue(QueryBody queryBody) {
        String[] matchNames = new String[queryBody.getMatch().getNameList().size()];

        return new NativeSearchQueryBuilder()
                .withIndices(queryBody.getIndex())
                .withQuery(multiMatchQuery(queryBody.getMatch().getQuery(), queryBody.getMatch().getNameList().toArray(matchNames))
                        .operator(Operator.fromString(queryBody.getMatch().getOperator())))
                .withPageable(PageRequest.of(queryBody.getPage(), queryBody.getSize()))
                .build();
    }

    public static SearchQuery matchPhrase(QueryBody queryBody) {
        return new NativeSearchQueryBuilder()
                .withIndices(queryBody.getIndex())
                .withQuery(matchPhraseQuery(queryBody.getMatch().getName(), queryBody.getMatch().getQuery())
                        .slop(queryBody.getMatch().getSlop()))
                .withPageable(PageRequest.of(queryBody.getPage(), queryBody.getSize()))
                .build();
    }

    public static SearchQuery queryString(QueryBody queryBody) {
        Map<String, Float> fields = Maps.newHashMap();

        for (String s : queryBody.getQueryString().getFieldList()) {
            String[] fieldString = s.split("\\^");
            fields.put(fieldString[0], Float.valueOf(fieldString[1]));
        }

        return new NativeSearchQueryBuilder()
                .withIndices(queryBody.getIndex())
                .withQuery(queryStringQuery(queryBody.getQueryString().getQuery())
                        .defaultField(queryBody.getQueryString().getDefaultField())
                        .fields(fields))
                .withPageable(PageRequest.of(queryBody.getPage(), queryBody.getSize()))
                .build();
    }

    public static SearchQuery term(QueryBody queryBody) {
        return new NativeSearchQueryBuilder()
                .withIndices(queryBody.getIndex())
                .withQuery(termQuery(queryBody.getTerm().getName(), queryBody.getTerm().getQuery()))
                .withPageable(PageRequest.of(queryBody.getPage(), queryBody.getSize()))
                .build();
    }

    public static SearchQuery terms(QueryBody queryBody) {
        String[] queryValues = new String[queryBody.getTerm().getQueryList().size()];

        return new NativeSearchQueryBuilder()
                .withIndices(queryBody.getIndex())
                .withQuery(termsQuery(queryBody.getTerm().getName(), queryBody.getTerm().getQueryList().toArray(queryValues)))
                .withPageable(PageRequest.of(queryBody.getPage(), queryBody.getSize()))
                .build();
    }
}

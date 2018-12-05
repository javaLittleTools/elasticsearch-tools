package com.oc.elastic.util;

import com.google.common.collect.Maps;
import com.oc.elastic.dto.QueryBody;
import com.oc.elastic.enums.BoolQueryEnum;
import org.elasticsearch.index.query.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @author SxL
 * Created on 11/17/2018 11:14.
 */
public class QueryUtils {

    public static SearchQuery buildQuery(QueryBody queryBody, QueryBuilder queryBuilder) {
        return new NativeSearchQueryBuilder()
                .withIndices(queryBody.getIndex())
                .withQuery(queryBuilder)
                .withPageable(PageRequest.of(queryBody.getPage(), queryBody.getSize()))
                .build();
    }

    public static MatchAllQueryBuilder matchAll() {
        return matchAllQuery();
    }

    public static MatchQueryBuilder match(QueryBody queryBody) {
        return matchQuery(queryBody.getMatch().getField(), queryBody.getMatch().getQuery())
                .operator(Operator.fromString(queryBody.getMatch().getOperator()))
                .minimumShouldMatch(queryBody.getMatch().getMiniShouldMatch());
    }

    public static MultiMatchQueryBuilder multiMatchForSameValue(QueryBody queryBody) {
        String[] matchNames = new String[queryBody.getMatch().getFieldList().size()];

        return multiMatchQuery(queryBody.getMatch().getQuery(), queryBody.getMatch().getFieldList().toArray(matchNames))
                .operator(Operator.fromString(queryBody.getMatch().getOperator()));
    }

    public static MatchPhraseQueryBuilder matchPhrase(QueryBody queryBody) {
        return matchPhraseQuery(queryBody.getMatch().getField(), queryBody.getMatch().getQuery())
                .slop(queryBody.getMatch().getSlop());
    }

    public static QueryStringQueryBuilder queryString(QueryBody queryBody) {
        Map<String, Float> fields = Maps.newHashMap();

        for (String s : queryBody.getQueryString().getFieldList()) {
            String[] fieldString = s.split("\\^");
            fields.put(fieldString[0], Float.valueOf(fieldString[1]));
        }

        return queryStringQuery(queryBody.getQueryString().getQuery())
                .defaultField(queryBody.getQueryString().getDefaultField())
                .fields(fields);
    }

    public static TermQueryBuilder term(QueryBody queryBody) {
        return termQuery(queryBody.getTerm().getField(), queryBody.getTerm().getQuery());
    }

    public static TermsQueryBuilder terms(QueryBody queryBody) {
        String[] queryValues = new String[queryBody.getTerm().getQueryList().size()];

        return termsQuery(queryBody.getTerm().getField(), queryBody.getTerm().getQueryList().toArray(queryValues));
    }

    public static RangeQueryBuilder dateFrom(QueryBody queryBody) {
        return rangeQuery(queryBody.getRange().getField())
                .from(queryBody.getRange().getFrom());
    }

    public static RangeQueryBuilder dateTo(QueryBody queryBody) {
        return rangeQuery(queryBody.getRange().getField())
                .to(queryBody.getRange().getTo());
    }

    public static RangeQueryBuilder dateRange(QueryBody queryBody) {
        return rangeQuery(queryBody.getRange().getField())
                .from(queryBody.getRange().getFrom())
                .to(queryBody.getRange().getTo());
    }

    public static RangeQueryBuilder numericGt(QueryBody queryBody) {
        return rangeQuery(queryBody.getRange().getField())
                .gt(queryBody.getRange().getGt());
    }

    public static RangeQueryBuilder numericGte(QueryBody queryBody) {
        return rangeQuery(queryBody.getRange().getField())
                .gte(queryBody.getRange().getGte());
    }

    public static RangeQueryBuilder numericLt(QueryBody queryBody) {
        return rangeQuery(queryBody.getRange().getField())
                .lt(queryBody.getRange().getLt());
    }

    public static RangeQueryBuilder numericLte(QueryBody queryBody) {
        return rangeQuery(queryBody.getRange().getField())
                .lte(queryBody.getRange().getLte());
    }

    public static RangeQueryBuilder numericRange(QueryBody queryBody) {
        RangeQueryBuilder rangeQueryBuilder = rangeQuery(queryBody.getRange().getField());

        if (queryBody.getRange().getGt() != null) {
            rangeQueryBuilder.gt(queryBody.getRange().getGt());
        }

        if (queryBody.getRange().getGte() != null) {
            rangeQueryBuilder.gte(queryBody.getRange().getGte());
        }

        if (queryBody.getRange().getLt() != null) {
            rangeQueryBuilder.lt(queryBody.getRange().getLt());
        }

        if (queryBody.getRange().getLte() != null) {
            rangeQueryBuilder.lte(queryBody.getRange().getLte());
        }

        return rangeQueryBuilder;
    }


    public static BoolQueryBuilder bool(HashMap<String, QueryBuilder> boolMap) {

        BoolQueryBuilder boolQueryBuilder = boolQuery();

        if (boolMap.containsKey(BoolQueryEnum.FILTER)) {
            boolQueryBuilder.must(boolMap.get(BoolQueryEnum.FILTER));
        }

        if (boolMap.containsKey(BoolQueryEnum.SHOULD)) {
            boolQueryBuilder.should(boolMap.get(BoolQueryEnum.SHOULD));
        }

        if (boolMap.containsKey(BoolQueryEnum.MUST)) {
            boolQueryBuilder.must(boolMap.get(BoolQueryEnum.MUST));
        }

        if (boolMap.containsKey(BoolQueryEnum.MUST_NOT)) {
            boolQueryBuilder.mustNot(boolMap.get(BoolQueryEnum.MUST_NOT));
        }

        return boolQueryBuilder;
    }
}

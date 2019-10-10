package personal.nathan;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author nathan.z
 * @date 2019/10/9.
 */
@Component
public class SearchService {

    @Autowired
    private RestHighLevelClient esClient;

    public void search(String index, String key, String value) {

        // 构建搜索
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 查询条件很多,如果做搜索时有些选项可填可不填,可空克不空
        // 创建条件查询
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        // 条件一
        boolBuilder.must(QueryBuilders.matchPhraseQuery(key, value));
        // 分个页(from从第几条开始,size每页显示几条)
        // 将条件添加进构造的搜索
        searchSourceBuilder.query(boolBuilder);
        SearchRequest searchRequest = new SearchRequest();
        // 设置索引
        searchRequest.indices(index);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits searchHits = searchResponse.getHits();
        System.out.println("查到数目:" + searchHits.getTotalHits());
        SearchHit[] hits = searchHits.getHits();
        ArrayList<String> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            list.add(json);
        }
        System.out.println(list);
    }
}

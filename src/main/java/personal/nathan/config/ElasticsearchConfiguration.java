package personal.nathan.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nathan.z
 * @date 2019/10/9.
 */
@Configuration
public class ElasticsearchConfiguration {

    /**
     * 读取自定义配置
     */
    @Value("${spring.data.elasticsearch.host}")
    private String HOST;

    @Value("${spring.data.elasticsearch.port}")
    private String PORT;

    @Bean
    public RestHighLevelClient getClient(){

        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(HOST,
                Integer.valueOf(PORT), "http"));
        RestHighLevelClient client = new RestHighLevelClient(clientBuilder);
        return  client;
    }


}

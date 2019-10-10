package personal.nathan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author nathan.z
 * @date 2019/10/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Autowired
    private SearchService searchService;

    @Test
    public void testEsSearch() {
//        searchService.search("kibana_sample_data_ecommerce", "currency", "EUR");
        // trace:8989683255cb7ee2
        searchService.search("microlog-*", "trace", "8989683255cb7ee2");
    }
}

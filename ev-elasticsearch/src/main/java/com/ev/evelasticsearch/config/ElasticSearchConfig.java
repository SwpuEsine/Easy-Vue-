package com.ev.evelasticsearch.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author
 * @create 2019-02-09 下午3:52
 **/
@Configuration
public class ElasticSearchConfig {
  /*  @Bean
    public TransportClient esClient(){
        Settings settings=Settings.builder().
                put("cluster.name","esine-cluster").
                put("client.transport.sniff",true).build(); //自动发现节点
        InetSocketTransportAddress
    }
*/
  //使用自动化配置
}

package com.ajoshow.playground.service.impl;

import com.ajoshow.playground.domain.AdContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andychu on 2017/4/22.
 */
@Service
public class AdContentServiceImpl {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${app.tenmax.datasource.url:127.0.0.1}")
    private String tenMaxDSUrl;
    @Value("${app.mockserver.datasource.url:127.0.0.1}")
    private String mockServerDSUrl;

    public AdContent fetchContent(String url){
        AdContent data = restTemplate.getForObject(tenMaxDSUrl, AdContent.class);
        return data;
    }
}

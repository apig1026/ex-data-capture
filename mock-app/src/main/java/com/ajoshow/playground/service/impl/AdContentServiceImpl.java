package com.ajoshow.playground.service.impl;

import com.ajoshow.playground.domain.dto.AdContentDto;
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


    @Value("${app.tenmax.datasource.url}")
    private String tenMaxDSUrl;
    @Value("${app.mockserver.datasource.url}")
    private String mockServerDSUrl;

    public AdContentDto fetchContent(String url){
        AdContentDto data = restTemplate.getForObject(tenMaxDSUrl, AdContentDto.class);
        return data;
    }
}

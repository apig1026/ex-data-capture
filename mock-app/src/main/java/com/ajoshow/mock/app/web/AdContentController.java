package com.ajoshow.mock.app.web;

import com.ajoshow.mock.web.dto.AdContentDto;
import com.ajoshow.mock.repository.entity.AdContentEntity;
import com.ajoshow.mock.service.AdContentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ajoshow.mock.web.converter.BeanConverter.convertToDto;
import static com.ajoshow.mock.web.converter.BeanConverter.convertToEntity;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by andychu on 2017/4/22.
 */
@Validated
@Controller
@RequestMapping(value = "ad-contents", params = "v=1")
public class AdContentController {

    @Value("${app.tenmax.datasource.url}")
    private String tenMaxDSUrl;

    @Value("${app.mockserver.datasource.url}")
    private String mockServerDSUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("AdContentServiceImpl")
    private AdContentService svc;

    @Autowired
    private ObjectMapper objectMapper;

    // << Basic Question 1 >>
    @Scheduled(cron = "${app.schedule.fetch.content.cron}")
    public void scheduleFetchAndSaveContent() throws IOException {
        fetchAndSaveContent();
    }

    // << Advanced Question 2 >>
    @Scheduled(cron = "${app.schedule.fetch.content.cron}")
    public void scheduleAdvancedFetchAndSaveContent() throws IOException {
        advancedFetchAndSaveContent();
    }

    @ResponseStatus(OK)
    @ResponseBody
    @RequestMapping(method= GET, value="", params="do=fetch")
    public AdContentDto fetchAndSaveContent() throws IOException {
        AdContentDto dto = fetchAdContent(tenMaxDSUrl);
        AdContentEntity entity = convertToEntity(dto);

        if(entity != null){
            // debug | log event
            System.out.println(objectMapper.writeValueAsString(entity));

            // << Basic Question 2 >>
            svc.saveOrUpdateAdContent(entity);
        }else{
            // invalid or null response body causes NULL entity
            // log event or do other business logic.
        }
        return dto;
    }

    @ResponseStatus(OK)
    @ResponseBody
    @RequestMapping(method= GET, value="", params="do=adv-fetch")
    public AdContentDto advancedFetchAndSaveContent() throws IOException {
        AdContentDto dto;

        // TODO
        dto = fetchAdContent(tenMaxDSUrl);
        dto = fetchAdContent(mockServerDSUrl);

        AdContentEntity entity = convertToEntity(dto);
        if(entity != null){
            // debug | log event
            System.out.println(objectMapper.writeValueAsString(entity));
            svc.saveOrUpdateAdContent(entity);
        }else{
            // invalid or null response body causes NULL entity
            // log event or do other business logic.
        }
        return dto;
    }

    // << Basic Question 3 >>
    @ResponseStatus(OK)
    @ResponseBody
    @RequestMapping(method= POST, value="/query")
    public List<AdContentDto> findAdContentEntityByTitle(
            @RequestParam(name="title") String title
    ) throws IOException {
        List<AdContentEntity> entities = svc.findAdContentEntityByTitle(title);
        List<AdContentDto> dtos = new ArrayList<>();
        for(AdContentEntity entity : entities){
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    /**
     * Send GET request to fetch data and return in form of {@link AdContentDto} object.
     *
     * @return {@link AdContentDto} object.
     * @throws IOException
     */
    private AdContentDto fetchAdContent(String url) throws IOException{
        return restTemplate.getForObject(url, AdContentDto.class);
    }

}

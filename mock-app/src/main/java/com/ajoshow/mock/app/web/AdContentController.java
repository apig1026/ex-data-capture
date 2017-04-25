package com.ajoshow.mock.app.web;

import com.ajoshow.mock.repository.entity.AdContent;
import com.ajoshow.mock.service.AdContentService;
import com.ajoshow.mock.web.dto.AdContentDto;
import com.ajoshow.mock.web.dto.AdContentWrapDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    public void scheduleFetchAndSaveContent()throws IOException {
        fetchAndSaveContent();
    }

    // << Advanced Question 2 >>
    @Scheduled(cron = "${app.schedule.fetch.content.cron}")
    public void scheduleAdvancedFetchAndSaveContent()throws IOException, ExecutionException, InterruptedException {
        advancedFetchAndSaveContent();
    }

    @ResponseBody
    @RequestMapping(method= GET, value="", params="do=fetch")
    public ResponseEntity fetchAndSaveContent()throws IOException {
        AdContentWrapDto dto = fetchAdContent(tenMaxDSUrl);
        AdContent entity = convertToEntity(dto.getAdContentDto());

        if(entity != null){
            // debug | log event
            System.out.println(objectMapper.writeValueAsString(entity));

            // << Basic Question 2 >>
            svc.saveOrUpdateAdContent(entity);
        }else{
            // invalid or null response body causes NULL entity
            // log event or do other business logic.
        }
        return new ResponseEntity(dto, OK);
    }

    @ResponseBody
    @RequestMapping(method= GET, value="", params="do=adv-fetch")
    public ResponseEntity advancedFetchAndSaveContent() throws IOException, ExecutionException, InterruptedException {

        CompletableFuture<AdContentWrapDto> fetchTenMaxContentFuture = CompletableFuture.supplyAsync(()->fetchAdContent(tenMaxDSUrl));
        CompletableFuture<AdContentWrapDto> fetchMockServerContentFuture = CompletableFuture.supplyAsync(()->fetchAdContent(mockServerDSUrl));
        CompletableFuture fetchContentFuture = CompletableFuture.anyOf(fetchTenMaxContentFuture, fetchMockServerContentFuture);
        AdContentWrapDto dto = (AdContentWrapDto) fetchContentFuture.get();

        AdContent entity = convertToEntity(dto.getAdContentDto());
        if(entity != null){
            // debug | log event
            System.out.println(objectMapper.writeValueAsString(entity));
            svc.saveOrUpdateAdContent(entity);
        }else{
            // invalid or null response body causes NULL entity
            // log event or do other business logic.
        }
        return new ResponseEntity(dto, OK);
    }

    // << Basic Question 3 >>
    @ResponseBody
    @RequestMapping(method= POST, value="/query")
    public ResponseEntity findAdContentEntityByTitle(
            @RequestParam(name="title") String title
    ) throws IOException {
        List<AdContent> entities = svc.findAdContentEntityByTitle(title);
        List<AdContentWrapDto> dtos = new ArrayList<>();
        AdContentDto dto;
        for(AdContent entity : entities){
            dto = convertToDto(entity);
            dtos.add(new AdContentWrapDto(dto));
        }
        return new ResponseEntity(dtos, OK);
    }

    /**
     * Send GET request to fetch data and return in form of {@link AdContentWrapDto} object.
     *
     * @return {@link AdContentWrapDto} object.
     * @throws IOException
     */
    private AdContentWrapDto fetchAdContent(String url) {
        return restTemplate.getForObject(url, AdContentWrapDto.class);
    }

}

package com.ajoshow.playground.controller;

import com.ajoshow.playground.domain.dto.AdContentDto;
import com.ajoshow.playground.service.impl.AdContentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by andychu on 2017/4/22.
 */
@Validated
@Controller
@RequestMapping(value = "")
public class AdContentController{

    @Autowired
    private AdContentServiceImpl svc;


    @Autowired
    private ObjectMapper objectMapper;

    @ResponseStatus(OK)
    @ResponseBody
    @RequestMapping(method= GET, value="/print")
    public AdContentDto fetchContent() {
        AdContentDto data = svc.fetchContent("");
        try {
            System.out.println(objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }
}

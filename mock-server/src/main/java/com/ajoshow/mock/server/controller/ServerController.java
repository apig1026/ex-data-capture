package com.ajoshow.mock.server.controller;

import com.ajoshow.mock.domain.AdContent;
import com.ajoshow.mock.domain.dto.AdContentDto;
import com.ajoshow.mock.domain.entity.AdContentEntity;
import com.ajoshow.mock.service.AdContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ajoshow.mock.converter.BeanConverter.convertToDto;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by andychu on 2017/4/22.
 */
@Validated
@Controller
@RequestMapping(value = "/server/ad-contents", params="v=1")
public class ServerController {

    @Autowired
    @Qualifier("AdContentServiceImpl")
    private AdContentService svc;

    private static final AdContentDto EMPTY_OBJECT = new AdContentDto();

    @ResponseStatus(OK)
    @ResponseBody
    @RequestMapping(method= GET, value="")
    public AdContentDto findAdContentEntityByTitle() throws IOException {
        if(new SecureRandom().nextBoolean()) {
            Optional<AdContentEntity> entityOpt = svc.getRandomAdContentEntity();
            if (entityOpt.isPresent()) {
                return convertToDto(entityOpt.get());
            } else {
                return EMPTY_OBJECT;
            }
        }else{
            try {
                Thread.sleep(10000); // 10 seconds delay
            } catch (InterruptedException e) {
                // log interrupted event
            }
            return EMPTY_OBJECT;
        }
    }



}

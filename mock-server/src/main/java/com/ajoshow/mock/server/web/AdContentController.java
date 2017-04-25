package com.ajoshow.mock.server.web;

import com.ajoshow.mock.repository.entity.AdContentEntity;
import com.ajoshow.mock.service.AdContentService;
import com.ajoshow.mock.web.EmptyResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Optional;

import static com.ajoshow.mock.web.converter.BeanConverter.convertToDto;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by andychu on 2017/4/22.
 */
@Validated
@Controller
@RequestMapping(value = "/ad-contents", params="v=1")
public class AdContentController {

    @Autowired
    @Qualifier("AdContentServiceImpl")
    private AdContentService svc;

    @ResponseBody
    @RequestMapping(method= GET, value="")
    public ResponseEntity findAdContentEntityByTitle() throws IOException {
        if(new SecureRandom().nextBoolean()) {
            Optional<AdContentEntity> entityOpt = svc.getRandomAdContentEntity();
            if (entityOpt.isPresent()) {
                return new ResponseEntity<>(convertToDto(entityOpt.get()), OK);
            } else {
                return new EmptyResponseEntity();
            }
        }else{
            try {
                Thread.sleep(10000); // 10 seconds delay
            } catch (InterruptedException e) {
                // log interrupted event
            }
            return new EmptyResponseEntity();
        }
    }



}

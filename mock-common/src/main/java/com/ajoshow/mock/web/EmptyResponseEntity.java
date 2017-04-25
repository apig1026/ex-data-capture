package com.ajoshow.mock.web;

import com.ajoshow.mock.web.dto.EmptyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Andy on 2017/4/25.
 */
public class EmptyResponseEntity extends ResponseEntity<EmptyDto> {

    public EmptyResponseEntity() {
        super(new EmptyDto(), HttpStatus.OK);
    }
}

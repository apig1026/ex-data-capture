package com.ajoshow.mock.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by andychu on 2017/4/22.
 */
public class AdContentWrapDto {
    @JsonProperty("native")
    private AdContentDto adContentDto;

    public AdContentWrapDto(){}

    public AdContentWrapDto(AdContentDto dto){
        this.adContentDto = dto;
    }

    public AdContentDto getAdContentDto() {
        return adContentDto;
    }

    public void setAdContentDto(AdContentDto adContentDto) {
        this.adContentDto = adContentDto;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdContentWrapDto{");
        sb.append("adContentDto=").append(adContentDto);
        sb.append('}');
        return sb.toString();
    }
}

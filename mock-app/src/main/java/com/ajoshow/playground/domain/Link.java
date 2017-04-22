package com.ajoshow.playground.domain;

import com.ajoshow.playground.converter.StringSetConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andychu on 2017/4/23.
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {

    private Set<String> clicktrackers;
    private String url;

    public Link(){
        clicktrackers = new HashSet<>();
    }

    @Column(name="link_url", length=512)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column
    @Convert(converter = StringSetConverter.class)
    public Set<String> getClicktrackers() {
        return clicktrackers;
    }

    public void setClicktrackers(Set<String> clicktrackers) {
        this.clicktrackers = clicktrackers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Link{");

        if(clicktrackers != null)
            sb.append(", clicktrackers=").append("[").append(StringUtils.join(clicktrackers, ";")).append("]");

        if(url != null)
            sb.append(", url='").append(url).append('\'');

        sb.append('}');
        return sb.toString();
    }
}

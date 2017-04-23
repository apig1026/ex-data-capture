package com.ajoshow.playground;

import com.ajoshow.playground.converter.AssetConverter;
import com.ajoshow.playground.converter.LinkConverter;
import com.ajoshow.playground.domain.Asset;
import com.ajoshow.playground.domain.Link;
import com.ajoshow.playground.domain.entity.AdContentAssetEntity;
import com.ajoshow.playground.domain.entity.AdContentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class MockAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockAppApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper()
				.setSerializationInclusion(JsonInclude.Include.NON_NULL)
				.configure(SerializationFeature.INDENT_OUTPUT, true)
				.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	}

	@Bean
	public DozerBeanMapper beanMapper() {
		DozerBeanMapper bm = new DozerBeanMapper();
		bm.setCustomConverters(Arrays.asList(
				new AssetConverter(Asset.class, AdContentAssetEntity.class),
				new LinkConverter(Link.class, Link.class)
		));
		bm.addMapping(new BeanMappingBuilder() {
			@Override
			protected void configure() {
				mapping(Asset.class, AdContentAssetEntity.class)
						.exclude("link");
			}
		});
		return bm;
	}
}

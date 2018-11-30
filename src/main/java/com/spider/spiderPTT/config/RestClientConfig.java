package com.spider.spiderPTT.config;

import java.nio.charset.Charset;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.spider.spiderPTT.handler.RestErrorHandler;
@Configuration
public class RestClientConfig {
	
	    @Bean  
	    public RestTemplate restTemplate() { 
	    	//對於ssl驗證全部通過 但對於安全有疑慮
	    	CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(httpClient);
//	        SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
	        //常用超时设置
	        requestFactory.setReadTimeout(6000);
	        requestFactory.setConnectTimeout(6000);
	        RestTemplate restTemplate = new RestTemplate(requestFactory);  
	        restTemplate.setErrorHandler(new RestErrorHandler());
	        restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
	        restTemplate.getMessageConverters().add(new MappingJackson2XmlHttpMessageConverter());
	        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
	        restTemplate.getMessageConverters().add(new TextMappingJackson2HttpMessageConverter());
	        return restTemplate;  
	    }  
}

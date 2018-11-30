package com.spider.spiderPTT.handler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class RestErrorHandler extends DefaultResponseErrorHandler {
       
	    @Override  
	    public void handleError(ClientHttpResponse response) throws IOException {  
	  
	    }  
}

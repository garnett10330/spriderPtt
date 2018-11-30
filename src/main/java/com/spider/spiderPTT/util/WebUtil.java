package com.spider.spiderPTT.util;




import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;




/**
 *
 */
public class WebUtil {
    public static final String FORMTYPE = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final String XML = "text/xml; charset=UTF-8";
    public static final String JSON = "application/json; charset=UTF-8";
    public static final String MUTIPART = "multipart/form-data; charset=UTF-8";
    public static final String TEXT = "text/html; charset=UTF-8";
	public static <T> HttpEntity<T>  getHttpEntity(T params,String contentType) {
		HttpHeaders headers = new HttpHeaders();
	    MediaType type = MediaType.parseMediaType(contentType);
	    headers.setContentType(type);
//	    headers.add("Cookie", "JSESSIONID=1231");
	    //String queryparam = WebUtil.buildQuery(params);
	    //HttpEntity<String> httpEntity = new HttpEntity<String>(queryparam, headers);
	    HttpEntity<T> httpEntity = new HttpEntity<T>(params, headers);
	    return httpEntity;
	}
	
	
	
}
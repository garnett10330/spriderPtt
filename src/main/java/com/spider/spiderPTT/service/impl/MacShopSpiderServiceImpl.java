package com.spider.spiderPTT.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.spider.spiderPTT.dao.MacShopBean;
import com.spider.spiderPTT.service.SpiderDataService;
import com.spider.spiderPTT.util.ParseUtil;
import com.spider.spiderPTT.util.WebUtil;


@Service
@Qualifier("MacShopSpiderServiceImpl")
public class MacShopSpiderServiceImpl implements SpiderDataService{
	private static final Logger log = LoggerFactory.getLogger(MacShopSpiderServiceImpl.class);
	private static final String pttUrl = "https://www.ptt.cc";
	private static final String macShopUrl = "/bbs/MacShop/search?page={page}&q={question}";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<MacShopBean> getSpiderData(String searchCondition) throws Exception{
		log.info("MacShopSpiderServiceImpl getSpiderData 開始");
		String searchArr[] = searchCondition.split(",");
		Assert.notEmpty(searchArr,"搜尋條件請以逗號分隔");
		String date = searchArr[1];
		String search  = searchArr[0];
		List<MacShopBean> macBeanList = new ArrayList<>();	
		String searchStr  = null;
		if(search.toUpperCase().contains("MAC") || search.toUpperCase().contains("IPAD")
				|| search.toUpperCase().contains("IPHONE")	
					) {
				searchStr = search;
		}						
		Assert.notNull(searchStr, "搜尋條件錯誤");
		String macUrl = pttUrl+macShopUrl;
		//搜尋10頁的看板
		for(int i =1;i<=10;i++) {
		    Map<String,Object> params = new HashMap<>();
	        params.put("page",i);
	        params.put("question",searchStr);
	        ResponseEntity<String> responseEntity = restTemplate.getForEntity(macUrl,String.class,params);
	        List<MacShopBean> list = parsePttData(responseEntity.getBody());
	        macBeanList.addAll(list);
		}
		log.info("MacShopSpiderServiceImpl getSpiderData 结束");
		return macBeanList;

	}
    private List<MacShopBean> parsePttData(String respObj) {
    	Assert.notNull(respObj, "傳入的resData為空值");
    	List<MacShopBean> beanList = new ArrayList<MacShopBean>();
    	Document shopDoc = Jsoup.parse(respObj);
    	Elements rentEl = shopDoc.select(".r-ent");
    	if(null==rentEl) {
    		return beanList;
    	}
    	for(int i=1;i<rentEl.size();i++){
    		MacShopBean bean = new MacShopBean();
    		Elements titleEle=rentEl.get(i).select(".title");
    		String title = titleEle.get(0).text();
    		//只統計販售
    		if(title.indexOf("販售")==-1) {
    			continue;
    		}
    		bean.setTitle(title);
    		String buyUrl = pttUrl+titleEle.get(0).getElementsByTag("a").attr("href");
    		bean.setBuyUrl(buyUrl);   				
    		Elements authorEle=rentEl.get(i).select(".author");
    		bean.setUser(authorEle.get(0).text());
    		Elements dateEle=rentEl.get(i).select(".date");
    		bean.setDate(dateEle.get(0).text());
    		ResponseEntity<String> responseEntity = restTemplate.getForEntity(buyUrl,String.class);
    		//log.info("responseEntity.getBody()"+responseEntity.getBody());
    		if(null==responseEntity.getBody()) {
    			continue;
    		}
    		Document commodityDoc = Jsoup.parse(responseEntity.getBody());
    		Elements mainEle = commodityDoc.select("#main-content");
    		String mainEleStr = mainEle.get(0).text();
    		String mainEleStrArr[] =  mainEleStr.split("\\s+\\[");
    		for(int j=0;j<mainEleStrArr.length;j++){
    			String arrOne = mainEleStrArr[j];   			
    			if(arrOne.indexOf("物品規格]：")!=-1) {
    				bean.setSpec(arrOne.substring(6));
    			}
				if(arrOne.indexOf("保固日期]：")!=-1) {
					bean.setWarrantyPeriod(arrOne.substring(6));				
				}
				if(arrOne.indexOf("交易地點]：")!=-1) {
					bean.setCity(arrOne.substring(6));
				}
				if(arrOne.indexOf("交易價格]：")!=-1) {
					bean.setMoney(arrOne.substring(6));
				}
				if(arrOne.indexOf("其他備註]：")!=-1) {
					String markStr = arrOne.substring(6);
					String markStrArr[]= markStr.split("※");
					bean.setMark(markStrArr[0]);
				}
    		}
    		beanList.add(bean);
    	}
    	return beanList;
    }
	public void test123() {
		String param = "{\"params\":\"{\\\"charset\\\":\\\"UTF-8\\\",\\\"user_ip\\\":\\\"127.0.0.1\\\",\\\"nonce_str\\\":\\\"43675\\\",\\\"pay_order_code\\\":\\\"665544340\\\",\\\"sync_url\\\":\\\"http://mobile.recharge.com\\\",\\\"subject\\\":\\\"shtpay支付宝\\\",\\\"merchant_ip\\\":\\\"127.0.0.1\\\",\\\"sign\\\":\\\"C0DC3BE53F408A5C24B7BF8EFD001831\\\",\\\"merchant_id\\\":\\\"MCH201806191950437670001\\\",\\\"body\\\":\\\"支付\\\",\\\"version\\\":\\\"1.0\\\",\\\"async_url\\\":\\\"http://106.75.130.135:8888/user/ttpay_notify.go\\\",\\\"total_fee\\\":\\\"101.100\\\",\\\"pay_type\\\":\\\"10011\\\"}\"}";
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("param", param);
		
		HttpEntity<MultiValueMap<String, String>> httpEntity = WebUtil.getHttpEntity(params,WebUtil.JSON);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://pay.vn28.cn/shtpay-center/pay/order",httpEntity, String.class);
        System.out.println(responseEntity);
	}
}

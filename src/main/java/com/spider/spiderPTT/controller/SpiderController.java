package com.spider.spiderPTT.controller;



import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spider.spiderPTT.dao.MacShopBean;
import com.spider.spiderPTT.service.SpiderDataService;




@RestController
@RequestMapping("/spider")
public class SpiderController {
	
	@Qualifier("MacShopSpiderServiceImpl")
	@Autowired
	private SpiderDataService spiderDataService;
	
	private static final Logger logger = LoggerFactory.getLogger(SpiderController.class);
	@Autowired
	private ObjectMapper mapper ;
	@RequestMapping("/spiderPttMacShop")
	public String spiderPttMacShop(String searchCondition) {
		List<MacShopBean> macDataList = new ArrayList<MacShopBean>();
		String returnData = "";
		try {
			Assert.notNull(searchCondition, "搜尋條件不可為空值");
			macDataList = spiderDataService.getSpiderData(searchCondition);
			returnData = mapper.writeValueAsString(macDataList);
		} catch (Exception e) {
			return e.getMessage();
		}
		return returnData;
	}


}
package com.spider.spiderPTT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spider.spiderPTT.controller.SpiderController;
import com.spider.spiderPTT.service.impl.MacShopSpiderServiceImpl;





@RunWith(SpringRunner.class)
@SpringBootTest
public class MacShopTest {
	@Autowired	
	SpiderController service;
	
	@Test
	public void execute() {
		String conStr = "macbook pro,20180630";
		try {
			String str = service.spiderPttMacShop(conStr);
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

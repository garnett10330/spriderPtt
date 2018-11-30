package com.spider.spiderPTT.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class ParseUtil {
	private static Logger log = LoggerFactory.getLogger(ParseUtil.class);
	
	/**
	 * 检测数据是否为String类型，是则返回true，不是或为null则返回false
	 * @param respObj
	 * @return
	 */
	public static boolean checkResponseDataIsStr(Object respObj) {
		boolean flag = false;
		if (respObj == null) {
			return flag;
		}
		if (respObj instanceof String) {
			flag = true;
		} else {
			log.error("Response回傳參數不為字串");
		}
		return flag;
	}

}

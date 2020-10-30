package com.cq.ddns.core;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cq.ddns.config.DDNSProperties;

@Component
public class IpParser {

	@Autowired
	DDNSProperties properties;

	/**
	 * 解析本地ip
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getOwnInternetIp() throws IOException {
		IOException exception = null;
		// 如果网路异常，最多重试三次
		for (int i = 0; i < 3; i++) {
			try {
				Document doc = Jsoup.connect(properties.getParseIpUrl()).get();
				String title = doc.title();
				String ip = title.replaceAll("您的IP地址是：", "");
				return ip;
			} catch (IOException e) {
				e.printStackTrace();
				exception = e;
			}
		}
		throw exception;
	}

}

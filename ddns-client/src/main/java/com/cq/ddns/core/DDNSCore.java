package com.cq.ddns.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cq.ddns.config.DDNSProperties;

@Component
public class DDNSCore {

	String ownInternateIp;

	@Autowired
	DDNSProperties properties;

	@Autowired
	IpParser parser;

	@Autowired
	DNSCaller caller;

	@Scheduled(cron = "${ddns.poll.cron:0 0/1 * * * ?}")
	public void run() throws Exception {
		String ip = parser.getOwnInternetIp();
		// 比对缓存ip，如果ip没有变动，则放弃后续任务
		if (!ip.equals(ownInternateIp)) {
			DNSEntity entity = caller.get(properties.getDnsRecordId());

			// 如果找不到对应的dns记录则抛出异常
			if (entity == null) {
				throw new RuntimeException("can not find dns in aliyun");
			}

			// 如果dns的ip和本地ip不一致则更新dns
			if (entity.getValue() != null && !entity.getValue().equals(ip)) {
				// 更新dns
				caller.update(entity.setValue(ip));
			}
			// 缓存ip
			ownInternateIp = ip;
		}
	}

}

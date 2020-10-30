package com.cq.ddns.core;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordInfoRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordInfoResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.cq.ddns.config.DDNSProperties;

@Component
public class DNSCaller implements InitializingBean {

	@Autowired
	DDNSProperties properties;

	IAcsClient client;

	private static final String REGION_ID = "cn-hangzhou";

	/**
	 * 更新单条dns
	 * 
	 * @param dns
	 */
	public void update(DNSEntity dns) {

		UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
		request.setRecordId(dns.getId());
		request.setRR(dns.getRr());
		request.setType(dns.getType());
		request.setValue(dns.getValue());
		try {
			client.getAcsResponse(request);

			return;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 获取单条dns
	 * 
	 * @param id
	 * @return
	 */
	public DNSEntity get(String id) {

		DescribeDomainRecordInfoRequest request = new DescribeDomainRecordInfoRequest();
		request.setRecordId(id);
		try {
			DescribeDomainRecordInfoResponse response = client.getAcsResponse(request);

			return new DNSEntity().setValue(response.getValue())
					.setType(response.getType())
					.setRr(response.getRR())
					.setId(response.getRecordId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		IClientProfile profile = DefaultProfile.getProfile(REGION_ID, properties.getAccessKeyID(),
				properties.getAccessKeySecret());
//		若报Can not find endpoint to access异常，请添加以下此行代码
//		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns","alidns.aliyuncs.com");
		client = new DefaultAcsClient(profile);

	}

}

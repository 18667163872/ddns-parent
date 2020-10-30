package com.cq.ddns.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ddns")
public class DDNSProperties {

	String accessKeyID;

	String accessKeySecret;

	String dnsRecordId;

	String parseIpUrl;

	public String getAccessKeyID() {
		return accessKeyID;
	}

	public DDNSProperties setAccessKeyID(String accessKeyID) {
		this.accessKeyID = accessKeyID;
		return this;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public DDNSProperties setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
		return this;
	}

	public String getDnsRecordId() {
		return dnsRecordId;
	}

	public DDNSProperties setDnsRecordId(String dnsRecordId) {
		this.dnsRecordId = dnsRecordId;
		return this;
	}

	public String getParseIpUrl() {
		return parseIpUrl;
	}

	public DDNSProperties setParseIpUrl(String parseIpUrl) {
		this.parseIpUrl = parseIpUrl;
		return this;
	}

}

package com.cq.ddns.core;

public class DNSEntity {

	String id;

	String rr;

	String type;

	String value;

	public String getId() {
		return id;
	}

	public DNSEntity setId(String id) {
		this.id = id;
		return this;
	}

	public String getRr() {
		return rr;
	}

	public DNSEntity setRr(String rr) {
		this.rr = rr;
		return this;
	}

	public String getType() {
		return type;
	}

	public DNSEntity setType(String type) {
		this.type = type;
		return this;
	}

	public String getValue() {
		return value;
	}

	public DNSEntity setValue(String value) {
		this.value = value;
		return this;
	}

}

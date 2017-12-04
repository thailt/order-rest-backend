package com.egs.bigdata.sqoop.client.rest.domain;

public interface Identifiable extends org.springframework.hateoas.Identifiable<Long> {
	public void setId(Long id);
}

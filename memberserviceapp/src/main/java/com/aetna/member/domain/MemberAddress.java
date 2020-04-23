package com.aetna.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberAddress {

	private String streetLine1;
	private String streetLine2;
	private String city;
	private String sate;
	private String zipcode;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
}

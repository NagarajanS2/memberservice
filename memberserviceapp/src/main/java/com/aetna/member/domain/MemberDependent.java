package com.aetna.member.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDependent {

	private String dependentId;
	private String dependentFirstName;
	private String dependentLastName;
	private Date dependentDateofBirth;
	private String relationship;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this); 
	}
}

package com.aetna.member.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

	private String responseCode;
	private String responseMessage;
	private String errorMessage;
	private AetnaMember aetnaMember;
	
}

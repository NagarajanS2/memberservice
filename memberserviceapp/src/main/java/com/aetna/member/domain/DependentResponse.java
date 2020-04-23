package com.aetna.member.domain;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DependentResponse {

	private String responseCode;
	private String responseMessage;
	private String errorMessage;
	private String memberId;
	
	private Optional<List<MemberDependent>> memberDependents=Optional.empty();
	
}
